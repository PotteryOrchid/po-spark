package com.po.study.traits

/**
  * Created by ZJ on 02/08/2018.
  */
class StringIterator(s: String) extends AbsIterator {
  private var i: Int = 0

  type T = Char

  def hasNext: Boolean = i < s.length

  def next(): Char = {
    val ch = s charAt i
    i += 1
    ch
  }
}
