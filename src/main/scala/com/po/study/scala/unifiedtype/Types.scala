package com.po.study.scala.unifiedtype

/**
  * Any is the supertype of all types. It defines certain universal methods such as equal, hashCode, and toString. Any has two direct subclasses: AnyVal and AnyRef.
  *
  * AnyVal represents value types. There are nine predefined value types and they are non-nullable: Double, Float, Long, Int, Short, Byte, Char, Unit, and Boolean.
  * Unit is value type that carries no meaningful information.
  *
  * AnyRef represents reference types.
  *
  * Nothing is a subtype of all types, also called the bottom type. There is no value that has type Nothing. A common use is to signal non-termination such as a thrown exception, program exit, or an infinite loop (i.e., it is the type of an expression which does not evaluate to a value, or a method that does not return normally).
  *
  * Null is a subtype of all reference types (i.e. any subtype of AnyRef). It has a single value identified by the keyword literal null. Null is provided mostly for interoperability with other JVM languages and should almost never be used in Scala code. We’ll cover alternatives to null later in the tour.
  *
  * Created by ZJ on 25/10/2018.
  */
class Types {

}
