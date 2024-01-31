import scala.annotation.tailrec

@main
def mainProg: Unit = {
  //zad1
  def reverse(str: String): String = {
    @tailrec
    def helper(str: String, acc: String): String = {
      if(str == "") { acc }
      else helper(str.tail, str.head+:acc)
    }
    helper(str, "")
  }
  println("Kot in reverse is: " + reverse("Kot"))

  //zad2
  def isPrime(n: Int): Boolean = {
    @tailrec
    def helper(number: Int): Boolean = {
      if (number <= 1) true
      else if (n % number == 0) false
      else helper(number - 1)
    }
    if (n <= 1) false
    else helper(Math.sqrt(n).toInt)
  }
  println("53 is a prime number: " + isPrime(53))
  println("10 is a prime number: " + isPrime(10))

  //zad3 
  def binToDec(bin: Int): Int = {
     @tailrec
    def binToDecHelper(binary: Int, power: Int, result: Int): Int = {
      if (binary == 0) result
      else binToDecHelper(binary / 10, power + 1, result + (binary % 10) * math.pow(2, power).toInt)
    }
    binToDecHelper(bin, 0, 0)
  }
  println("1010^2 == " + binToDec(1010) + "^10")  // 10
  println("1101^2 == " + binToDec(1101) + "^10")  // 13
  println("11111^2 == " + binToDec(11111) + "^10") // 31

  //zad4
  def value(n: Int): Int = {
    @tailrec
    def valueHelper(n: Int, a: Int, b: Int): Int = {
      if (n == 0) a
      else valueHelper(n - 1, b, a + b)
    }
    valueHelper(n, 2, 1)
  }
  for (i <- 0 until 10) {
      println(value(i))
    }
}