package com.po.study.scala.basics

/**
  * Classes: You can define classes with the class keyword followed by it's name and constructor parameter.
  *
  * Created by ZJ on 25/10/2018.
  */
class Geeter(prefix: String, suffix: String) {
  def geet(name: String): Unit = {
    println(prefix + name + suffix)
  }
}
