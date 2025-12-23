package language

type Number = Int | Double

object SchemeSyntax {
  def add(xs: Double*): Double = xs.sum
  def subtract(xs: Double*): Double = xs.head - xs.tail.sum
  def multiply(xs: Double*): Double = xs.product

  def add(xs: Int*): Int = xs.sum
  def subtract(xs: Int*): Int = xs.head - xs.tail.sum
  def multiply(xs: Int*): Int = xs.product
  
  
  
}
