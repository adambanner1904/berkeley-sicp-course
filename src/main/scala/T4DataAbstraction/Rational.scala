package T4DataAbstraction

import language.SchemeSyntax.*
import language.CommonFunctions.*

import scala.annotation.tailrec

object Rational {
  type Rational = (Int, Int)
  
  @tailrec
  private def gcd(a: Int, b: Int): Int =
    if b == 0 then a
    else gcd(b, a % b)

  def apply(numerator: Int, denominator: Int): Rational = { 
    require(denominator != 0, "Denominator cannot be zero!")
    val d = gcd(numerator, denominator)
    val reducedN = numerator / d
    val reducedD = denominator / d
    
    if negative(numerator) && positive(denominator) then (reducedN, reducedD)
    else if negative(numerator) && negative(denominator) then (-reducedN, -reducedD)
    else if positive(numerator) && negative(denominator) then (-reducedN, -reducedD)
    else (reducedN, reducedD)
    
  }

  def numerator(rational: Rational): Int = rational._1

  def denominator(rational: Rational): Int = rational._2

  def add(first: Rational, second: Rational): Rational = Rational(
    numerator(first) * denominator(second) + numerator(second) * denominator(first),
    denominator(first) * denominator(second)
  )

  def subtract(first: Rational, second: Rational): Rational = Rational(
    numerator(first) * denominator(second) - numerator(second) * denominator(first),
    denominator(first) * denominator(second)
  )

  def multiply(first: Rational, second: Rational): Rational = Rational(
    numerator(first) * numerator(second),
    denominator(first) * denominator(second)
  )
  
  def divide(first: Rational, second: Rational): Rational = Rational(
    numerator(first) * denominator(second),
    denominator(first) * numerator(second)
  )

  def equal(first: Rational, second: Rational): Boolean =
    numerator(first) * denominator(second) == numerator(second) * denominator(first)
    
  def print(rational: Rational): Unit =
    println(s"${numerator(rational)}/${denominator(rational)}")
}
