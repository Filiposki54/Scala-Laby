@main
def zad1: Unit = {
  println(
    io.Source
      .fromResource("ogniem-i-mieczem.txt")
      .getLines
      .mkString
      .toLowerCase
      .filter(_.isLetter)
      .groupBy(identity)
      .mapValues(_.length)
      .toList
      .sortWith(_._2 > _._2)
      .map { case (char, freq) =>
        s"$char:${"*" * (freq * 50 / io.Source
          .fromResource("ogniem-i-mieczem.txt")
          .getLines
          .mkString
          .toLowerCase
          .filter(_.isLetter)
          .groupBy(identity)
          .mapValues(_.length)
          .maxBy(_._2)._2)}"
      }
      .mkString("\n")
  )
}