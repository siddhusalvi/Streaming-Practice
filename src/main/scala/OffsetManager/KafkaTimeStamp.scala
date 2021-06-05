package OffsetManager

import org.apache.kafka.common.serialization.StringDeserializer
import org.apache.spark.sql.SparkSession
import org.apache.spark.streaming.kafka010.ConsumerStrategies.Subscribe
import org.apache.spark.streaming.kafka010.LocationStrategies.PreferConsistent
import org.apache.spark.streaming.kafka010._
import org.apache.spark.streaming.{Milliseconds, StreamingContext}

import java.util.Date

object KafkaTimeStamp {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder()
      .master("local[2]")
      .appName("KafkaTime")
      .getOrCreate()

    val sparkContext = spark.sparkContext

    val ssc = new StreamingContext(sparkContext, Milliseconds(1000))

    val kafkaParams = Map[String, Object](
      "bootstrap.servers" -> "localhost:9092",
      "key.deserializer" -> classOf[StringDeserializer],
      "value.deserializer" -> classOf[StringDeserializer],
      "group.id" -> "testgroup",
      "auto.offset.reset" -> "earliest",
      "enable.auto.commit" -> (false: java.lang.Boolean)
    )

    val topics = Array("test")

    val stream = KafkaUtils.createDirectStream[String,String](ssc,PreferConsistent,Subscribe[String,String](topics,kafkaParams))

    val raws = stream.foreachRDD{
      rdd =>{
        rdd.foreach {
          record => println(new Date(record.timestamp())+" "+record.timestamp()+" "+record.value())
        }
      }
    }

    ssc.start()
    ssc.awaitTermination()


  }
}
