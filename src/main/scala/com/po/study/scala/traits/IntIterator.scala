package com.po.study.scala.traits

/**
  * Created by ZJ on 01/08/2018.
  */
class IntIterator(no: Int) extends Iterator[Int] {
  private var current = 0

  override def hasNext: Boolean = current < no

  override def next(): Int = {
    if (hasNext) {
      val t = current
      current += 1
      t
    } else 0
  }
}

object IntIterator {
  def main(args: Array[String]): Unit = {
    val i = new IntIterator(7)

    while (i.hasNext) println(i.next())
  }
}
