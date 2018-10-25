package com.po.study.scala.anyref

/**
  * main method can only be declared in object, not can be in class.
  * Created by ZJ on 27/07/2018.
  */
object Helloworld {

  def square(x: Int): Int = x * x

  def main(args: Array[String]): Unit = {

    square(2)

    val person = new Person(name = "Book")

    person.name = "Book" // 可变构造函数
    person.setNumber_=(-1) // 特殊setter方法
    person.cmnt = "hello1" // 公有属性

    person.say()

    println(person.name + person.gender + person.cmnt)
  }

}
