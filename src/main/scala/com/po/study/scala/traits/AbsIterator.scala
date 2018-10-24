package com.po.study.scala.traits

/**
  * Created by ZJ on 02/08/2018.
  */
abstract class AbsIterator {
  type T

  def hasNext: Boolean

  def next(): T
}
