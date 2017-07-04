package crawler

import java.net.URL

import akka.actor.{ActorSystem, PoisonPill, Props}

import scala.concurrent.Await
import scala.concurrent.duration._
import scala.language.postfixOps

/**
  * Created by Ouasmine on 06/06/2017.
  */
object StartingPoint{
  def crawl(url: String): Unit ={
    val system = ActorSystem()
    val supervisor = system.actorOf(Props(new Supervisor(system)))
    var link:URL = new URL(url)

    supervisor ! Start(link)

    Await.result(system.whenTerminated, 10 minutes)

    supervisor ! PoisonPill
    system.terminate
  }
}
