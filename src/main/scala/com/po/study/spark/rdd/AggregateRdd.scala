package com.po.study.spark.rdd

import org.apache.spark.{SparkConf, SparkContext}

/**
  * Created by ZJ on 20/11/2018.
  */
object AggregateRdd extends {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("Spark Run ...").setMaster("local[3]")
    val sc = new SparkContext(conf)

    /**
      * aggregate demo
      */
    val data = sc.parallelize(List(23, 2, 1, 4, 5, 6));
    println(data.aggregate(9)((a: Int, b: Int) => math.min(a, b), (x: Int, y: Int) => x + y))

    /**
      *
      */
    val data1 = sc.parallelize(List((1, 3), (1, 7), (1, 8), (2, 13), (2, 1)))
    data1.aggregateByKey(3)((a, b) => {
      println("seq: " + a + "," + b);
      math.max(a, b)
    }, (a, b) => {
      println("com: " + a + "," + b);
      a + b
    }).collect().foreach(println)
  }
}
