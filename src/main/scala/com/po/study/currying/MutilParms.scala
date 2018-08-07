package com.po.study.currying

/**
  * Created by ZJ on 05/08/2018.
  */
object MutilParms extends App {

  /**
    * foldLeft:第一个参数是起始数值，后面定义的 ((m, n) => m + n) 定义的是累加函数，将集合中的所有数累加后加上初始值。
    */
  val numbers = List(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
  val res = numbers.foldLeft(0)((m, n) => m + n)

  // Print result
  println(res) // 55

  // Set init value
  println(numbers.foldLeft(10)((m, n) => m + n)) // 65

  // Scala type
  println("res 3: " + numbers.foldLeft(10)(_ + _)) // 65


  /**
    * 定义一个新的函数，并预定义函数，函数实现未定义
    */
  // foldLeft参数 z 为空列表 List[Int]()，"_" 代表将要定义的函数
  val numberFunc = numbers.foldLeft(List[Int]()) _
  // xs 为上面传入的空列表，":+" 表示在列表尾部放入元素，"xs :+ x * x" 在列表尾部放入"x*x"
  val squares = numberFunc((xs, x) => xs :+ x * x)
  println(squares.toString()) // List(1, 4, 9, 16, 25, 36, 49, 64, 81, 100)

  val cubes = numberFunc((xs, x) => xs :+ x * x * x)
  println(cubes.toString()) // List(1, 8, 27, 64, 125, 216, 343, 512, 729, 1000)

  /**
    * 语法糖为方法的符号表示，可以理解为简便表达方式。
    * foldLeft 方法语法糖 /:
    * foldRight 方法语法糖 :\
    */
  val n = (2 /: numbers) (_ + _)
  println(n)
  val m = (numbers :\ 1) (_ + _)
  println(m)
}
