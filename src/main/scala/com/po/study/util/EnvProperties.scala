package com.po.study.util

import java.util.Properties

import scala.collection.mutable

/**
  * Generate a singleton application env configurations instance, this instance provides essential methods for accessing app configured value
  * and loads local server environmental variables automatically
  */
object EnvProperties {

  private val properties = new Properties()

  private val env = new mutable.HashMap[String, EnvEntry]()

  try {
    synchronized({
      // load application properties file and generate properties instance
      val inputStream = ClassLoader.getSystemResourceAsStream("application.properties")
      properties.load(inputStream)

      // get app variables need to be update according to server env
      val keys = properties.propertyNames()
      while (keys.hasMoreElements) {
        deal(keys.nextElement.toString)(addValue)
      }

      // load environmental variables on local server
      loadEnv()

      // replace application variables with local server env variables
      val keys0 = properties.propertyNames()
      while (keys0.hasMoreElements) {
        val k = keys0.nextElement.toString
        deal(k)(repValue(k))
      }
    })
  } catch {
    case e: Exception => e.printStackTrace()
  } finally {}

  /**
    * Get property value by key.
    *
    * @param k : key in properties
    * @return property value by key
    */
  def getProperty(k: String): String = {
    properties.getProperty(k)
  }

  /**
    * Generate a function to deal with env variables in properties
    *
    * @param k    : property key
    * @param func : define a function which to be used to deal with env variables configurations
    */
  private def deal(k: String)(func: String => Any): Unit = {
    val pattern = """\$\{(.+?)\}""".r
    pattern.findAllMatchIn(properties.getProperty(k)).foreach(e => func(e.matched))
  }

  /**
    * Add app env variables to env map
    *
    * @param m : matched env variables configurations in property value
    */
  private def addValue(m: String): Unit = {
    val entry: Array[String] = m.replace("""${""", "").replace("""}""", "").split(":")

    if (entry.length == 1) {
      env.put(entry(0), new EnvEntry(null, null))
    } else if (entry.length == 2) {
      env.put(entry(0), new EnvEntry(null, entry(1)))
    } else {
      val v = new mutable.StringBuilder()
      for (i <- 1 until entry.length - 1) {
        v.append(entry(i))
        if (i != entry.length - 1) v.append(":")
      }
      env.put(entry(0), new EnvEntry(null, v.toString))
    }
  }

  /**
    * Replace app env variables with local server env variables
    *
    * @param k : key in properties
    * @param m : matched env variables configurations in property value
    */
  private def repValue(k: String)(m: String): Unit = {
    val v = properties.get(k).toString.replace(m, env(m.replace("""${""", "").replace("""}""", "").split(":")(0)).getV)
    properties.setProperty(k, v)
  }

  /**
    * Load local server env variables
    */
  private def loadEnv(): Unit = env.foreach(entry => {
    entry._2.setV(System.getenv(entry._1))
  })
}
