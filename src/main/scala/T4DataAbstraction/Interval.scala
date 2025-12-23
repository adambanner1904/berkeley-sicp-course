package T4DataAbstraction


case class Interval(lowerBound: Double, upperBound: Double):
  def center: Double = (upperBound + lowerBound) / 2
  def width: Double = (upperBound - lowerBound) / 2
  def percent: Double = (width / center) * 100.0
  infix def +(other: Interval): Interval = Interval(
    lowerBound + other.lowerBound,
    upperBound + other.upperBound
  )
  infix def -(other: Interval): Interval = Interval(
    lowerBound - other.upperBound,
    upperBound - other.lowerBound
  )
  infix def *(other: Interval): Interval =
    import scala.List
    val ps = List(
      lowerBound * other.lowerBound,
      lowerBound * other.upperBound,
      upperBound * other.lowerBound,
      upperBound * other.upperBound
    )
    Interval(ps.min, ps.max)
    
  infix def /(other: Interval): Interval = { 
    require(other.lowerBound > 0, "Cannot divide with an interval that spans zero")
    this * Interval(1.0 / other.upperBound, 1.0 / other.lowerBound)
  }

object Interval:
  def makeCenterWidth(center: Double, width: Double): Interval = 
    Interval(center - width, center + width)
    
  def makeCenterPercent(center: Double, percent: Double): Interval =
    makeCenterWidth(center, center * (percent / 100.0))