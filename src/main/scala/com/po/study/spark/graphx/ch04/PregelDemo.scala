package com.po.study.spark.graphx.ch04

import org.apache.spark.graphx.{EdgeDirection, EdgeTriplet, Pregel, VertexId}

/**
  * Created by ZJ on 07/11/2018.
  */
object PregelDemo extends App {
  /**
    * pregel graph
    * InitGraph.graph.mapVertices((vid, vd) => 0) ##初始化图对象
    * 0 ##传递消息初始化
    * activeDirection = EdgeDirection.Out ##定义消息传递方向
    *
    * (vid: VertexId, vd: Int, pd: Int) => math.max(vd, pd) ##用mergeMsg传递来的消息更新节点属性
    * (sendMsg: EdgeTriplet[Int, String]) => Iterator((sendMsg.dstId, sendMsg.dstAttr + 1)) ##定义sendMsg及下一过程传递节点范围
    * (a: Int, b: Int) => math.max(a, b) ##定义消息合并处理逻辑
    */
  val pregel = Pregel(InitGraph.graph.mapVertices((vid, vd) => 0), 0, activeDirection = EdgeDirection.Out)((vid: VertexId, vd: Int, pd: Int) => math.max(vd, pd), (sendMsg: EdgeTriplet[Int, String]) => Iterator((sendMsg.dstId, sendMsg.dstAttr + 1)), (a: Int, b: Int) => math.max(a, b))
  pregel.vertices.collect.foreach(println)
}
