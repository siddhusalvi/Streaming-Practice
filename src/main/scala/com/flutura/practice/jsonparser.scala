package com.flutura.practice
import org.json.{JSONArray, JSONObject}

object jsonparser extends App {
  val jsonStr = "[ { \"EquipmentId\" : \"WELLS_GM\", \"TagId\" : \" WELLS_GM_PRSTAXIN\", \"Value\" : \"250.1744\", \"Type\" : \"AI\", \"Units\" : \"psi\", \"Timestamp\" : \"2021-03-28T04:57:31.85Z\", \"Status\" : null, \"UnixTimeMilliseconds\" : 1616907451850, \"UnixTimeSeconds\" : 1616907451, \"UtcTimestamp\" : \"2021-03-28T04:57:31.85Z\" }, { \"EquipmentId\" : \"WELLS_GM\", \"TagId\" : \"WELLS_GM_PRSTAXIN\", \"Value\" : \"250.4814\", \"Type\" : \"AI\", \"Units\" : \"psi\", \"Timestamp\" : \"2021-03-28T05:07:31.899Z\", \"Status\" : null, \"UnixTimeMilliseconds\" : 1616908051899, \"UnixTimeSeconds\" : 1616908051, \"UtcTimestamp\" : \"2021-03-28T05:07:31.899Z\" }, { \"EquipmentId\" : \"WELLS_GM\", \"TagId\" : \"WELLS_GM_PRSTAXIN\", \"Value\" : \"250.7868\", \"Type\" : \"AI\", \"Units\" : \"psi\", \"Timestamp\" : \"2021-03-28T05:17:31.976Z\", \"Status\" : null, \"UnixTimeMilliseconds\" : 1616908651976, \"UnixTimeSeconds\" : 1616908651, \"UtcTimestamp\" : \"2021-03-28T05:17:31.976Z\" }, { \"EquipmentId\" : \"WELLS_GM\", \"TagId\" : \"WELLS_GM_PRSTAXIN\", \"Value\" : \"251.0892\", \"Type\" : \"AI\", \"Units\" : \"psi\", \"Timestamp\" : \"2021-03-28T05:27:31.939Z\", \"Status\" : null, \"UnixTimeMilliseconds\" : 1616909251939, \"UnixTimeSeconds\" : 1616909251, \"UtcTimestamp\" : \"2021-03-28T05:27:31.939Z\" } ]"
  val JSONObject = "{\"EquipmentId\":\"DIMO_N8168\",\"TagId\":\"HOUSTON.UIS:DIMO_N8168_VGT\",\"Value\":\"96.2\",\"Type\":\"AI\",\"Units\":\"MCF\",\"Timestamp\":\"2020-10-19T20:49:36.034Z\",\"Status\":null,\"UnixTimeMilliseconds\":1603140576034,\"UnixTimeSeconds\":1603140576,\"UtcTimestamp\":\"2020-10-19T20:49:36.034Z\"}"
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
