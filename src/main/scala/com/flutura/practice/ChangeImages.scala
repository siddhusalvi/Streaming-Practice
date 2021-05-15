package com.flutura.practice

import java.io.File
import scala.collection.mutable.ListBuffer

object ChangeImages extends App {

  val directoryPath = new File("D:\\yaml files\\standard-applications")

  //List of all files and directories
  val filesList = directoryPath.listFiles.toList
  println(filesList)

for(file <- filesList)
println(file)
}

