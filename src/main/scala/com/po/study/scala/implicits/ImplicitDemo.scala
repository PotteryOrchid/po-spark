package com.po.study.scala.implicits

/**
  * When the compiler sees the need for an implicit, either because you are calling a method which does not exist on the object's class,
  * or because you are calling a method that requires an implicit parameter, it will search for an implicit that will fit the need.
  *
  * 1. First look in current scope
  * a. Implicits defined in current scope
  * b. Explicit imports
  * c. wildcard imports
  * d. Same scope in other files
  *
  * 2. Now look at associated types in
  * a. Companion objects of a type
  * b. Companion objects of type parameters types
  * c. Outer objects for nested types
  * d. Other dimensions
  *
  * Created by ZJ on 07/08/2018.
  */
class ImplicitDemo {

}

object ImplicitDemo extends App {
  /**
    * String does not support the method map, but StringOps does, and there's an implicit conversion from String to StringOps available (see implicit def augmentString on Predef).
    */
  println("abd".map(_.toInt))

  /**
    * The other kind of implicit is the implicit parameter. These are passed to method calls like any other parameter, but the compiler tries to fill them in automatically.
    *
    * When you pass in an Int, it's going to look for an implicit object that is an Integral[Int] and it finds it in scala.math.Numeric.
    * You can look at the source code of scala.math.Numeric, where you will find it.
    *
    * @param t
    * @param integral
    * @tparam T
    */
  def foo[T](t: T)(implicit integral: Integral[T]): Unit = {
    // rem 取余数
    println(integral.rem(t, t))
  }

  foo(23)

  /**
    * The method getIndex can receive any object, as long as there is an implicit conversion available from its class to Seq[T].
    * Because of that, a String can be passed to getIndex, and it will work.
    * Behind the scenes, the compiler changes seq.IndexOf(value) to conv(seq).indexOf(value).
    *
    * @param seq
    * @param value
    * @param conv
    * @tparam T
    * @tparam CC
    * @return
    */
  def getIndex[T, CC](seq: CC, value: T)(implicit conv: CC => Seq[T]) = seq.indexOf(value)

  println(getIndex("abc", 'b'))

  /**
    * 3*(2^3) = 3*(2*2*2) = 24
    * 1、 "<< n" 代表左移 n 位，即 2 的 n 次方。
    * 2、"m << n" 代表 m 乘以 2 的 n 次方。
    */
  println(2 << 3)

}
