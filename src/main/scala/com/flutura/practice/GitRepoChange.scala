package com.flutura.practice

import scala.io.Source

object GitRepoChange extends App {

  val repos = Source.fromFile("src/main/resources/repos.txt").getLines()

  for(line <- repos){
    val first = "docker push "
    val second = line
    val third = ""
    //println(first+second+third+second.substring(43))
    println(first+third+second.substring(43))


  }


}
