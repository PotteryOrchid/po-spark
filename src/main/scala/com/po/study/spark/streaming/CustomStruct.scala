package com.po.study.spark.streaming

import org.apache.spark.sql.types._

/**
  * Created by ZJ on 11/12/2018.
  */
object CustomStruct {
  val schemaTest = StructType(
    StructField("name", StringType, nullable = true) ::
      StructField("age", IntegerType, nullable = true) ::
      StructField("address",
        ArrayType(
          StructType(
            StructField("isHave", BooleanType, nullable = true) ::
              StructField("home", StringType, nullable = true) ::
              StructField("com", ArrayType(StringType, containsNull = true), nullable = true) :: Nil
          )), nullable = false) :: Nil)

  val schema = StructType(
    StructField("name", StringType, nullable = true) ::
      StructField("age", IntegerType, nullable = true) ::
      StructField("address",
        StructType(
          StructField("isHave", BooleanType, nullable = true) ::
            StructField("home", StringType, nullable = true) ::
            StructField("com", ArrayType(StringType, containsNull = true), nullable = true) :: Nil
        ), nullable = false) :: Nil)
}
