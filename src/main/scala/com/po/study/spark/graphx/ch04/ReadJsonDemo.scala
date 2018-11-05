package com.po.study.spark.graphx.ch04

import com.fasterxml.jackson.core.`type`.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.scala.DefaultScalaModule
import org.apache.spark.graphx.{Edge, Graph}

/**
  * Created by ZJ on 05/11/2018.
  */
object ReadJsonDemo extends App {
  // define graph
  val g = Graph(
    // read vertices value
    InitGraph.sc.textFile("src/main/resources/tmp/json-v").mapPartitions(vertices => {
      val mapper = new ObjectMapper()
      mapper.registerModule(new DefaultScalaModule)
      vertices.map(vertex => {
        val r = mapper.readValue[Tuple2[Integer, String]](vertex, new TypeReference[Tuple2[Integer, String]] {})
        (r._1.toLong, r._2)
      })
    }),
    // read edges value
    InitGraph.sc.textFile("src/main/resources/tmp/json-e").mapPartitions(edges => {
      val mapper = new ObjectMapper()
      mapper.registerModule(new DefaultScalaModule)
      edges.map(e => {
        mapper.readValue[Edge[String]](e, new TypeReference[Edge[String]] {})
      })
    }))

  // show graph
  g.triplets.collect().foreach(println)
}
