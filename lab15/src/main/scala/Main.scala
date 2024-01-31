@main
def mainProg: Unit = {
    // Zadanie 1
  class C(val re: Double, val im: Double) extends Ordered[C] {
    // Zadanie 2
    override def toString: String = im match {
      case 0 => s"$re"
      case _ if im > 0 => s"$re + ${im}i"
      case _ => s"$re - ${-im}i"
    }

    // Zadanie 3
    def +(that: C): C = new C(this.re + that.re, this.im + that.im)
    def -(that: C): C = new C(this.re - that.re, this.im - that.im)
    def *(that: C): C = new C(this.re * that.re - this.im * that.im, this.re * that.im + this.im * that.re)

    // Zadanie 4
    def /(that: C): C = {
      if (that.re == 0 && that.im == 0) throw new IllegalArgumentException("Cannot divide by zero")
      val denom = that.re * that.re + that.im * that.im
      new C((this.re * that.re + this.im * that.im) / denom, (this.im * that.re - this.re * that.im) / denom)
    }

    // Zadanie 6
    def +(that: Double): C = new C(this.re + that, this.im)
    def *(that: Double): C = new C(this.re * that, this.im * that)

    // Zadanie 7
    override def compare(that: C): Int = (this.re * this.re + this.im * this.im) compare (that.re * that.re + that.im * that.im)
  }

  // Zadanie 5
  object C {
    def apply(re: Double): C = new C(re, 0)
  }

  // Testy
  val c1 = new C(3.0, 4.0)
  val c2 = new C(3.0, -4.0)
  val c3 = C(3.0)
  println(c1 + c2)
  println(c1 * c2)
  println(c1 / c2)
  println(c1 + 5.3)
  println(c1 * 2.5)
  println(c1 > c2)
  println(c1 == c2)
}