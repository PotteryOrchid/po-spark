package com.po.study.scala.basics

/**
  * Created by ZJ on 25/10/2018.
  */
class GeeterTraitImpl(prefix: String, suffix: String) extends GeeterTrait {
  override def geetDefault(name: String): Unit = ???

  override def geet(name: String): Unit = {
    println(prefix + name + suffix)
  }
}
