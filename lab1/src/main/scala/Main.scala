@main
def mainProg: Unit = {
  println(obramuj("Scala jest straszny!\n To zadanie jest okay jak sie zrozumialo"))
}

def obramuj(napis: String): String = {
  val linie = napis.split('\n')
  val maksDlugosc = linie.maxBy(_.length).length
  val ramka = "*" * (maksDlugosc + 4)

  val wynik = ramka + "\n" +
    linie.map(linia => s"* $linia${" " * (maksDlugosc - linia.length)} *").mkString("\n") +
    "\n" + ramka

  return (wynik)
}

