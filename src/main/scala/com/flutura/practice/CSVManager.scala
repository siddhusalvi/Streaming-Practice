package com.flutura.practice

import scala.io.Source

object CSVManager extends App {

  var file = "src/main/resources/a.csv"

  var lines = Source.fromFile(file).getLines()
  //  val NEW_FORMAT = "yyyy-dd-MM"
  //  val formatter = new SimpleDateFormat(NEW_FORMAT)
  //  formatter.setTimeZone(TimeZone.getTimeZone("UTC"))
  //  val strDate = "3/8/2021 12:00:00 AM"
  //  val strDate1 = "3/5/2021 8:45:31 AM"
  //  Friday, March 5, 2021 8:45:31.725 AM
  //  var d = formatter.parse(strDate)


  lines.foreach {
    line =>
      var record = line.split(",")
      var rawDate = record(2).split(" ")(0).split("-")
      var date = rawDate(1) + "/" + rawDate(2) + "/" + rawDate(0) + " 12:00:00 AM"
      record(2) = date
      var newRecord = "\""+record(0)+"\"" + "," +"\"" + record(2)+"\"" + ","+"\"" + record(3)+"\"" + ","+"\"" + record(4)+"\"" + "," +"\""+ record(5) +"\""+ ","+"\"" + record(6)+"\""+ ","+"\""+ record(7)+"\""+ ","+"\""+ record(8)+"\""
      println(newRecord)
  }


}
