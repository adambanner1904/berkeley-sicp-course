package T3Processes

import language.SchemeSyntax.*
import T1FunctionalProgramming.Book.square

import scala.annotation.tailrec

object Book:

  def countChange(amount: Double, coins: List[Double]): Double = {
    println(s"Counting change for £${amount.toInt} with ${coins.map(_.toInt)} coins")
    if amount == 0 then 1
    else if amount < 0 || coins.isEmpty then 0
    else add(
      countChange(amount, coins.tail),
      countChange(subtract(amount, coins.head), coins)
    )
  }

  def fRecursive(n: Double): Double =
    if n < 3 then n
    else add(fRecursive(subtract(n, 1)), multiply(2, fRecursive(subtract(n, 2))), multiply(3, fRecursive(subtract(n, 3))))

  def fIterative(n: Double): Double =
    @tailrec
    def iter(i: Double, f1: Double, f2: Double, f3: Double): Double = {
      val next = add(f3, multiply(2, f2), multiply(3, f1))
      if i == n then f3
      else iter(add(i, 1), f2, f3, next)
    }

    if n < 3 then n
    else iter(i=3, 0, 1, 2)

  def pascal(row: Int, col: Int): Int =
    if col == 0 || col == row then 1
    else pascal(row - 1, col - 1) + pascal(row - 1, col)

  def fastExponential(b: Double, n: Double): Double =
    @tailrec
    def iter(nextB: Double, nextN: Double, acc: Double): Double = {
      println(s"Calculating $nextB to the power of $nextN with carry over $acc")
      if nextN == 0 then acc
      else if nextN % 2 == 0 then iter(square(nextB), nextN / 2, acc)
      else iter(nextB, subtract(nextN, 1), nextB * acc)
    }

    iter(b, n, 1.0)

  def fastMultiplication(a: Double, b: Double): Double =
    def double(n: Double): Double = n * 2
    def halve(n: Double): Double = n / 2
    @tailrec
    def iter(nextA: Double, nextB: Double, acc: Double): Double = {
      println(s"Calculating $nextA multiplied by $nextB with carry over $acc")
      if nextB == 0 then acc
      else if nextB % 2 == 0 then iter(double(nextA), halve(nextB), acc)
      else iter(nextA, subtract(nextB, 1), nextA * acc)
    }

    iter(a, b, 1.0)

  def main(args: Array[String]): Unit = {
//     println(countChange(11.toDouble, List(1, 5, 10, 25, 50).map(_.toDouble)))
//     val startTime = System.nanoTime()
//     println(fRecursive(100))
//     println(System.nanoTime() - startTime )
//
//     val iterativeStartTime = System.nanoTime()
//     println(fIterative(100))
//     println(System.nanoTime() - iterativeStartTime)
//
//      println(pascal(4, 2)) // 6
    println(fastMultiplication(25903, 2349453))
  }