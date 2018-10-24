package com.po.study.scala.traits

import scala.collection.mutable.ArrayBuffer

/**
  * Created by ZJ on 01/08/2018.
  */
trait Pet {
  val name: String
}

object Pet {
  def main(args: Array[String]): Unit = {
    val pets = ArrayBuffer.empty[Pet]
    pets.append(new Dog("Scala"))
    pets.append(new Cat("spark"))
    pets.foreach(p => println(p.name))
  }
}
