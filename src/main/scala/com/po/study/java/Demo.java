package com.po.study.java;

/**
 * Created by ZJ on 13/11/2018.
 */
public class Demo {

  public static void main(String[] args) {

    String str3 = new String("hello Str");
    str3.intern();
    String str1 = "hello Str";

    System.out.println(str1 == str3.intern());
  }

}
