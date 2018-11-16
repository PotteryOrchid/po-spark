package com.po.study.scala

/**
  * 闭包
  *
  * Created by ZJ on 15/11/2018.
  */
object Closure {
  def main(args: Array[String]): Unit = {
    def closure(x: Int) = (y: Int) => x + y

    val a = closure(23)
    val b = closure(22)

    println(a(1))
    println(b(1))
    println(closure(2)(221))
  }
}
