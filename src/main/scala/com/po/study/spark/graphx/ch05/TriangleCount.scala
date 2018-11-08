package com.po.study.spark.graphx.ch05

import org.apache.spark.graphx.{Edge, Graph, GraphLoader, PartitionStrategy}
import org.apache.spark.{SparkConf, SparkContext}

/**
  * Created by ZJ on 07/11/2018.
  */
object TriangleCount {
  def main(args: Array[String]): Unit = {
    // Initialize spark context
    val conf = new SparkConf().setAppName("TriangleCount").setMaster("local")
    val sc = new SparkContext(conf)

    // create graph and swap edge nodes if srcId > dstId
    val graph = GraphLoader.edgeListFile(sc, "src/main/resources/data/soc-Slashdot0811.txt").mapEdges(e => if (e.srcId > e.dstId) new Edge(e.dstId, e.srcId, e.attr) else e).cache()
    // get graph partition
    val graph1 = graph.partitionBy(PartitionStrategy.RandomVertexCut)
    // calculate triangle count for each sub graph
    (0 to 6).map(i => graph1.subgraph(vpred = (vid, vd) => vid >= i * 10000 && vid < (i + 1) * 10000).triangleCount().vertices.map(_._2).reduce(_ + _)).foreach(println)
  }
}
