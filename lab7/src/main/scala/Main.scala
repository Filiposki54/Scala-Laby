@main

//zad1
def subSeq[A](seq: Seq[A], begIdx: Int, endIdx: Int): Seq[A] = {
    seq.drop(begIdx).take(endIdx - begIdx)
}

//zad2
def getMinMax[A](seq: Seq[(A, Double)]): Option[(A, A)] = {
    if (seq.isEmpty) None
    else {
        val minElement = seq.minBy(_._2)
        val maxElement = seq.maxBy(_._2)
        Some((minElement._1, maxElement._1))
    }
}

//zad3
def remElems[A](seq: Seq[A], k: Int): Seq[A] = {
    seq.zipWithIndex.filter { case (_, index) => index != k }.map(_._1)
}

//zad4
def diff[A](seq1: Seq[A], seq2: Seq[A]): Seq[A] = {
    seq1.zip(seq2).filter { case (elem1, elem2) => elem1 != elem2 }.map(_._1)
}

//zad5
def sumOption(seq: Seq[Option[Double]]): Double = {
    seq.foldLeft(0.0) {
        case (sum, Some(value)) => sum + value
        case (sum, None) => sum
    }
}

//zad6
def deStutter[A](seq: Seq[A]): Seq[A] = {
    seq.foldLeft(Seq.empty[A]) {
        case (acc, elem) if acc.isEmpty || acc.last != elem => acc :+ elem
        case (acc, _) => acc
    }
}

//zad7
def isOrdered[A](seq: Seq[A])(leq: (A, A) => Boolean): Boolean = {
    seq.sliding(2).forall {
        case Seq(a, b) => leq(a, b)
        case _ => true
    }
}

//zad8
def freq[A](seq: Seq[A]): Set[(A, Int)] = {
    seq.groupBy(identity).map { case (key, values) => (key, values.size) }.toSet
}

//zad9
def threeNumbers(n: Int): Seq[(Int, Int, Int)] = {
    for {
        c <- 1 to n
        b <- 1 until c
        a <- 1 until b
        if a*a + b*b == c*c
    } yield (a, b, c)
}

def mainProg: Unit = {
}