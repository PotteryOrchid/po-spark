package com.po.study.spark.graphx.ch04

import java.io.StringWriter

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.scala.DefaultScalaModule

/**
  * Created by ZJ on 05/11/2018.
  */
object SaveJsonDemoPartitions {
  def main(args: Array[String]): Unit = {
    // save graph vertices to json by jackson-module-scala
    InitGraph.graph.vertices.mapPartitions(partition => {
      // create an mapper for each partition
      val mapper = new ObjectMapper()
      // register scala module
      mapper.registerModule(DefaultScalaModule)
      // create a writer
      val writer = new StringWriter()
      // write value on partition using same writer
      partition.map(vertex => {
        writer.getBuffer().setLength(0)
        mapper.writeValue(writer, vertex)
        writer.toString()
      })
    }).coalesce(1, true).saveAsTextFile("src/main/resources/tmp/json-v")

    // save graph edges to json file
    InitGraph.graph.edges.mapPartitions(partition => {
      val mapper = new ObjectMapper()
      mapper.registerModule(DefaultScalaModule)
      val writer = new StringWriter()
      partition.map(edge => {
        writer.getBuffer.setLength(0)
        mapper.writeValue(writer, edge)
        writer.toString()
      })
    }).coalesce(1, true).saveAsTextFile("src/main/resources/tmp/json-e")
  }
}
