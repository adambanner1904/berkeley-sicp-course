package T2HigherOrderProcedures

import language.SchemeSyntax.*
import language.Types.*

import scala.annotation.tailrec

object Book:
  def sum(f: Function, a: Double, b: Double, next: Function = increment): Double = accumulate(add(_, _), 0, f, a, b)

  def product(f: Function, a: Double, b: Double, next: Function = increment): Double = accumulate(multiply(_, _), 1, f, a, b)

  def accumulate(combiner: Combiner, z: Double, f: Function, a: Double, b: Double, next: Function = increment): Double =
    @tailrec
    def iter(newA: Double, acc: Double): Double =
      if newA > b then acc
      else iter(next(newA), combiner(f(newA), acc))

    iter(a, z)

  def accumulateRec(combiner: Combiner, z: Double, f: Function, a: Double, b: Double, next: Function = increment): Double =

    if a > b then z
    else combiner(f(a), accumulateRec(combiner, z, f, next(a), b))

  def filteredAccumulate(predicate: Predicate[Double],
                         combiner: Combiner,
                         z: Double,
                         f: Function,
                         a: Double,
                         b: Double,
                         next: Function = increment): Double =
    @tailrec
    def iter(newA: Double, acc: Double): Double = {
      if newA > b then acc
      else if predicate(newA) then iter(next(newA), combiner(f(newA), acc))
      else iter(next(newA), acc)
    }

    iter(a, z)


  def cube(x: Double): Double = x * x * x

  def increment(x: Double): Double = x + 1

  def identity(x: Double): Double = x

  def piFunction(x: Double): Double = 1.0 / multiply(x, add(x, 2))

  def piNext(x: Double): Double = x + 4

  def sumCubes(a: Double, b: Double): Double = sum(cube, a, b)

  def sumIntegers(a: Double, b: Double): Double = sum(identity, a, b)

  def sumPi(a: Double, b: Double): Double = sum(piFunction, a, b, piNext)

  def integral(f: Function, a: Double, b: Double, dx: Double): Double =
    multiply(sum(f, add(a, dx / 2.0), b, _ + dx), dx)

  def simpsonsRule(f: Function, a: Double, b: Double, n: Int): Double =
    val h = subtract(b - a) / n

    def yf(k: Double): Double =
      val c = {
        if k == 0 || k == n then 1
        else if k % 2 == 0 then 2
        else 4
      }
      multiply(c, f(add(a, multiply(k, h))))

    multiply(h / 3, sum(yf, 0, n))

  def factorial(n: Int): Double = product(identity, 1.0, n.toDouble)

  def piApprox(n: Int): Double = {
    def f(k: Double): Double =
      multiply(multiply(2, k) / subtract(multiply(2, k), 1), multiply(2, k) / add(multiply(2, k), 1))

    multiply(2, product(f, 1.0, n.toDouble))
  }

  def isPrime(n: Double): Boolean =
    val stop = math.sqrt(n).ceil
    @tailrec
    def isPrimeHelper(x: Double): Boolean =
      if x > stop then true
      else if n % x == 0 then false
      else isPrimeHelper(x + 1)

    if n <= 2 then true
    else isPrimeHelper(2)

  def sumPrimes(a: Int, b: Int): Double =
    filteredAccumulate(isPrime, add(_, _), 0, identity, a.toDouble, b.toDouble)

  @tailrec
  def gcd(a: Int, b: Int): Int =
    if b == 0 then a
    else gcd(b, a % b)

  def coprimeProduct(n: Int): Double =
    filteredAccumulate((x: Double) => gcd(n, x.toInt) == 1, multiply(_, _), 1, identity, 1, n)

  def main(args: Array[String]): Unit = {
    val int = integral(cube, 0, 1, 0.001)
    val simpsInt100 = simpsonsRule(cube, 0, 1, 100)
    val simpsInt1000 = simpsonsRule(cube, 0, 1, 1000)
    println(s"Normal integral: $int")
    println(s"Simpsons rule with 100: $simpsInt100")
    println(s"Simpsons rule with 1000: $simpsInt1000")
    println(s"Factorial using product: ${factorial(5)}")
    println(s"Pi approx with 100: ${piApprox(100)}")
    println(s"Pi approx with 1000: ${piApprox(1000)}")
    // println(s"Pi approx with 100000: ${piApprox(100000)}")
    println(s"Accumulate rec : ${accumulate(multiply(_, _), 1, identity, 1.0, 5.0).toInt}")
    println(s"Sum primes between 4 and 20: ${sumPrimes(4, 10)}")
    println(s"Coprime product up to 10: ${coprimeProduct(5)}")
  }