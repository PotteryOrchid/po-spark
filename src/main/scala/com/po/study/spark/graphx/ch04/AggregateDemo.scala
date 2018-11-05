package com.po.study.spark.graphx.ch04

/**
  * Created by ZJ on 02/11/2018.
  */
object AggregateDemo {
  def main(args: Array[String]): Unit = {
    // computer out-degree of each vertex
    InitGraph.graph.aggregateMessages[Int](_.sendToSrc(1), _ + _).rightOuterJoin(InitGraph.graph.vertices).map(x => (x._2._2, x._2._1.getOrElse(0))).collect().foreach(println)
  }
}
