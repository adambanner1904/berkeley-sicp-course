package language

import scala.annotation.tailrec
import language.Types.{Combiner, Function}
import T2HigherOrderProcedures.Book.product

object CommonFunctions:
  def positive(x: Int): Boolean = x > 0
  def negative(x: Int): Boolean = x < 0
  def average(xs: Double*): Double = xs.sum / xs.length
  def increment(x: Double): Double = x + 1
  def identity(x: Double): Double = x
  def square(x: Int): Int = x * x
  def square(x: Double): Double = x * x
  def factorial(n: Int): Double = product(identity, 1.0, n.toDouble)
  @tailrec
  def gcd(a: Int, b: Int): Int =
    if b == 0 then a
    else gcd(b, a % b)
  def isPrime(n: Double): Boolean =
    val stop = math.sqrt(n).ceil

    @tailrec
    def isPrimeHelper(x: Double): Boolean =
      if x > stop then true
      else if n % x == 0 then false
      else isPrimeHelper(x + 1)

    if n <= 2 then true
    else isPrimeHelper(2)

  def fib(n: Int): Int =
    @tailrec
    def iter(i: Int, f1: Int, f2: Int): Int =
      if i > n then f2
      else iter(i + 1, f2, f1 + f2)

    if n < 2 then n
    else iter(2, 0, 1)
    

  def main(args: Array[String]): Unit = {
    println((0 to 10).map(fib))
  }
