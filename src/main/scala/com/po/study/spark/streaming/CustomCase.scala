package com.po.study.spark.streaming

import java.sql.Timestamp

/**
  * Model
  */
case class Address(isHave: Boolean, home: String, com: Array[String])

case class Person(name: String, age: Int, address: Address)

case class MsgTest(key: String, value: Person, topic: String, partition: Int, offset: Long, timestamp: Timestamp, timestampType: Int)

case class Out(name: String, age: Int, count: Long)

case class Msg(key: String, value: String, topic: String, partition: Int, offset: Long, timestamp: Timestamp, timestampType: Int)
