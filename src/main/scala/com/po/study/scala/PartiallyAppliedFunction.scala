package com.po.study.scala

/**
  * 偏应用函数
  * Created by ZJ on 15/11/2018.
  */
object PartiallyAppliedFunction extends App {
  def sum(a: Int, b: Int, c: Int) = a + b + c

  val f = sum(2, _: Int, 3)
  Array.apply(23).foreach(println)
}
