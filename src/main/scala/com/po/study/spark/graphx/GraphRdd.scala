package com.po.study.spark.graphx

import org.apache.spark.graphx.{Edge, Graph, VertexId, VertexRDD}
import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
  * Created by ZJ on 29/10/2018.
  */
class GraphRdd {

}

object GraphRdd {
  def main(args: Array[String]): Unit = {
    // Create an spark context
    val conf = new SparkConf().setAppName("Graph demo").setMaster("local")
    val sc = new SparkContext(conf)

    // Create an RDD for vertices
    val users: RDD[(VertexId, (String, String))] = sc.parallelize(Array((3L, ("rxin", "student")), (7L, ("jgonzal", "postdoc")),
      (5L, ("franklin", "prof")), (2L, ("istoica", "prof"))))

    // Create an RDD for edges
    val relationships: RDD[Edge[String]] =
      sc.parallelize(Array(Edge(3L, 7L, "collab"),
        Edge(3L, 7L, "colleague"), Edge(5L, 3L, "advisor"),
        Edge(2L, 5L, "colleague"), Edge(5L, 7L, "pi")))

    // Define a default user in case there are relationship with missing user
    val defaultUser = ("John Doe", "Missing")

    // Build the initial Graph
    val graph = Graph(users, relationships, defaultUser)

    // Vertices filter
    val count1 = graph.vertices.filter { case (id, v) => v._2 == "postdoc" }.count()
    val count2 = graph.vertices.filter({ case (id, (name, pos)) => pos == "postdoc" }).count()
    println("vertices count: ", count1, count2)

    // Edges filter
    val count3 = graph.edges.filter(e => e.attr == "pi").count()
    val count4 = graph.edges.filter(e => e.srcId > e.dstId).count()
    val count5 = graph.edges.filter { case Edge(src, dst, prop) => src > dst }.count
    println("edges count: ", count3, count4, count5)

    // Use the triplets view to create an RDD of facts
    val facts: RDD[String] = graph.triplets.map(triplet => triplet.srcId + " is the " + triplet.attr + " of " + triplet.dstId)
    facts.foreach(str => println(str))

    // vertices in-degrees
    val inDegrees: VertexRDD[Int] = graph.inDegrees
    inDegrees.foreach({ case (id, n) => println("the in-degrees of vertex " + id.toString + " is : " + n) })

    // vertices out-degrees
    val outDegrees: VertexRDD[Int] = graph.outDegrees
    outDegrees.foreach({ case (id, n) => println("the out-degrees of vertex " + id.toString + " is : " + n) })

    // Property Operators
    val inputGraph: Graph[Int, String] = graph.outerJoinVertices(graph.outDegrees)((vid, _, degOpt) => degOpt.getOrElse(0))
    val outputGraph: Graph[Double, Double] = inputGraph.mapTriplets(triplet => 1.0 / triplet.srcAttr).mapVertices((id, _) => 1.0)
    val facts2: RDD[String] = inputGraph.triplets.map(triplet => "facts2: " + triplet.srcId + " is the " + triplet.attr + " of " + triplet.dstId)
    facts2.foreach(str => println(str))
    val facts3: RDD[String] = outputGraph.triplets.map(triplet => "facts3: " + triplet.srcId + " is the " + triplet.attr + " of " + triplet.dstId)
    facts3.foreach(str => println(str))

  }
}
