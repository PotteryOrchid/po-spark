package com.po.study.spark.graphx.ch05

import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.graphx.GraphLoader

/**
  * Created by ZJ on 07/11/2018.
  */
object PageRank extends App {
  // Initialize spark context
  val conf = new SparkConf().setAppName("PageRank").setMaster("local")
  val sc = new SparkContext(conf)

  // personalized page rank
  val graph = GraphLoader.edgeListFile(sc, "src/main/resources/data/cit-HepTh.txt")
  println(graph.personalizedPageRank(9207016, 0.001).vertices.filter(_._1 != 9207016).reduce((a, b) => if (a._2 > b._2) a else b))
}
