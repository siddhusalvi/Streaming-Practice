package com.flutura.practice

import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.TimeZone
import java.sql.Timestamp

object dateformat extends App {
  val NEW_FORMAT = "MM/dd/yyyy HH:mm:ss a"
  val formatter = new SimpleDateFormat(NEW_FORMAT)
  formatter.setTimeZone(TimeZone.getTimeZone("UTC"))
  val strDate = "3/8/2021 12:00:00 AM"
  val strDate1 = "3/5/2021 8:45:31 AM"


//  Friday, March 5, 2021 8:45:31.725 AM

  var d = formatter.parse(strDate)
  println(d.getTime());
val str = "DIMO_N8293_MVTERR"
val str1 = "DIMO_N8293"
println(str)


  d = formatter.parse(strDate)
  println(d.getTime());


}
