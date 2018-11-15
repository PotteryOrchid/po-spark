package com.po.study.scala.basics

/**
  *
  * 除非以下情况的一种成立， 否则行尾被认为是一个分号:
  *1.疑问行由一个不能合法作为语句结尾的字结束，如句点或中缀操作符。
  *2.下一行开始于不能作为语句开始的字。
  *3.行结束于括号(...)或方框[...]内部，因为这些符号不可能容纳多个语句。
  *
  * 如果你希望把它作为一个语句x + y，你可以把它包裹在括 号里:
  * (x
  * + y)
  * 或者，你也可以把+放在行末。正是由于这个原因，当你在串接类似于+的中缀操作符，把操作符放在行尾而不是行头是普遍的 Scala 风格:
  * x+
  * y+
  * z
  *
  * Created by ZJ on 13/11/2018.
  */
object Semicolon {
  def main(args: Array[String]): Unit = {
    val x = "hello"
    val y = "world"
    val z = "ko"
    val res = x +
      y +
      z +
      z
    println(res)

    val s = 'nihao
    val n = 'nihao
    println(s == n)
  }

  def makeRowSeq(row: Int) =
    for (col <- 1 to 10) yield {
      val prod = (row * col).toString
      val padding = " " * (4 - prod.length)
      padding + prod
    }

  println(makeRowSeq(2))
}
