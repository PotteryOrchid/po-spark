package com.po.study.spark.graphx.ch05

import org.apache.spark.graphx.{Edge, Graph}
import org.apache.spark.{SparkConf, SparkContext}

/**
  * 每个顶点都可以通过其他顶点到达，且没有闭塞不通的路径。
  * Created by ZJ on 09/11/2018.
  */
object StrongConnectedComponent extends App {
  // Initialize spark context
  val conf = new SparkConf().setAppName("ConnectedComponent").setMaster("local")
  val sc = new SparkContext(conf)
  // create graph (1), (3,2,5,4), (6,7)
  val g = Graph(sc.makeRDD((1L to 7L).map((_, ""))), sc.makeRDD(Array(Edge(2L, 5L, ""), Edge(5L, 3L, ""), Edge(3L, 2L, ""), Edge(4L, 5L, ""), Edge(6L, 7L, ""))))
  // show use demo
  val vertices = g.stronglyConnectedComponents(10).vertices.map(_.swap).cache()
  vertices.groupByKey().collect().foreach(println)
  vertices.groupBy(_._1).collect().foreach(println)
}
