package com.flutura.practice

import org.apache.log4j.{LogManager, Logger}

object DriverProgram {
  def main(args: Array[String]): Unit = {
    var logger: Logger = null
    try {

      //Getting properties
      val properties = PropertyManager.getProperties()
//
//      //Setting up logger
//      logger = LogManager.getLogger(properties.getProperty("logger"))

      val kafkaReceiver = new KafkaReceiver()
      kafkaReceiver.start()


    } catch {
      case exception: Exception => logger.debug(exception.printStackTrace())
    } finally {
      //Releasing resources
      logger.info("Running finally block")
    }
  }
}
