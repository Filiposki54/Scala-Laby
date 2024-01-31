@main
def mainProg: Unit = {
  def checkIfContains(lst: List[Int], set: Set[(Int, Int)]): Boolean = {

  def countOccurrences(lst: List[Int], num: Int, acc: Int = 0): Int = lst match {
    case Nil => acc
    case head :: tail => head match {
      case `num` => countOccurrences(tail, num, acc + 1)
      case _ => countOccurrences(tail, num, acc)
    }
  }

    def check(set: List[(Int, Int)], acc: Boolean = true): Boolean = set match {
      case Nil => acc
      case (num, count) :: tail =>
        check(tail, acc && (countOccurrences(lst, num) == count))
    }

    check(set.toList)
  }

  println(checkIfContains(List(1,1,1,2,2,2,3,2), Set((1,3), (2,4)))) //true
  println(checkIfContains(List(1,1,1,2,2,2,3,2), Set((1,3), (2,3)))) //false
  println(checkIfContains(List(1,1,1,2,2,2,3,2), Set((3,1), (2,2)))) //false
  return ()
}