package com.po.study.scala

import com.po.study.scala.anyref.Helloworld
import org.scalatest.FunSuite;

/**
  * Created by ZJ on 27/07/2018.
  */
class HelloworldTest extends FunSuite {

  test("Helloworld.square") {
    assert(Helloworld.square(3) === 9)
  }

}
