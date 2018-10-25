package com.po.study.scala.basics

/**
  * We will cover basics of Scala.
  * Created by ZJ on 25/10/2018.
  */
class Basics {

}

object Basics extends App {

  /**
    * show println use.
    */
  println(23)
  println("hello " + "world!")

  /**
    * Values can not be re-assigned.
    */
  val val0 = "hello, "
  // State the type
  val val1: Int = 34

  /**
    * Variables
    */
  var var0 = 1 + 2
  var0 = 23
  println(var0)
  // State the type
  var var1: String = "kit"

  /**
    * Blocks: Combining expressions by surrounding them with {} .
    */
  println({
    val x = 34 + 23
    x + 2
  })

  /**
    * Functions are expressions that take parameters.
    * On the left of => is the list of parameters.
    * The last expression in the body is the function's return value.
    */
  // An anonymous function that return a integer plus one.
  (x: Int) => {
    x + 1
  }
  // You can omit the block expression if there is only one line code.
  (x: Int) => x + 1
  // Name functions
  val func1 = (x: Int) => x + 1
  println(func1(23))
  // Functions may take multiple parameters.
  val func2 = (x: Int, y: Int) => x + y
  // Functions can take no parameters.
  val func3 = () => 34

  /**
    * Methods look and behave very similar to functions, but there are a few key differences between them.
    * The last expression in the body is the method's return value.
    */
  // Methods are defined with the def keyword, def is followed by name, parameters list, a return type, and a body. (you can omit block expression if there is only one line code)
  def metd1(x: Int, y: Int): Int = {
    x + y
  }

  // Methods can take multiple parameter list.
  def meth2(x: Int, y: Int)(n: Int): Int = {
    val tmp = x + y
    tmp * n
  }

  // Method can take no parameter list and no return type.
  def metd3() = System.getProperty("user.name")

  println(metd3())

  /**
    * Classes
    */
  val geeter = new Geeter("hello, ", "!")
  geeter.geet("scala")

  /**
    * Case classes
    */
  val p1 = new Point(12, 12)
  // You can instantiate case classes without 'new' keyword.
  val p2 = Point(12, 12)
  val p3 = new Point(12, 22)
  if (p1 == p2) {
    println("p1 and p2 is same point.")
  } else {
    println("p1 and p2 is not same point.")
  }
  if (p3 == p2) {
    println("p2 and p3 is same point.")
  } else {
    println("p2 and p3 is not same point.")
  }

  /**
    * Objects
    */
  // You can access an object by referring to its name.
  val n1 = IdFactory.create()
  val n2 = IdFactory.create()
  println(n1, " and ", n2)

  val geeterTraitDefaultImpl = new GeeterTraitDefaultImpl()
  geeterTraitDefaultImpl.geet("kitty")
}