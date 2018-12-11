package com.po.study.spark.streaming

import Session.getSpark.implicits._
import com.po.study.util.EnvProperties
import org.apache.spark.sql._
import org.apache.spark.sql.streaming.StreamingQuery
import org.apache.spark.sql.types.{StringType, StructType}

/**
  * Created by ZJ on 08/12/2018.
  */
object KafkaPkg {

  private val spark: SparkSession = Session.getSpark

  def read(topic: String, group: String): Dataset[Session.R] = {
    val df = reader(topic, group)

    df.selectExpr("CAST(key AS STRING)", "CAST(value AS STRING)", "CAST(topic AS STRING)", "CAST(partition AS INT)", "CAST(offset AS LONG)", "CAST(timestamp AS LONG)", "CAST(timestampType AS INT)").as[Session.R]
  }

  def readSchema(topic: String, group: String, schema: StructType): Dataset[Session.R] = {
    import org.apache.spark.sql.functions.from_json

    val df = reader(topic, group)

    df.withColumn("value", from_json($"value".cast(StringType), schema)).as[Session.R]
  }

  def readBatch(topic: String, group: String): Dataset[Session.R] = {
    val df = batchReader(topic, group)

    df.selectExpr("CAST(key AS STRING)", "CAST(value AS STRING)", "CAST(topic AS STRING)", "CAST(partition AS INT)", "CAST(offset AS LONG)", "CAST(timestamp AS LONG)", "CAST(timestampType AS INT)").as[Session.R]
  }

  def write(ds: Dataset[Session.W], topic: String)(op: Dataset[Session.W] => DataFrame): StreamingQuery = {
    op(ds).writeStream.format("kafka")
      .outputMode("complete")
      .option("kafka.bootstrap.servers", EnvProperties.getProperty("kafka.bootstrap.servers"))
      .option("topic", topic)
      .option("checkpointLocation", EnvProperties.getProperty("spark.checkpoint.path"))
      .start()
  }

  def writeBatch(ds: Dataset[Session.W], topic: String)(op: Dataset[Session.W] => DataFrame): Unit = {
    op(ds).write.format("kafka")
      .option("kafka.bootstrap.servers", EnvProperties.getProperty("kafka.bootstrap.servers"))
      .option("topic", topic)
      .save()
  }

  private def reader(topic: String, group: String): DataFrame = {
    spark.readStream.format("kafka")
      .option("kafka.bootstrap.servers", EnvProperties.getProperty("kafka.bootstrap.servers"))
      .option("subscribe", topic)
      .option("group.id", group)
      .load()
  }

  private def batchReader(topic: String, group: String) = {
    spark.read.format("kafka")
      .option("kafka.bootstrap.servers", EnvProperties.getProperty("kafka.bootstrap.servers"))
      .option("subscribe", topic)
      .option("group.id", group)
      .load()
  }
}