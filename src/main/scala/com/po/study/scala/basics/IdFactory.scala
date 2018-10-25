package com.po.study.scala.basics

/**
  * Objects are single instances of their own definitions. You can think of them as singletons of their own classes.
  *
  * You can define objects with the object keyword.
  * Created by ZJ on 25/10/2018.
  */
object IdFactory {
  private var n: Int = 0

  def create(): Int = {
    n += 1
    n
  }
}
