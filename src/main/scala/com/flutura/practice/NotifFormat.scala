package com.flutura.practice

class Msg{
  val asset_serial_number = "ABC"
  val parameter_tag_id = "OFFLINE"
}
object NotifFormat extends App{

  var counter:Double = 1
  var amount:Double = 1000000
  var rate:Double = 10
  while(counter <= 15 ){
    var str = counter+" "+amount.toInt+" "+(amount/100*rate).toInt+" "+(amount+amount/100*rate).toInt
    println(str)
    amount += amount/100*rate
    counter +=1
  }


}
