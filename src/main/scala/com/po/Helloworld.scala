package main.scala.com.po

import com.po.anyref.Person

/**
  * Created by ZJ on 27/07/2018.
  */
object Helloworld extends App {

  def square(x: Int): Int = x * x

  square(2)

  val person = new Person(name = "Book")

  person.name = "Book" // 可变构造函数
  person.setNumber_=(-1) // 特殊setter方法
  person.cmnt = "hello1" // 公有属性

  person.say()

  println(person.name + person.gender + person.cmnt)
}
