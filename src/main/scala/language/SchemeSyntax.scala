package language

object SchemeSyntax {
  def add(xs: Int*): Int = xs.sum
  def add(xs: Double*): Double = xs.sum
  def subtract(xs: Int*): Int = xs.head - xs.tail.sum
  def multiply(xs: Int*): Int = xs.product
}
