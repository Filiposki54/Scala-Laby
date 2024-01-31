import org.apache.pekko
import pekko.actor.{ActorSystem, Actor, ActorLogging, ActorRef, Props}

case object Ping
case object Pong

class Player(name: String) extends Actor with ActorLogging {
  def receive: Receive = {
    case Ping =>
      log.info(s"$name: Ping received")
      sender() ! Pong
    case Pong =>
      log.info(s"$name: Pong received")
      sender() ! Ping
  }
}

@main 
def mainProg: Unit = {
  val system = ActorSystem("PingPongGame")
  val jarek = system.actorOf(Props(new Player("Player")), "jarek")
  val lech = system.actorOf(Props(new Player("Player")), "lech")

  jarek.tell(Ping, lech)
}

//zad 2
// class Player(name: String, var nextPlayer: ActorRef) extends Actor with ActorLogging {
//   def receive: Receive = {
//     case Ping =>
//       log.info(s"$name: Ping received")
//       nextPlayer ! Pong
//     case Pong =>
//       log.info(s"$name: Pong received")
//       nextPlayer ! Ping
//     case SetNextPlayer(player) =>
//       nextPlayer = player
//   }
// }

// @main 
// def mainProg: Unit = {
//   val system = ActorSystem("PingPongGame")
//   val playerNames = List("Player1", "Player2", "Player3")
//   val players = playerNames.map(name => system.actorOf(Props(new Player(name, null)), name))

//   players.zip(players.tail :+ players.head).foreach { case (player, nextPlayer) =>
//     player ! SetNextPlayer(nextPlayer)
//   }

//   players.head ! Ping
// }

//zad 3
// import org.apache.pekko
// import pekko.actor.{ActorSystem, Actor, ActorLogging, ActorRef, Props}

// case object Ping
// case object Pong
// case class SetNextPlayer(player: ActorRef)

// class Player(name: String, var nextPlayer: Option[ActorRef] = None) extends Actor with ActorLogging {
//   def receive: Receive = {
//     case Ping =>
//       log.info(s"$name: Ping received")
//       nextPlayer.foreach(_ ! Pong)
//     case Pong =>
//       log.info(s"$name: Pong received")
//       nextPlayer.foreach(_ ! Ping)
//     case SetNextPlayer(player) =>
//       nextPlayer = Some(player)
//   }
// }

// @main 
// def mainProg: Unit = {
//   val system = ActorSystem("PingPongGame")
//   val playerNames = List("Player1", "Player2", "Player3", "Player4")
//   val players = playerNames.map(name => system.actorOf(Props(new Player(name)), name))

//   players.zip(players.tail :+ players.head).foreach { case (player, nextPlayer) =>
//     player ! SetNextPlayer(nextPlayer)
//   }

//   players.headOption.foreach(_ ! Ping)
// }