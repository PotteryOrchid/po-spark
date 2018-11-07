package com.po.study.spark.graphx.ch04

import java.io.PrintWriter

import org.apache.spark.graphx.Graph
import org.apache.spark.graphx.util.GraphGenerators

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

  // generate grid graph
  val pw1 = new PrintWriter("src/main/resources/tmp/gexf/demo2.gexf")
  pw1.write(toGexf(GraphGenerators.gridGraph(InitGraph.sc, 4, 4)))
  pw1.close()

  // generate star graph
  val pw2 = new PrintWriter("src/main/resources/tmp/gexf/demo3.gexf")
  pw2.write(toGexf(GraphGenerators.starGraph(InitGraph.sc, 8)))
  pw2.close()

  // generate log normal graph
  val pw3 = new PrintWriter("src/main/resources/tmp/gexf/demo4.gexf")
  val graph3 = GraphGenerators.logNormalGraph(InitGraph.sc, 20)
  pw3.write(toGexf(graph3))
  println(graph3.aggregateMessages[Int](_.sendToSrc(1), _ + _).map(_._2).collect().sorted)
  pw3.close()

  //
}
