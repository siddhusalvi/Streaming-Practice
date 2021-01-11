package com.flutura.practice

import java.io.FileInputStream
import java.util.Properties

//Class to import kafka properties
object PropertyManager {
  //Function to get properties
  def getProperties(): Properties = {
    val filePath = "src/main/resources/config.properties"
    val properties = new Properties()
    try {
      val fileInputStream = new FileInputStream(filePath)
      properties.load(fileInputStream)
      fileInputStream.close()
    } catch {
      case exception: Exception => println(exception.printStackTrace())
    }
    return properties
  }

}
