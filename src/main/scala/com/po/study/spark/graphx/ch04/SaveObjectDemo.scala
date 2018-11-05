package com.po.study.spark.graphx.ch04

import org.apache.spark.graphx.{Edge, Graph, VertexId}

/**
  * Created by ZJ on 05/11/2018.
  */
object SaveObjectDemo extends App {
  var createGraph = false
  if (createGraph == true) {
    // save  vertices object
    InitGraph.graph.vertices.saveAsObjectFile("src/main/resources/tmp/vertices")
    // save edges object
    InitGraph.graph.edges.saveAsObjectFile("src/main/resources/tmp/edges")
  }
  var showGraph = true
  if (showGraph == true) {
    // loading vertices and edges from file object
    val graph1 = Graph(InitGraph.sc.objectFile[Tuple2[VertexId, String]]("src/main/resources/tmp/vertices"), InitGraph.sc.objectFile[Edge[String]]("src/main/resources/tmp/edges"))
    graph1.triplets.collect().foreach(println)
  }
}
