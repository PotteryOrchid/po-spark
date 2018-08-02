package com.po.study.higherfun

/**
  * Functions that accept functions.
  *
  * Created by ZJ on 02/08/2018.
  */
object DemoThree extends App {
  private def promotion(salaries: Seq[Double], function: Double => Double) = salaries map function

  def smallPromotion(seq: Seq[Double]) = promotion(seq, _ * 1.1)

  /**
    * 此处省略了 Math.log 函数的传入参数 也可写成 "Math.log _"，"_" 代表入参。
    *
    * @param seq
    * @return
    */
  def bigPromotion(seq: Seq[Double]) = promotion(seq, Math.log)

  println(smallPromotion(Seq(2, 10.1)))
  println(bigPromotion(Seq(2, 10.1)))
}
