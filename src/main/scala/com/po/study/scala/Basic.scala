package com.po.study.scala

import java.io.{File, PrintWriter}
import java.util.Date

/**
  * Created by ZJ on 15/11/2018.
  */
object Basic extends App {

  /**
    * 重复参数，可以传入多个参数；传入参数被编译成一个数组类型；但当传入数组类型参数需要后面接": _ *"
    */
  def f1(x: String*) = x.foreach(println)

  f1("ni", "hjao")
  val arr = Array("hello", "woo")
  f1(arr: _ *)

  /**
    * Curry 克里化，将多个入参转化成多个括号参数
    * 将file 和 op 分别放到一个小括号中实现克里化
    * 当每个小括号中只有一个参数时可以用"_"代替部分参数实现一个部分参数的初始化函数创建
    * 当小括号中只有一个参数时，可以用大括号替代小括号
    */
  def withPrintWriter(file: File)(op: PrintWriter => Unit) {
    val writer = new PrintWriter(file)
    try {
      op(writer)
    } finally {
      writer.close()
    }
  }

  val fi = new File("date.txt")
  withPrintWriter(fi) {
    writer => writer.println(new Date())
  }


}
