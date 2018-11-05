package com.po.study.spark.graphx.ch04

import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.graphx.{Edge, Graph}

/**
  * Created by ZJ on 02/11/2018.
  */
object InitGraph {
  // create spark context
  val conf = new SparkConf().setMaster("local").setAppName("ch04-Demo")
  val sc = new SparkContext(conf)
  // initialize vertices
  val vertices = sc.parallelize(Array((1L, "Anna"), (2L, "Bill"), (3L, "Charles"), (4L, "Diane"), (5L, "Went to gym this morning")))
  // initialize edges
  val edges = sc.parallelize(Array(Edge(1L, 2L, "is-friends-with"), Edge(2L, 3L, "is-friends-with"), Edge(3L, 4L, "is-friends-with"), Edge(4L, 5L, "Likes-status"), Edge(3L, 5L, "Wrote-status")))
  // initialize graph
  val graph = Graph(vertices, edges)
}
