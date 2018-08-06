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
  println(res) // 55
  println(numbers.foldLeft(10)((m, n) => m + n)) // 55
}
