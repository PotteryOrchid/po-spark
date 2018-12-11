package com.po.study.spark.streaming

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.types._

/**
  * Created by ZJ on 05/12/2018.
  */
object StructuredStreamingKafka {

  val spark: SparkSession = SparkSession.builder().appName("kafka demo").master("local").getOrCreate()

  def readStream(): Unit = {
    import spark.implicits._

    val df = spark
      .readStream
      .format("kafka")
      .option("kafka.bootstrap.servers", "localhost:9092")
      // set topic by regex
      .option("subscribePattern", """test.+""")
      .load()

    val out = df.selectExpr("CAST(key AS STRING)", "CAST(value AS STRING)", "CAST(topic AS STRING)", "CAST(partition AS INT)", "CAST(offset AS LONG)", "CAST(timestamp AS LONG)", "CAST(timestampType AS INT)")
      .as[(String, String, String, Int, Long, Long, Int)].select("key", "value").groupBy("key").count()

    val flag = "update"

    flag match {
      // only sink the append result (append mode need watermark)
      case "append" => out.writeStream.outputMode("append").format("console").start()
      // sink the update result which to be appended and changed
      case "update" => out.writeStream.outputMode("update").format("console").start()
      // complete mode will storage result in memory all time
      case "complete" => out.writeStream.outputMode("complete").format("console").start()
      case _ => println("error")
    }
  }

  def readSchema(): Unit = {
    import spark.implicits._
    import org.apache.spark.sql.functions.from_json

    val df = spark
      .readStream
      .format("kafka")
      .option("kafka.bootstrap.servers", "localhost:9092")
      // set topic by regex
      .option("subscribePattern", """test.+""")
      .load()

    val schema = StructType(
      StructField("name", StringType, true) ::
        StructField("age", IntegerType, true) ::
        StructField("address",
          StructType(
            StructField("isHave", BooleanType, true) ::
              StructField("home", StringType, true) ::
              StructField("com", ArrayType(StringType, true), true) :: Nil
          ), false) :: Nil)

    val out = df.withColumn("value", from_json($"value".cast(StringType), schema)).as[Msg].groupBy("value.name", "value.age").count()

    val flag = "complete"

    flag match {
      // only sink the append result (append mode need watermark)
      case "append" => out.writeStream.outputMode("append").format("console").start()
      // sink the update result which to be appended and changed
      case "update" => out.writeStream.outputMode("update").format("console").start()
      // complete mode will storage result in memory all time
      case "complete" => out.writeStream.option("hi", "hello").outputMode("complete").format("console").start()
      case _ => println("error")
    }
  }

  def readBatch(): Unit = {
    import spark.implicits._

    val df = spark
      .read
      .format("kafka")
      .option("kafka.bootstrap.servers", "localhost:9092")
      .option("subscribe", "test_zj")
      // can be customized
      .option("startingOffsets", "earliest")
      .option("endingOffsets", "latest")
      .load()

    val out = df.selectExpr("CAST(key AS STRING)", "CAST(value AS STRING)")
      .as[(String, String)]

    out.show()
  }

  def main(args: Array[String]): Unit = {
    //    StructuredStreamingKafka.readStream()

    StructuredStreamingKafka.readSchema()

    //    for (i <- 1 to 10) {
    //      StructuredStreamingKafka.readBatch()
    //      Thread.sleep(6000)
    //      println(i)
    //    }

    spark.streams.awaitAnyTermination()
  }
}
