package com.po.study.higherfun

/**
  * Created by ZJ on 02/08/2018.
  */
object DemoFour extends App {
  def urlBuilder(ssl: Boolean, domainName: String): (String, String) => String = {
    val schema = if (ssl) "https://" else "http://"
    (endpoint: String, query: String) => s"$schema$domainName/$endpoint?$query"
  }

  val domainName = "www.po.com"

  def getUrl = urlBuilder(true, domainName)

  val endpoint = "pictures"
  val query = "id=10"
  val url = getUrl(endpoint, query)
  println(url)
}
