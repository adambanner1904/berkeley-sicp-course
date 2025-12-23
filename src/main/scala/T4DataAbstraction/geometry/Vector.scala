package T4DataAbstraction.geometry

case class Vector(a: Double, b: Double):
  def +(other: Vector): Vector = Vector(a + other.a, b + other.b)
  def -(other: Vector): Vector = Vector(a - other.a, b - other.b)
  def *(scalar: Double): Vector = Vector(a * scalar, b * scalar)
  def magnitude: Double = {
    val square = math.pow(_, 2)
    math.sqrt(square(a) + square(b))
  }

  override def toString: String = s"($a, $b)"
