package T4DataAbstraction.geometry

case class LineSegment(a: Point, b: Point):
  def magnitude: Double = {
    val square = math.pow(_, 2)
    math.sqrt(square(b.x - a.x) + square(b.y - a.y))
  }
  def midpoint: Point = Point(
    (a.x + b.x) / 2,
    (a.y + b.y) / 2
  )
  def length: Double = b.x - a.x
  def height: Double = b.y - a.y
  def slope: Double = height / length
  def toLine: Line = x => 
    val m = slope
    val c = a.y - m * a.x
    m * x + c
  