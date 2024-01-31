import scala.annotation.tailrec

//zad1
def isOrdered(list: List[Int], leq: (Int, Int) => Boolean): Boolean = {
  @tailrec
  def isOrderedHelper(remaining: List[Int], prev: Int): Boolean = {
    remaining match {
      case Nil => true
      case head :: tail =>
        if (leq(prev, head)) {
          isOrderedHelper(tail, head)
        } else {
          false
        }
    }
  }
  list match {
    case Nil => true
    case head :: tail => isOrderedHelper(tail, head)
  }
}

//zad2
def merge(a: List[Int], b: List[Int])(leq: (Int, Int) => Boolean): List[Int] = {
  @tailrec
  def mergeHelper(result: List[Int], remainingA: List[Int], remainingB: List[Int]): List[Int] = {
    (remainingA, remainingB) match {
      case (Nil, Nil) => result.reverse
      case (Nil, headB :: tailB) => mergeHelper(headB :: result, Nil, tailB)
      case (headA :: tailA, Nil) => mergeHelper(headA :: result, tailA, Nil)
      case (headA :: tailA, headB :: tailB) =>
        if (leq(headA, headB)) {
          mergeHelper(headA :: result, tailA, remainingB)
        } else {
          mergeHelper(headB :: result, remainingA, tailB)
        }
    }
  }
  mergeHelper(Nil, a, b)
}

//zad3
def getFirst(l: List[Int])(pred: Int => Boolean)(op: Int => Int): Option[Int] = {
  @tailrec
  def getFirstHelper(remaining: List[Int], found: Boolean): Option[Int] = {
    remaining match {
      case Nil => None
      case head :: tail =>
        if (found) {
          Some(op(head))
        } else if (pred(head)) {
          getFirstHelper(tail, found = true)
        } else {
          getFirstHelper(tail, found)
        }
    }
  }
  getFirstHelper(l, found = false)
}

//zad4
def modify(l1: List[Int], l2: List[Int])(pred: (Int, Int) => Boolean)(op: (Int, Int) => Int): List[Int] = {
  def modifyHelper(remaining1: List[Int], remaining2: List[Int], result: List[Int]): List[Int] = {
    (remaining1, remaining2) match {
      case (Nil, _) | (_, Nil) => result.reverse
      case (head1 :: tail1, head2 :: tail2) =>
        if (pred(head1, head2)) {
          modifyHelper(tail1, tail2, op(head1, head2) :: result)
        } else {
          modifyHelper(tail1, tail2, result)
        }
    }
  }
  modifyHelper(l1, l2, Nil)
}

@main
def mainProg: Unit = {
  println(isOrdered(List(1, 3, 3, 6, 8), (_ <= _)))
  println(isOrdered(List(1, 3, 3, 6, 8), (_ >= _)))

  println(merge(List(1, 3, 5, 8), List(2, 4, 6, 8, 10, 12))((m, n) => m < n))
  println(getFirst(List(1, 2, 3, 4, 5))(_ > 2)(_ + 2))
  println(getFirst(List(1, 2, 3, 4, 5))(_ > 5)(_ + 2))
  println(modify(List(2, -1, 3, -8, 5), List(3, -3, 3, 0, -4, 5))(_ < _)(_ + _))
  }
