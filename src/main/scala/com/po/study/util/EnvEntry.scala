package com.po.study.util

/**
  * Define an env element entry
  *
  * @param v    : an environmental variable
  * @param defV : default value to an environmental variable
  */
class EnvEntry(var v: String, val defV: String) extends Serializable {
  /**
    * Set an exist environmental variable to a new value
    *
    * @param v : a new value
    */
  def setV(v: String): Unit = this.v = v

  /**
    * Get a value of an environmental variable, match the first one in order { value, default value, null }
    *
    * @return an environmental variable value
    */
  def getV: String = if (v == null) {
    if (defV == null) null else defV
  } else v

}
