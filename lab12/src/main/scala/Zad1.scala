import org.apache.pekko
import pekko.actor._

case class Init(liczbaPracownikow: Int)
case class Zlecenie(tekst: List[String])
case class Wykonaj(napis: String)
case class Wynik(liczbaSlow: Int)

class Pracownik extends Actor {
  def receive: Receive = {
    case Wykonaj(napis) =>
      val liczbaSlow = napis.split("\\s+").map(_.toLowerCase).distinct.length
      sender() ! Wynik(liczbaSlow)
  }
}

class Nadzorca extends Actor {
  var pracownicy: List[ActorRef] = Nil
  var tekst: List[String] = Nil
  var wynik: Int = 0

  def receive: Receive = idle

  def busy: Receive = {
    case Zlecenie(tekst) =>
      this.tekst = tekst
      pracownicy.foreach(_ ! Wykonaj(tekst.head))
    case Wynik(liczbaSlow) =>
      wynik += liczbaSlow
      tekst = tekst.tail
      if (tekst.isEmpty) {
        println(s"Wynik: $wynik")
        wynik = 0
        context.become(idle)
      } else {
        sender() ! Wykonaj(tekst.head)
      }
  }

  def idle: Receive = {
    case Init(liczbaPracownikow) =>
      pracownicy = (1 to liczbaPracownikow).map(_ => context.actorOf(Props[Pracownik]())).toList
      context.become(busy)
  }
}

object Main {
  @main 
  def zad1: Unit = {
    def dane(): List[String] = {
      scala.io.Source.fromResource("ogniem_i_mieczem.txt").getLines.toList
    }
    val system = ActorSystem("WordCounter")
    val nadzorca = system.actorOf(Props[Nadzorca](), "nadzorca")
    nadzorca ! Init(5)
    nadzorca ! Zlecenie(dane())
  }
}