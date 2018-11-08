package com.po.study.spark.graphx.ch05

import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.graphx.{Edge, Graph}

/**
  * Created by ZJ on 08/11/2018.
  */
object ConnectedComponent extends App {
  // Initialize spark context
  val conf = new SparkConf().setAppName("ConnectedComponent").setMaster("local")
  val sc = new SparkContext(conf)
  // create graph (1), (3,2,5,4), (6,7)
  val g = Graph(sc.makeRDD((1L to 7L).map((_, ""))), sc.makeRDD(Array(Edge(2L, 5L, ""), Edge(5L, 3L, ""), Edge(3L, 2L, ""), Edge(4L, 5L, ""), Edge(6L, 7L, ""))))
  // call graph connected component method
  g.connectedComponents().vertices.groupBy(v => v._2).map(x => x._2).collect().foreach(println)
}
