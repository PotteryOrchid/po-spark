package com.po.study.spark.streaming

import org.apache.spark.sql.Dataset
import org.apache.spark.sql.functions.{struct, to_json}
import org.apache.spark.sql.types._

/**
  * Created by ZJ on 08/12/2018.
  */
object Test {

  def main(args: Array[String]): Unit = {

    val ds: Dataset[MsgTest] = KafkaPkg.readSchema("test_zj", "zj", CustomStruct.schema)

    //    val dss: Dataset[MsgTest] = KafkaPkg.readBatch("test_zj", "zj")

    KafkaPkg.write(ds, "test_w") {
      // Customized func
      df => {
        val s = df.groupBy("value.name", "value.age").count()
        s.select(s.col("name").alias("key"), to_json(struct("*")).cast(StringType).alias("value"))
      }
    }

    Session.getSpark.streams.awaitAnyTermination()
  }
}
