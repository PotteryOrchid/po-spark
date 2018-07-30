package com.po.anyref

/**
  * Created by ZJ on 30/07/2018.
  */
// 构造函数：var 为可变参数，val 为不可变参数。当构造参数既不被val也不被var修饰时，该参数只能在类内部使用，不能在外部直接调用。
class Person(var name: String = "Scala", val gender: String = "男", passwd: String = "pass") extends SayTrait {

  /**
    * Scala default primary is pubic, and general getter and setter for public variables and parameters.
    */
  var cmnt: String = null

  private var number: Int = 0

  def getGender = gender

  /**
    * Notice the special syntax for the setters: the method has _= appended to the identifier of the getter and the parameters come after.
    * 特殊的set方法，可以实现校验。类会同时生成：setNumber_(int) 和 setNumber(int) 方法，只有 setNumber_=(int) 可以被调用。
    */
  def setNumber_=(newValue: Int) = {
    if (newValue < number) number = newValue else printWarning
  }

  private def printWarning = println("WARNING: Out of bounds")

}

object Person {
  var per = new Person();
  per.cmnt = "hello"
}
