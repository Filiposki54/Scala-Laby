import scala.annotation.tailrec

//zad1
def insertInto[A](l: List[A], el: A)(leq: (A, A) => Boolean): List[A] = {
  @tailrec
  def insertIntoHelper(result: List[A], remaining: List[A]): List[A] = {
    remaining match {
      case Nil => el :: result.reverse
      case head :: tail if leq(el, head) => (el :: result).reverse ::: remaining
      case head :: tail => insertIntoHelper(head :: result, tail)
    }
  }
  insertIntoHelper(Nil, l)
}

//zad2
def applyForAll[A, B](l: List[A])(f: A => B): List[B] = {
  @tailrec
  def applyForAllHelper(remaining: List[A], result: List[B]): List[B] = {
    remaining match {
      case Nil => result.reverse
      case head :: tail =>
        applyForAllHelper(tail, f(head) :: result)
    }
  }
  applyForAllHelper(l, Nil)
}

//zad3
def compute[A, B](l: List[A], init: B)(op: (A, B) => B): B = {
  def computeHelper(remaining: List[A], accumulator: B): B = {
    remaining match {
      case Nil => accumulator
      case head :: tail =>
        computeHelper(tail, op(head, accumulator))
    }
  }
  computeHelper(l, init)
}

@main
def mainProg: Unit = {
  println(insertInto(List(1, 2, 4, 6), 3)((a, b) => a < b))
  println(applyForAll(List(1, 3, 5))(n => n + 3))
  println(compute(List(1, 2, 3, 4), 0)(_ + _))
  println(compute(List(1, 2, 3, 4), 1)(_ * _))
  println(compute(List("kota", " ", "ma", " ", "ala"), "")(_ + _))
}