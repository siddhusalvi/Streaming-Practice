package com.flutura.practice

import redis.clients.jedis.Jedis

import scala.collection.JavaConverters._

object RedisConn extends App {

  val properties = PropertyManager.getProperties()


  var jedis: Jedis =getRedisDB()
  jedis.select(11)
  val map = jedis.hgetAll("ABB_PROD_WELL_MAP").keySet()

print(map.toString)



  //Function to get redis database
  def getRedisDB(): Jedis ={
    jedis = new Jedis(properties.getProperty("redisHost"), properties.getProperty("redisPort").toInt)
    jedis.auth(properties.getProperty("redisPassword"))
    jedis.select(properties.getProperty("redisDbIndex","1").toInt)
    jedis
  }

}
