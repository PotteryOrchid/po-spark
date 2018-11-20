package com.po.study.spark

import org.apache.spark.{SparkConf, SparkContext}

/**
  * Created by ZJ on 19/11/2018.
  */
object SparkInitialization {
  val conf = new SparkConf().setAppName("Spark Run ...").setMaster("local")
  val sc = new SparkContext(conf)
}
