package com.po.study.spark.graphx.ch05

import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.graphx.Graph
import org.apache.spark.rdd.RDD

/**
  * Created by ZJ on 08/11/2018.
  */
object SocialCircle extends App {

  /**
    * extract file name to id: (\d+) subexpresion to get a matched result.
    *
    * @param str : File path str
    * @return : ID
    */
  def extract(str: String): String = {
    val pattern = """^.*?(\d+).egonet""".r
    val pattern(id) = str
    id
  }

  /**
    * defined method to translate file content str to edges tuple.
    *
    * @param str : Input string data
    * @return : Edges Array
    */
  def getEdgeDataFromStr(str: String): Array[(Long, Long)] = {
    val lines = str.split("\n")
    val res = for (line <- lines) yield {
      val arr = line.split(":")
      val srcId = arr(0).trim.toInt
      val dstIds = arr(1).trim.split(" ")
      val edges = for (dstId <- dstIds; if dstId.trim != "") yield {
        (srcId.toLong, dstId.toLong)
      }
      if (edges.size == 0) Array((srcId.toLong, srcId.toLong)) else edges
    }
    // Flattens a two-dimensional array by concatenating all its rows
    res.flatten
  }

  /**
    * Get circle components result
    *
    * ++ : Combine two array set into one array
    * mkString : Split elements by the parameter str
    *
    * @param flat : Input edges data
    * @return : Circle array
    */
  def getCircle(flat: Array[(Long, Long)]): String = {
    Graph.fromEdgeTuples(sc.makeRDD(flat), 1).connectedComponents().vertices.map(x => (x._2, Array(x._1))).reduceByKey((a, b) => a ++ b).values.map(_.mkString(" ")).collect.mkString("; ")
  }

  // Initialize spark context
  val conf = new SparkConf().setAppName("SocialCircle").setMaster("local")
  val sc = new SparkContext(conf)
  // Read file info list in a path, each element in list is a (key, value) tuple, key is file path and value is file content
  val egonets: RDD[(String, String)] = sc.wholeTextFiles("src/main/resources/data/egonets")
  // Generate ego ids reset according to file name, each file path contain a id so can get id from path str
  val ego_ids: Array[String] = egonets.map(e => extract(e._1)).collect()
  // Get graph edges data from file content, each file has several lines and each line is a key-value structure Str.
  val ego_edgedata: Array[Array[(Long, Long)]] = egonets.map(e => getEdgeDataFromStr(e._2)).collect()
  // Calculate circles
  val circles: List[String] = ego_edgedata.toList.map(x => getCircle(x))
  // Combine ids and circles
  val res = ego_ids.zip(circles).map(x => x._1 + " ==: " + x._2)
  println(res.mkString("\n"))
}
