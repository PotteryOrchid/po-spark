package com.po.study.spark.graphx.ch04

import org.apache.spark.graphx.{EdgeContext, Graph}

/**
  * Created by ZJ on 02/11/2018.
  */
object PropagateDemo extends App {
  // defined send msg function
  def sendMsg(ec: EdgeContext[Int, String, Int]): Unit = {
    ec.sendToDst(ec.srcAttr + 1)
  }

  // defined merge msg function
  def mergeMsg(a: Int, b: Int): Int = {
    math.max(a, b)
  }

  // define propagate edge count
  def propagateEdgeCount(in: Graph[Int, String]): Graph[Int, String] = {
    val verts = in.aggregateMessages(sendMsg, mergeMsg)
    val graph = Graph(verts, in.edges)
    val check = graph.vertices.join(in.vertices).map(x => x._2._1 - x._2._2).reduce(_ + _)
    if (check > 0)
      propagateEdgeCount(graph)
    else
      graph
  }

  val initialGraph = InitGraph.graph.mapVertices((_, _) => 0)
  propagateEdgeCount(initialGraph).vertices.collect().foreach(println)

}
