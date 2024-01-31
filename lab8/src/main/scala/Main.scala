//zad1
def countChars(str: String): Int = {
  str.toSet.size
}

//zad2
val strefy: Seq[String] = java.util.TimeZone.getAvailableIDs.toSeq
def sortEuropeanTimeZones(): Seq[String] = {
    strefy
        .filter(_.startsWith("Europe"))
        .map(_.stripPrefix("Europe/"))
        .sortBy(s => (s.length, s))
}

//zad3
def score(code: Seq[Int])(move: Seq[Int]): (Int, Int) = {
  (
    (code zip move).count(pair => pair._1 == pair._2),
    code.groupBy(identity).mapValues(_.size).toList
      .flatMap { case (k, v) => List.fill(Math.min(v, move.count(_ == k)))(k) }
      .size - (code zip move).count(pair => pair._1 == pair._2)
  )
}

//zad4
def calculateFinalScores(partialScores: List[(String, String, Int, Int)]): List[(Int, String, String, Double)] = {
  partialScores.groupBy(ps => (ps._1, ps._2)).map { case ((name, surname), scores) =>
    (0, name, surname, scores.map(_._3).sum.toDouble / scores.size + scores.map(_._4).sum.toDouble / scores.size)
  }.toList.sortBy(fs => (-fs._4, -fs._1, fs._3, fs._2)).zipWithIndex.map {
    case (fs, index) => (index + 1, fs._2, fs._3, fs._4)
  }
}

@main
def mainProg: Unit = {
    println(countChars("Ala ma kota"))
    println(sortEuropeanTimeZones())
}