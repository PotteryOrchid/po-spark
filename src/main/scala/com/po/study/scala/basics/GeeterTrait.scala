package com.po.study.scala.basics

/**
  * Traits are types that containing certain fields and methods. Multiple traits can be combined.
  *
  * Traits can also have default implementations.
  *
  * Created by ZJ on 25/10/2018.
  */
trait GeeterTrait {
  def geetDefault(name: String): Unit

  def geet(name: String): Unit = {
    println(name)
  }
}
