package T2HigherOrderProcedures

import T1FunctionalProgramming.Lecture.square
import language.SchemeSyntax.*
import language.Types.*
import T2HigherOrderProcedures.Book.cube

import scala.annotation.tailrec

object Book133:
  def average(ns: Double*): Double = add(ns *) / ns.length
  val tolerance = 0.00001
  def closeEnough(x: Double, y: Double): Boolean = math.abs(subtract(x, y)) < tolerance

  def halfIntervalMethod(f: Function, a: Double, b: Double): Double =
    @tailrec
    def search(f: Function, negPoint: Double, posPoint: Double): Double =
      // println(s"searching with $negPoint and $posPoint")

      val midpoint = average(negPoint, posPoint)
      val testValue = f(midpoint)
      // println(s"Midpoint = $midpoint and the testValue = $testValue")

      if closeEnough(negPoint, posPoint) then midpoint
      else if testValue > 0 then search(f, negPoint, midpoint)
      else if testValue < 0 then search(f, midpoint, posPoint)
      else midpoint

    val fa = f(a)
    val fb = f(b)
    if fa < 0 & fb > 0 then search(f, a, b)
    else if fb < 0 & fa > 0 then search(f, b, a)
    else throw new IllegalArgumentException(s"Values are not of opposite sign a: $fa, b: $fb")

  def fixedPointWithoutAverageDamping(f: Function, firstGuess: Double): Double =
    @tailrec
    def iter(guess: Double): Double =
      val next = f(guess)
      if closeEnough(guess, next) then next
      else iter(next)

    iter(firstGuess)

  def sqrt(x: Double): Double = fixedPointOfTransform(x / _, averageDamp, 1.0)

  def fixedPoint(f: Function, firstGuess: Double): Double =
    @tailrec
    def iter(guess: Double): Double =
      val next = f(guess)
      if closeEnough(guess, next) then next
      else iter(next)

    iter(firstGuess)

  def continuedFraction(n: Function, d: Function, k: Int, combine: Combiner = add(_, _)): Double =
    def loop(i: Double): Double =
      if i > k then n(i) / d(i)
      else n(i) / combine(d(i), loop(i + 1))

    loop(1.0)

  def e: Double = {
    def d(i: Double): Double =
      if i % 3 == 2 then multiply(add((i / 3).floor, 1), 2)
      else 1.0
    continuedFraction(_ => 1.0, d, 10) + 2
  }

  def tanCF(x: Double, k: Int): Double =
    def n(i: Double): Double =
      if i == 1 then x
      else multiply(x, x)
    def d(i: Double): Double = add(multiply(subtract(i, 1), 2), 1)
    continuedFraction(n, d, k, subtract(_, _))

  def averageDamp(f: Function): Function = x => average(x, f(x))

  def cubeRoot(x: Double): Double = fixedPoint(y => x / square(y), 1.0)
  val dx = 0.00001

  def derivative(g: Function): Function = x =>
    subtract(g(add(x, dx)), g(x)) / dx

  def newtonTransform(g: Function): Function = x =>
    subtract(x, g(x) / derivative(g)(x))

  def newtonsMethod(g: Function, guess: Double): Double =
    fixedPoint(newtonTransform(g), guess)

  def sqrt_v2(x: Double): Double =
    fixedPointOfTransform(y => subtract(square(y), x), newtonTransform, 1.0)

  def fixedPointOfTransform(g: Function, transform: Transformer, guess: Double): Double =
    fixedPoint(transform(g), guess)

  // refactored sqrt and sqrt_v2 to use this generic function

  def cubic(a: Double, b: Double, c: Double): Function = x =>
    cube(x) + multiply(a, square(x)) + multiply(b, x) + c
  def double(f: Function): Function = x => f(f(x))
  def compose(f: Function, g: Function): Function = x => f(g(x))
  def repeated(f: Function, n: Int): Function =
    if n <= 1 then f
    else compose(f, repeated(f, n - 1))

  def smooth(f: Function): Function = x =>
    average(f(subtract(x, dx)), f(x), f(add(x, dx)))

  def nFoldSmooth(n: Int): Transformer = f => repeated(smooth(f), n)

  def iterativeImprove(goodEnough: Predicate[Double], improveGuess: Function): Function = initialGuess =>

    if goodEnough(initialGuess) then initialGuess
    else iterativeImprove(goodEnough, improveGuess)(improveGuess(initialGuess))

  def fixedPoint_v2(f: Function, firstGuess: Double): Double = {
    // very readable
    // use iterative improve method, closeEnough, compares a value to the first guess
    // the improve function is an average damped version of f
    // and we apply this to f
    iterativeImprove(closeEnough(_, firstGuess), averageDamp(f))(firstGuess)
  }


  def main(args: Array[String]): Unit = {
    // println(s"approximate pi: ${halfIntervalMethod(math.sin, 2.0, 4.0)}")
    // println(s"Fixed point of cos at x = 1.0: ${fixedPoint(math.cos, 1.0)}")
    // println(s"Sqrt of 2 using fixed point method: ${sqrt(2)}")
    // println(s"Sqrt of 2 using newtons method: ${sqrt_v2(2)}")
    val phi = fixedPoint_v2(1 + 1 / _, 1.0)
     println(s"The golden ratio (phi) as a fixed point: $phi")
    // fixedPointWithoutAverageDamping(math.log(1000) / math.log(_), 10) // takes 34 steps
    // fixedPoint(math.log(1000) / math.log(_), 10) // does it in 11 steps
    // println(s"1 over the fixed point phi ${1 / phi}")
    // println(s"The golden ratio as a continued fraction: ${continuedFraction(_ => 1.0, _ => 1.0, 10)}")
    // println(s"e approximated via continued fractions: $e")
    // println(s"Std lib tan function: ${math.tan(1.0)}")
    // println(s"Tan implemented as a continued fraction: ${tanCF(1.0, 10)}")
    // println(s"Zeros of cubic function: ${newtonsMethod(cubic(0, 0, 0), 1.0)}")
    println(repeated(square, 2)(5))
  }