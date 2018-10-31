package com.po.study.spark.graphx.ch02

import org.apache.spark.graphx.GraphLoader
import org.apache.spark.{SparkConf, SparkContext}

/**
  * Created by ZJ on 31/10/2018.
  */
object PageRank extends App {
  // create spark context
  val conf = new SparkConf().setAppName("PageRank").setMaster("local")
  val sc = new SparkContext(conf)

  // initialize graph loading data from file
  val graph = GraphLoader.edgeListFile(sc, "src/main/resources/data/cit-HepTh.txt")
  // show top 10 vertices
  graph.vertices.take(10).foreach(println)

  // calculate graph page rank
  val res = graph.pageRank(0.001).vertices.take(10)
  res.foreach(println)

  // find the biggest page rank vertex
  println(res.reduce((a, b) => if (a._2 > b._2) a else b))
}
