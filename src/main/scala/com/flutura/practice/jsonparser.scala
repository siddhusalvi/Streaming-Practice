package com.flutura.practice
import org.json.{JSONArray, JSONObject}

object jsonparser extends App {
  val jsonStr = ""
  val JSONObject = ""
  var jsonArray:JSONArray = new JSONArray()
  try{
//    jsonArray.put(new  JSONObject(JSONObject))
    jsonArray.put(new JSONObject(jsonStr))
  }catch {
    case _:Throwable =>jsonArray.putAll(new JSONArray(jsonStr))
  }
  val objects = (0 until jsonArray.length).map(jsonArray.getJSONObject)

  objects.foreach(println(_))

}
