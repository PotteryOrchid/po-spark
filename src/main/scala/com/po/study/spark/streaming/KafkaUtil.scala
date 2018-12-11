package com.po.study.spark.streaming

import Session.getSpark.implicits._
import com.po.study.util.EnvProperties
import org.apache.spark.sql.streaming.StreamingQuery
import org.apache.spark.sql.types.{StringType, StructType}
import org.apache.spark.sql.{DataFrame, Dataset, SparkSession}

/**
  * Created by ZJ on 08/12/2018.
  */
object KafkaUtil {

  private val spark: SparkSession = Session.getSpark

  def read(topic: String, group: String): Dataset[MsgTest] = {
    val df = reader(topic, group)

    df.selectExpr("CAST(key AS STRING)", "CAST(value AS STRING)", "CAST(topic AS STRING)", "CAST(partition AS INT)", "CAST(offset AS LONG)", "CAST(timestamp AS LONG)", "CAST(timestampType AS INT)").as[MsgTest]
  }

  def readSchema(topic: String, group: String, schema: StructType): Dataset[MsgTest] = {
    import org.apache.spark.sql.functions.from_json

    val df = reader(topic, group)

    df.withColumn("value", from_json($"value".cast(StringType), schema)).as[MsgTest]
  }

  def readBatch(topic: String, group: String): Dataset[MsgTest] = {
    val df = batchReader(topic, group)

    df.selectExpr("CAST(key AS STRING)", "CAST(value AS STRING)", "CAST(topic AS STRING)", "CAST(partition AS INT)", "CAST(offset AS LONG)", "CAST(timestamp AS LONG)", "CAST(timestampType AS INT)").as[MsgTest]
  }

  def write(ds: Dataset[MsgTest], topic: String)(op: Dataset[MsgTest] => DataFrame): StreamingQuery = {
    op(ds).writeStream.format("kafka")
      .outputMode("complete")
      .option("kafka.bootstrap.servers", EnvProperties.getProperty("kafka.bootstrap.servers"))
      .option("topic", topic)
      .option("checkpointLocation", EnvProperties.getProperty("spark.checkpoint.path"))
      .start()
  }

  def writeBatch(ds: Dataset[MsgTest], topic: String)(op: Dataset[MsgTest] => DataFrame): Unit = {
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