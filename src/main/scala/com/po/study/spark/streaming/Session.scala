package com.po.study.spark.streaming

import org.apache.spark.sql.SparkSession
import org.apache.spark.{SparkConf, SparkContext}

/**
  * Created by ZJ on 08/12/2018.
  */
object Session extends SessionType {
  // Initialize spark session
  val getSpark: SparkSession = SparkSession.builder().appName("kafka demo").master("local").getOrCreate()

  // Get spark context
  val getContext: SparkContext = getSpark.sparkContext

  // Get spark conf
  val getConf: SparkConf = getSpark.sparkContext.getConf
}
