package com.flutura.practice

import org.apache.kafka.clients.consumer.KafkaConsumer
import org.apache.kafka.common.TopicPartition

import java.util
import scala.collection.JavaConverters.iterableAsScalaIterableConverter
import scala.reflect.internal.util.Collections

object Nmessages extends App {

  val properties = PropertyManager.getProperties()
  val consumer: KafkaConsumer[String, String] = new KafkaConsumer[String, String](properties)

  val duration = properties.getProperty("duration").toInt

  val kafkaTopicName = util.Arrays.asList(properties.getProperty("topic"))

  val partitions = consumer.partitionsFor(properties.getProperty("topic"))

  val count = partitions.size()
  val partion = partitions.get(0)

  //  println(partion)
  //  var lst :List[org.apache.kafka.common.TopicPartition] = null
  //  var i = 0;
  //  while(  i < partitions.size()){
  //    val obj = partitions.get(i).partition()
  //    val topicPartition = new TopicPartition("test",0)
  //    lst += topicPartition
  //    i += 1
  //  }
  //

  import org.apache.kafka.common.TopicPartition
  import java.util

  val topicPartition = new TopicPartition("test", 0)

  val topics = util.Arrays.asList(topicPartition)

  consumer.assign(topics)

  consumer.seekToEnd(topics)

  val current = consumer.position(topicPartition)

  consumer.seek(topicPartition, current - 20)


  while (true) {

    val record = consumer.poll(1000).asScala

    record.foreach {
      line =>
        println(line.value())
    }

  }


//  kafkaConsumer.seekToEnd(partitionList);
//  long endPosition = kafkaConsumer.position(topicPartiton);
//  long recentMessagesStartPosition = endPosition - maxMessagesToReturn;
//  kafkaConsumer.seek(topicPartition, recentMessagesStartPosition);

}