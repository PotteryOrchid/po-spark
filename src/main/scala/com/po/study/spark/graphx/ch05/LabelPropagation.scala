package com.po.study.spark.graphx.ch05

import org.apache.spark.graphx.{Edge, Graph}
import org.apache.spark.graphx.lib.LabelPropagation
import org.apache.spark.{SparkConf, SparkContext}

/**
  * Created by ZJ on 09/11/2018.
  */
object LabelPropagationDemo extends App {
  // Initialize spark context
  val conf = new SparkConf().setAppName("Label Propagation").setMaster("local")
  val sc = new SparkContext(conf)
  // Initial graph vertices and edges
  val vertices = sc.makeRDD(Array((1L, "a"), (2L, "b"), (3L, "c"), (4L, "d"), (5L, "e"), (6L, "f"), (7L, "g"), (8L, "h")))
  val edges = sc.makeRDD(Array(Edge(1L, 2L, ""), Edge(2L, 3L, ""), Edge(3L, 4L, ""), Edge(4L, 1L, ""), Edge(1L, 3L, ""), Edge(2L, 4L, ""), Edge(4L, 5L, ""), Edge(5L, 6L, ""), Edge(6L, 7L, ""), Edge(7L, 8L, ""), Edge(8L, 5L, ""), Edge(5L, 7L, ""), Edge(6L, 8L, "")))
  // Show label propagation demo
  LabelPropagation.run(Graph(vertices, edges), 1).vertices.collect.sortWith(_._1 < _._1).foreach(println)
}
