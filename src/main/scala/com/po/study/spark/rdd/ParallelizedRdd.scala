package com.po.study.spark.rdd

import com.po.study.spark.SparkInitialization

/**
  * Created by ZJ on 19/11/2018.
  */
object ParallelizedRdd extends App {
  val arr = Array(23, 343, 121, 3443)
  val arrRdd = SparkInitialization.sc.parallelize(arr, 10)
  println(arrRdd.reduce((x, y) => x + y))
}
