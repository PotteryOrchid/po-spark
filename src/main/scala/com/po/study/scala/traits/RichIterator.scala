package com.po.study.scala.traits

/**
  * Created by ZJ on 02/08/2018.
  */
trait RichIterator extends AbsIterator {
  /**
    * 该处 foreach 参数里定义的是一个函数 f 输入类型是范性 输出类型是Unit，
    * foreach函数实现里 f 函数 传入的参数是 next()，没有定义 f 函数的输出。
    */
  def foreach(f: T => Unit) = while (hasNext) f(next())
}
