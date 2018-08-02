package com.po.study.higherfun

/**
  * Coercing methods into functions
  * (将方法强制为函数)
  * Created by ZJ on 02/08/2018.
  */
case class DemoTwo(temperatures: Seq[Double]) {
  private def convertCtoF(temp: Double) = temp * 1.8 + 32

  def forecastInFahrenheit: Seq[Double] = temperatures map convertCtoF
}

object DemoTwo extends App {
  val temps = new DemoTwo(Seq(2.3, 34.2))
  println(temps.forecastInFahrenheit)
}
