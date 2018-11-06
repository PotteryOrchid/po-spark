package com.po.study.spark.graphx.ch04

import java.io.PrintWriter

import org.apache.spark.graphx.Graph

/**
  * Created by ZJ on 05/11/2018.
  */
object SaveGexf extends App {
  // define generate gexf file method
  def toGexf[VD, ED](graph: Graph[VD, ED]) = {
    "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<gexf xmlns=\"http://www.gexf.net/1.2draft\" version=\"1.2\">\n\t<graph mode=\"static\" defaultedgetype=\"directed\">\n\t\t<nodes>" + graph.vertices.map(v => "\n\t\t\t<node id=\"" + v._1 + "\" label=\"" + v._2 + "\" />").collect().mkString + "\n\t\t</nodes>\n\t\t<edges>" + graph.edges.map(e => "\n\t\t\t<edge source=\"" + e.srcId + "\" target=\"" + e.dstId + "\" label=\"" + e.attr + "\" />").collect.mkString + "\n\t\t</edges>\n\t</graph>\n</gexf>"
  }

  // create print writer
  val pw = new PrintWriter("src/main/resources/tmp/gexf/demo1.gexf")
  pw.write(toGexf(InitGraph.graph))
  pw.close()
}
