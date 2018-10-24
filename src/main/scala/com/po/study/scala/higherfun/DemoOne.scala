package com.po.study.scala.higherfun

/**
  * Created by ZJ on 02/08/2018.
  */
object DemoOne extends App {
  /**
    * Demo one: Simple demo.
    */
  val salaries = Seq(20000, 18000, 30000)
  val doubleSalary = (x: Int) => x * 2
  // map 接受参数是函数
  val newSalaries = salaries map doubleSalary
  println("Demo one: " + newSalaries)

  /**
    * Demo two: Make the function anonymous(匿名).
    */
  val nSalaries = salaries map (x => x * 2)
  println("Demo two: " + nSalaries)

  /**
    * Demo three: more scala idiomatic(fu he xi guan) way.
    */
  val threeSalaries = salaries map (_ * 2)
  println("Demo three: " + threeSalaries)
}
