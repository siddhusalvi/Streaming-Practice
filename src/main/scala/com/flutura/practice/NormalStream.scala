package com.flutura.practice

import org.apache.kafka.clients.consumer.ConsumerRecord
import org.apache.kafka.common.serialization.StringDeserializer
import org.apache.log4j.LogManager
import org.apache.spark.sql.SparkSession
import org.apache.spark.streaming.dstream.DStream
import org.apache.spark.streaming.kafka010.{KafkaUtils, LocationStrategies}
import org.apache.spark.streaming.{Seconds, StreamingContext}
import org.apache.spark.streaming.kafka010._

class NormalStream {  //Getting properties
  val properties = PropertyManager.getProperties()

  //Creating sparksession
  val spark = SparkSession.builder()
    .master(properties.getProperty("spark-master"))
    .appName(properties.getProperty("app-name"))
    .getOrCreate()

  val logger = LogManager.getLogger(properties.getProperty("logger"))
  logger.info("created SparkSession")

  val duration = Seconds(properties.getProperty("streamDuration").toLong)

  val streamingContext = new StreamingContext(spark.sparkContext,duration)


  val kafkaConfig = Map[String, Object](
    "bootstrap.servers" -> properties.getProperty("kafkaHost"),
    "key.deserializer" -> classOf[StringDeserializer],
    "value.deserializer" -> classOf[StringDeserializer],
    "group.id" -> properties.getProperty("consumerGroup"),
    "auto.offset.reset" -> properties.getProperty("kafkaOffset"),
    "enable.auto.commit" -> "true"//properties.getProperty("kafkaCommitFlag")
  )

  val kafkaTopics = Array(properties.getProperty("kafkaTopic"))
  val kafkaRawStream = KafkaUtils.createDirectStream[String, String](
    streamingContext,
    LocationStrategies.PreferConsistent,
    ConsumerStrategies.Subscribe[String, String](kafkaTopics, kafkaConfig)
  )
  logger.info("Kafka Consumer setup complete")

  //Function to Start kafka consumer
  def start(): Unit = {
    logger.info("STARTED : Kafka-Spark Consumer")
    try {

       kafkaRawStream map (streamRawRecord =>
         StreamHandler.processStream(streamRawRecord.value))


//      kafkaRawStream.foreachRDD(
//        rdd => rdd.map(
//          records => println(records.value())
//        )
//      )

//      kafkaRawStream.foreachRDD(_.foreach(r =>println(r.value())))

//      kafkaRawStream.foreachRDD{
//        rdd =>{
//          rdd.map(StreamHandler.processStream(_))
//        }
//      }


//      kafkaRawStream.foreachRDD(
//        rdd => rdd.foreach(record => StreamHandler.processStream(record.value())
//        )
//      )
//
//      //Commit offset
//      kafkaRawStream.foreachRDD { rdd =>
//        val offsetRanges = rdd.asInstanceOf[HasOffsetRanges].offsetRanges
//        kafkaRawStream.asInstanceOf[CanCommitOffsets].commitAsync(offsetRanges)
//      }

      //Starting kafka
      streamingContext.start()
      streamingContext.awaitTermination()

    } catch {
      case exception: Exception => logger.error(exception.printStackTrace())
    }
  }

  //Closing the connection
  def close(): Unit = {
    if(streamingContext != null){
      streamingContext.stop()
    }
  }

 def dStreamOperation(data:String):Unit={
    print(data)
  }

}



