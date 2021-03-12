package com.flutura.practice

import redis.clients.jedis.Jedis

import scala.collection.JavaConverters._

object RedisConn extends App {

  val properties = PropertyManager.getProperties()


  var jedis: Jedis =getRedisDB()
  jedis.select(11)
  val map = jedis.hgetAll("ABB_PROD_WELL_MAP").keySet()

print(map.toString)


//  var activityAttributeList: Map[String, Set[String]] = _
//
//  val redisConn = jedis.pipelined()
//  redisConn.select(properties.getProperty("redisDbIndex","1").toInt)
//  // Read topic list
//  val pipeResponse:List[String] = redisConn.lrange(properties.getProperty("list-key"), 0L, -1L)
//  val keyResponse = redisConn.keys("ACTIVITY_*")
//  redisConn.sync()
//  // Fetch attribute keys
//  activityAttributeList = keyResponse.get().asScala.map(assetKey => {
//    (assetKey, jedis.smembers(assetKey).asScala.toSet)
//  }).toMap


  //Function to get redis database
  def getRedisDB(): Jedis ={
    jedis = new Jedis(properties.getProperty("redisHost"), properties.getProperty("redisPort").toInt)
    jedis.auth(properties.getProperty("redisPassword"))
    jedis.select(properties.getProperty("redisDbIndex","1").toInt)
    jedis
  }

}
