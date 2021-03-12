package com.flutura.practice

import org.json.JSONObject

object jsonEXAMPLES extends App {
  val properties = PropertyManager.getProperties()
  val str =properties.getProperty("json")

  val obj =  new JSONObject(str)

  val keys = obj.keys()
  println(obj.toString)

  println(String.format("{\"\"}"))
}
