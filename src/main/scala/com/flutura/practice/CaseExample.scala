package com.flutura.practice

import org.json.JSONObject
import redis.clients.jedis.Jedis


object CaseExample extends App {

  val properties = PropertyManager.getProperties()

//  val alarmTagList = List("TAG.Warning4")

  val obj = new JSONObject("{\"id\":\"TAG.Warning4\",\"v\":1.0,\"q\":true,\"t\":1604386843864}")

  var jedis: Jedis =getRedisDB()

  val alarmTagList = jedis.lrange(properties.getProperty("list-key"), 0L, -1L)

  println(alarmTagList.toString)

  val parameter_tag_ID = obj.getString("id")

  print(parameter_tag_ID)


  var event_type:String = null
  if(alarmTagList.contains(parameter_tag_ID) || alarmTagList.contains(parameter_tag_ID.toLowerCase)){
    println("alarm found"+obj.get("v"))
    if(!(obj.getDouble("v").toString.equals("0") || obj.getDouble("v").toString.equals("1"))){
      throw new Exception("Invalid Alarm value")
    }else{
      event_type = "ALARM"
    }
  }else{event_type="LOG"}

  println(event_type)
  println(1.0.toString.equals("1"))



//  Function to get redis database
  def getRedisDB(): Jedis ={
    jedis = new Jedis(properties.getProperty("redisHost"), properties.getProperty("redisPort").toInt)
    jedis.auth(properties.getProperty("redisPassword"))
    jedis.select(properties.getProperty("redisDbIndex","1").toInt)
    jedis
  }


}
