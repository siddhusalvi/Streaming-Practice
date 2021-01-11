package com.flutura.practice

import org.apache.log4j.LogManager
import org.apache.spark.sql.{DataFrame, SparkSession}
import org.json.JSONObject

import scala.collection.mutable.ListBuffer

class SensorDataClass {
  var id: String = " "
  var v: String = " "
  var t: String = " "
}


class KafkaReceiver {

  //Getting properties
  val properties = PropertyManager.getProperties()

  //Creating sparksession
  val spark = SparkSession.builder()
    .master(properties.getProperty("spark-master"))
    .appName(properties.getProperty("app-name"))
    .getOrCreate()

  val logger = LogManager.getLogger(properties.getProperty("logger"))
  logger.info("created SparkSession")


  val idKey = "id"
  val vKey = "v"
  val tKey = "t"


  val kafkaSource = properties.getProperty("kafka-source")
  val kafkaTopic = properties.getProperty("kafka-topic")
  val kafkaServers = properties.getProperty("kafka-host") + ":" + properties.getProperty("kafka-port")
  val kafkaOffset = properties.getProperty("kafka-offset")
  var df: DataFrame = null

  logger.info("Done setup properties for kafka consumer")

  //Starting consumer
  def start(): Unit = {

    logger.info("Starting Consumer")

    try {
      //getting streaming dataframe
      df = getStreamDF()

      df.rdd.map(row => parseFunction(row.getString(0)))


      def myFunc: (String => String) = { s => s.toLowerCase }

      def parseFunction: (String => Unit) = {
        str => {
          var listBuffer: ListBuffer[SensorDataClass] = null

          try {
            new JSONObject(str)
            var listBuffer = parseJson(str)
          } catch {
            case exception: Exception => {
              logger.error(exception.printStackTrace())
            }
          }
          if (listBuffer != null) {
            listBuffer.foreach(obj => if (obj != null) {
              println(obj.toString)
            })
          }

        }

      }


      //waiting for termination
      spark.streams.awaitAnyTermination()

    }


    logger.info("Initialized JsonHandler object")

  }
    //Function to parse json
    def parseJson(jsonStr: String): ListBuffer[SensorDataClass] = {
      var objList = new ListBuffer[SensorDataClass]
      try {

        //Getting single json object
        val jsonObj = new JSONObject(jsonStr)

        //Getting object keys
        val keys = jsonObj.keys()

        //Lopping through keys
        while (keys.hasNext) {
          val key = keys.next()

          //Fetching single JSON object
          val singleRecord = convertToSensorObject(jsonObj.getJSONObject(key))
          objList.append(singleRecord)
        }
      } catch {
        case exception: Exception => println(exception.printStackTrace())
      }
      objList
    }

    //Function to convert JSON into object
    def convertToSensorObject(record: JSONObject): SensorDataClass = {

      var dataObject = new SensorDataClass()
      try {
        if (record.has(idKey)) {
          dataObject.id = record.getString(idKey)
        }

        if (record.has(vKey)) {
          dataObject.v = record.getDouble(vKey).toString
        }
        if (record.has(tKey)) {
          dataObject.t = record.getBigInteger(tKey).toString
        }
      } catch {
        case exception: Exception => logger.error(exception.printStackTrace())
      }
      dataObject
    }

    //Function to get kafka Stream
    def getStreamDF(): DataFrame = {
      val streamDataFrame = spark.readStream
        .format(kafkaSource)
        .option("kafka.bootstrap.servers", kafkaServers)
        .option("subscribe", kafkaTopic)
        .option("startingOffsets", kafkaOffset)
        .load()
        //casting kafka value to the string
        .selectExpr("CAST(value AS STRING)")
      streamDataFrame
    }

  }
