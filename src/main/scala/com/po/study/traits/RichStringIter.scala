package com.po.study.traits

/**
  * Created by ZJ on 02/08/2018.
  */
object RichStringIterApp extends App {

  class RichStringIter extends StringIterator("Scala") with RichIterator

  val richStringIter = new RichStringIter

  richStringIter foreach println
}
