import org.apache.pekko
import pekko.actor.*

case class Przyjmij(lUcz: Set[ActorRef])
case object Pytanie
case class Odpowiedz(odp: Int)

class Nauczyciel extends Actor {
  def receive: Receive = {
    case Przyjmij(lUcz) =>
      lUcz.foreach(_ ! Pytanie) 
    case Odpowiedz(odp) =>
      println(s"Otrzymano odpowiedź: $odp")
  }
}

class Uczen extends Actor {
  def receive: Receive = {
    case Pytanie =>
      val odp = Random.nextInt(100)
      sender ! Odpowiedz(odp) 
  }
}

@main 
def mainProg: Unit = {
  val system = ActorSystem("Hollywood")
  val nauczyciel = system.actorOf(Props[Nauczyciel], "nauczyciel")
  val uczniowie = for (i <- 1 to 5) yield system.actorOf(Props[Uczen], s"uczen$i")
  nauczyciel ! Przyjmij(uczniowie.toSet)
}

