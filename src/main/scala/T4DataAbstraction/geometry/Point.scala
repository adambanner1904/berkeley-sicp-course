package T4DataAbstraction.geometry

/*(1, 2) + (3, 4) = (4, 6)*/
case class Point(x: Double, y: Double):
  override def toString: String = s"($x, $y)"


object Point:
  def distanceBetween(a: Point, b: Point): Double = LineSegment(a, b).magnitude