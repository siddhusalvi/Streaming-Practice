package com.flutura.practice

import org.apache.kafka.clients.consumer.KafkaConsumer

import java.util
import scala.collection.JavaConverters.iterableAsScalaIterableConverter
import scala.reflect.internal.util.Collections

object Nmessages extends App {

  val properties = PropertyManager.getProperties()
  val consumer: KafkaConsumer[String, String] = new KafkaConsumer[String, String](properties)

  val duration = properties.getProperty("duration").toInt

  val kafkaTopicName = util.Arrays.asList(properties.getProperty("topic"))

  val partitions = consumer.partitionsFor(properties.getProperty("topic"))
  var i = 0;
  while(  i < partitions.size()){
    val obj = partitions.get(i).partition()
    println(obj.toString)
    i += 1
  }

  import org.apache.kafka.common.TopicPartition
  import java.util

  import org.apache.kafka.common.TopicPartition
  import java.util

  val assignTopics = new util.ArrayList[TopicPartition]
  assignTopics(partition, assignTopics)
  consumer.assign(assignTopics)
  consumer.seekToEnd(assignTopics)



//  import java.util
//
//  consumer.subscribe(util.Arrays.asList(kafkaTopicName))
//
//  consumer.seekToEnd(partitions)
//  val recentMessagesStartPosition = endPosition - maxMessagesToReturn
//  var i = 0
//  kafkaConsumer.seek(topicPartition, recentMessagesStartPosition)
//  Subscribing the topics
//  consumer.subscribe(lst)
//
//  while (true) {
//
//    val record = consumer.poll(1000).asScala
//
//    record.foreach {
//      line =>
//        println(line.value())
//    }
//
//  }

}
