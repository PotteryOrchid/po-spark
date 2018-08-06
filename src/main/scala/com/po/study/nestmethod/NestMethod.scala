package com.po.study.nestmethod

/**
  * Nest method: 递归
  * Created by ZJ on 02/08/2018.
  */
object NestMethod extends App {
  def factorial(x: Int) = {

    /**
      * 定义阶乘函数
      *
      * @param x
      * @param acc
      * @return
      */
    def fact(x: Int, acc: Int): Int = {
      if (x <= 1) acc else fact(x - 1, x * acc)
    }

    /**
      * 调用上面定义的阶乘函数
      */
    fact(x, 1)
  }

  println(factorial(2))
  println(factorial(3))
}
