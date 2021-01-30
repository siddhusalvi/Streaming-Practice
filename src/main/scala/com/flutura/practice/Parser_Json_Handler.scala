package com.flutura.practice

import org.json.JSONObject

import java.io.File
import scala.io.Source
import org.json.JSONArray

import java.nio.charset.StandardCharsets
case class Data(q: Boolean, t: Long, v: Double, id: String)

/*

    topic: String,
    customer: String,
    asset_serial_number: String,
    epoch_timestamp: Long,
    parameter_tag_ID: String,
    parameter_value: String,
    uom: String,
    event_type: String,
    dq_flag: Int,
    gateway_manufacturer: String,
    reserved_fields: List[String]


* */

case class ParsedMessage(
                          topic: String,
                          customer: String,
                          asset_serial_number: String,
                          epoch_timestamp: Long,
                          parameter_tag_ID: String,
                          parameter_value: String,
                          uom: String,
                          event_type: String,
                          gateway_manufacturer: String,
                          reserved_fields: List[String]
                        )

object Parser_Json_Handler {
  def main(args: Array[String]): Unit = {

    val lines = Source.fromFile(new File("src/main/resources/data.txt")).getLines.mkString

    println(lines)

    val obj = new JSONObject(lines)

    val topic = "topic_list"
    val customer = "ongc"
    val asset_serial_number = obj.getString("AssetName")
    val epoch_timestamp = obj.getLong("timestamp")



    val  values = obj.getJSONArray("values")

    for(i <- 0 until  values.length()){
      val obj = values.getJSONObject(i)

      val parameter_tag_ID = obj.getString("id")
      val parameter_value = obj.getLong("v")
      val uom = "NA"
      val event_type = ""
      val dq_flag = obj.getBoolean("q")
      val gateway_manufacturer = ""
      val reserved_fields = ""

      val txt = topic + " " + customer + " " + asset_serial_number + " " + epoch_timestamp+ " " + parameter_tag_ID +" "+ parameter_value +" "+ uom  +" "+ event_type +" "+ dq_flag +" "+ gateway_manufacturer  +" "+  reserved_fields

      println(txt)

//      val parsedMessages: Iterator[ParsedMessage] = recordIterator.flatMap(kafkaRecord => {
//        try {
//          rawMsg = new String(kafkaRecord.value(), StandardCharsets.UTF_8)
//          val jsonObject = new JSONObject()
//
//          val customer = "ongc"
//          val asset_serial_number = jsonObject.getString("AssetName")
//          val epoch_timestamp = jsonObject.getLong("timestamp")
//
//          val  values = jsonObject.getJSONArray("values")
//
//          var seq:Seq[ParsedMessage]= null
//
//          for(i <- 0 until  values.length()) {
//            val obj = values.getJSONObject(i)
//
//            val parameter_tag_ID = obj.getString("id")
//            val parameter_value_ID = obj.getString("v")
//            val uom = "NA"
//            val event_type = ""
//            val dq_flag = obj.getString("q")
//            val gateway_manufacturer = ""
//            val reserved_fields = ""
//
//            seq+= Seq(ParsedMessage(kafkaRecord.topic(), customer, asset_serial_number, epoch_timestamp, parameter_tag_ID, parameter_value_ID  , "NA", event_type , customer, List(dq_flag)))
//
//          }
//
//          seq.toIterator
//
//        } catch {
//          case e: Throwable =>
//            logger.error(s"Exception while parsing message using fmcti_common: $rawMsg", e)
//            KafkaSink.writeErroneousMessage(rawMsg, kafkaRecord.topic(), configBroadcast)
//            Seq()
//        }
//      })


    }





    def isValid(obj: String): Boolean = {
      try {
        new JSONObject(obj)
        true
      } catch {
        case _ => false
      }
    }


  }
}
