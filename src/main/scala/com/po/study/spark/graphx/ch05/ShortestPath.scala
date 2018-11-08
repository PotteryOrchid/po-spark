package com.po.study.spark.graphx.ch05

import com.po.study.spark.graphx.ch04.InitGraph
import org.apache.spark.graphx.lib.ShortestPaths

/**
  * Created by ZJ on 08/11/2018.
  */
object ShortestPath extends App {
  // Computes shortest paths to the given set of landmark vertices. In this demo landmark vertex is node with id 3L
  // if dst node can not be reached map result is null
  ShortestPaths.run(InitGraph.graph, Array(3L)).vertices.collect().foreach(println)
}
