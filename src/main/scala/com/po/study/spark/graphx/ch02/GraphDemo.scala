package com.po.study.spark.graphx.ch02

import org.apache.spark.graphx.GraphLoader
import org.apache.spark.{SparkConf, SparkContext}

/**
  * Created by ZJ on 31/10/2018.
  */
object GraphDemo {
  def main(args: Array[String]): Unit = {
    // Create an spark context
    val conf = new SparkConf().setAppName("GraphDemo").setMaster("local")
    val sc = new SparkContext(conf)

    // load graph data
    val graph = GraphLoader.edgeListFile(sc, "src/main/resources/data/cit-HepTh.txt")
    // calculate the most cited papers
    val res = graph.inDegrees.reduce((a, b) => if (a._2 > b._2) a else b)
    println(res)
  }
}
