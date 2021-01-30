package com.flutura.practice


import org.apache.kafka.clients.consumer.ConsumerRecord
import org.apache.log4j.LogManager
import org.json.JSONObject

class SensorParamters{
  var id:String=null
  var v:String=null
  var t:String=null
}


object StreamHandler {

  //Getting properties
  val properties = PropertyManager.getProperties()


  val logger = LogManager.getLogger(properties.getProperty("logger"))
  logger.info("created SparkSession")

  val idKey = "id"
  val vKey = "v"
  val tKey = "t"

  //Function to process stream
  def processStream(streamData:String): Unit = {
    if(streamData ==null){
      print("Null consumer record")
    }else {
      print(streamData)
    }
  }

  //Function to parse json
  def parseJson(jsonStr: String): Unit = {
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
      }

    } catch {
      case exception: Exception => println(exception.printStackTrace())
    }
  }

  //Function to convert JSON into object
  def convertToSensorObject(record: JSONObject): SensorParamters = {

    var dataObj = new SensorParamters()
    try {
      if (record.has(idKey)) {
        dataObj.id = record.getString(idKey)
      }

      if (record.has(vKey)) {
        dataObj.v = record.getDouble(vKey).toString
      }
      if (record.has(tKey)) {
        dataObj.t = record.getBigInteger(tKey).toString
      }
    } catch {
      case exception: Exception => print(exception.printStackTrace())
    }
    dataObj
  }



}
