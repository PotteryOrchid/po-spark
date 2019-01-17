package com.po.study.spark.dataset

import org.apache.spark.sql.SparkSession

/**
  * Created by ZJ on 18/12/2018.
  */
object Demo {

  val spark = SparkSession.builder().master("local").getOrCreate()

  import spark.implicits._

  val primitiveDS = Seq(1, 2, 3).toDS()

}
