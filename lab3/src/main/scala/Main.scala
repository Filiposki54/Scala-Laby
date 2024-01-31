import scala.annotation.tailrec

  //zad1
def divide[Int](list: List[Int]): (List[Int], List[Int]) = {
  @tailrec
  def divideHelper(remaining: List[Int], evenList: List[Int] = Nil, oddList: List[Int] = Nil, evenIndex: Boolean = true): (List[Int], List[Int]) = {
  remaining match {
    case Nil => (evenList.reverse, oddList.reverse)
    case head :: tail =>
      if (evenIndex) {
        divideHelper(tail, head :: evenList, oddList, evenIndex = false)
      } else {
        divideHelper(tail, evenList, head :: oddList, evenIndex = true)
      }
    }
  }
  divideHelper(list)
}
//zad2
def sum(list: List[Option[Int]]): Option[Int] = {
  def sumHelper(remaining: List[Option[Int]], acc: Int): Option[Int] = {
    remaining match {
      case Nil => Some(acc)
      case Some(value) :: tail => sumHelper(tail, acc + value)
      case None :: _ => None
    }
  }
  sumHelper(list, 0)
}

//zad3
def compress(l: List[Char]): List[(Char, Int)] = {
  def compressHelper(remaining: List[Char], currentChar: Char, currentCount: Int, result: List[(Char, Int)]): List[(Char, Int)] = {
    remaining match {
      case Nil => (currentChar, currentCount) :: result
      case head :: tail if head == currentChar => compressHelper(tail, currentChar, currentCount + 1, result)
      case head :: tail => compressHelper(tail, head, 1, (currentChar, currentCount) :: result)
    }
  }
  if (l.isEmpty) Nil
  else compressHelper(l.tail, l.head, 1, Nil).reverse
}

//zad4
def isSub(l: List[Char], lSub: List[Char]): Boolean = {
  def isSubHelper(remaining: List[Char], subRemaining: List[Char]): Boolean = {
    (remaining, subRemaining) match {
      case (_, Nil) => true
      case (Nil, _) => false
      case (head :: tail, subHead :: subTail) if head == subHead =>
        isSubHelper(tail, subTail)
      case (head :: tail, _) =>
        isSubHelper(tail, lSub)
    }
  }
  l.tails.exists(tail => isSubHelper(tail.toList, lSub))
}


@main
def mainProg: Unit = {
  println(divide(List(1, 3, 5, 6, 7)))
  println(sum(List(Some(1), Some(2), Some(3))))  
  println(compress(List('a', 'a', 'b', 'c', 'c', 'c', 'a', 'a', 'b', 'd')))
  println(isSub(List('b', 'o', 'c', 'i', 'a', 'n'), List('a', 'b', 'c')))
}

