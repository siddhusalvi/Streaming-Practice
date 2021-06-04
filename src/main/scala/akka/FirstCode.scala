package akka
import akka.actor.{Actor, ActorSystem, Props}

class Example extends Actor{
  override def receive() ={
    case a:Int=>println(a)
    case b:String=>println(b + " " + sender())
  }
}
object FirstCode  {
  def main(args: Array[String]): Unit = {

    var actorSys = ActorSystem("sysName")
    var actor = actorSys.actorOf(Props[Example], "ExampleInstance")

    actor ! "flyingGett"
    actor ! 25

  }
}
