package com.flutura.practice

case class MathEngineOutput(
                             operating_unit_serial_number: String,
                             timestamp: Long,
                             parameter: String,
                             reading: Double,
                             DQ: Int,
                             uom: String,
                             createdTimestamp: Long
                           )

object MathEngineMessageFormat extends App{

  val mathRecord = new MathEngineOutput("a",12,"ab",12,5,"sd",123)
  val anotherMsg = s"${3}$$${8}$$"
  val msg = s"${mathRecord.operating_unit_serial_number}$$${mathRecord.timestamp}$$${mathRecord.parameter}$$" + s"${mathRecord.reading}$$${mathRecord.DQ}$$${mathRecord.uom}$$NA"

  println(msg)
}