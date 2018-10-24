package com.po.study.scala.traits

/**
  * Created by ZJ on 01/08/2018.
  */
trait Iterator[T] {
  def hasNext: Boolean
  def next(): T
}
