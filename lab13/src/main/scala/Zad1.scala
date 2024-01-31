import org.apache.pekko
import pekko.actor._
import scala.concurrent.duration._
import scala.util.Random

object Attack
case class Health(hp: Int)
case object DefenderFallen

class HigherPower extends Actor {
  def receive = {
    case Attack => 
      println("HigherPower issued an attack")
      val castleActors = context.actorSelection("/user/*Castle")
      castleActors.tell(Identify(None), self)
    case ActorIdentity(_, Some(actorRef)) =>
      actorRef ! Attack
    case ActorIdentity(_, None) =>
      println("No active castle actors found")
      context.stop(self)
    case DefenderFallen =>
      context.stop(self)
  }
}

class Castle(castleNumber: Int) extends Actor {
  var defenders = List.fill(100)(context.actorOf(Props[Defender]()))
  var health = defenders.size * 100 // assuming each defender has 100 health

  def receive = {
    case Attack => 
      println(s"Castle $castleNumber is under attack, health: $health")
      if (defenders.nonEmpty) {
        val hitChance = defenders.size / 200.0
        if (Random.nextDouble() < hitChance) {
          val defender = defenders(Random.nextInt(defenders.size))
          defender ! Attack
        }
      } else {
        println(s"Castle $castleNumber has fallen")
        context.parent ! DefenderFallen
      }
    case DefenderFallen =>
      defenders = defenders.filterNot(_ == sender())
      health = defenders.size * 100
  }
}

class Defender extends Actor {
  var health = 100
  def receive = {
    case Attack =>
      health -= 10
      println(s"Defender was attacked, remaining health: $health")
      if (health <= 0) {
        println("Defender has fallen")
        context.parent ! DefenderFallen
        context.stop(self)
      }
  }
}

@main 
def zad1: Unit = {

  val system = ActorSystem("system")
  import system.dispatcher

  val higherPower = system.actorOf(Props[HigherPower](), "higherPower")
  val castle1 = system.actorOf(Props[Castle](), "Castle1")
  val castle2 = system.actorOf(Props[Castle](), "Castle2")

  system.scheduler.scheduleWithFixedDelay(
    Duration.Zero,
    1.second,
    higherPower,
    Attack
  )
}