package com.po.study.spark.graphx.ch04

import java.io.StringWriter

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.scala.DefaultScalaModule

/**
  * Created by ZJ on 05/11/2018.
  */
object SaveJsonDemoPartitions {
  def main(args: Array[String]): Unit = {
    // save graph to json by jackson-module-scala
    InitGraph.graph.vertices.map(x => {
      // create mapper
      val mapper = new ObjectMapper()
      // register scala module
      mapper.registerModule(DefaultScalaModule)
      // writer value to text file
      val writer = new StringWriter()
      mapper.writeValue(writer, x)
      writer.toString()
    }).coalesce(1, true).saveAsTextFile("src/main/resources/tmp/json-v")
  }
}
