package T4DataAbstraction

import language.CommonFunctions.*

import scala.annotation.tailrec

type Point = (Double, Double)
type Line = (Point, Point)
type Rectangle = (Line, Line)
type RectangleFromPoints = (Point, Point)
enum Dimension:
  case X, Y
  
import Dimension.*

object Point:
  def apply(x: Double, y: Double): Point = (x, y)
def x(point: Point): Double = point._1
def y(point: Point): Double = point._2
def print(point: Point): Unit = println(s"(${x(point)}, ${y(point)})")

object Line:
  def apply(start: Point, end: Point): Line = (start, end)
def start(line: Line): Point = line._1
def end(line: Line): Point = line._2
def midpoint(line: Line): Point = Point(
  average(x(start(line)), x(end(line))),
  average(y(start(line)), y(end(line)))
)
def length(line: Line, dimension: Dimension): Double =
  val (start, end) = line
  dimension match
    case X => x(end) - x(start)
    case Y => y(end) - y(start)




object Rectangle:
  def apply(horizontal: Line, vertical: Line): Rectangle = (horizontal, vertical)
def horizontal(rectangle: Rectangle): Line = rectangle._1
def vertical(rectangle: Rectangle): Line = rectangle._2
def width(rectangle: Rectangle): Double =
  val (horizontalLine, _) = rectangle
  length(horizontalLine, X)
def height(rectangle: Rectangle): Double =
  val (_, verticalLine) = rectangle
  length(verticalLine, Y)

def perimeter(rectangle: Rectangle): Double =
  2 * height(rectangle) + 2 * width(rectangle)

def area(rectangle: Rectangle): Double =
  height(rectangle) * width(rectangle)





@main def run(): Unit =
  val start = Point(1, 4)
  val end = Point(4, 10)
  print(midpoint(Line(start, end)))

  val horizontalLine = Line(Point(0, 0), Point(10, 0)) // Width of 10
  val verticalLine = Line(Point(0, 0), Point(0, 5)) // Height of 5
  
  val rectangle = Rectangle(horizontalLine, verticalLine)
  println(s"Perimeter of the rectangle: ${perimeter(rectangle)}")
  println(s"Area of the rectangle: ${area(rectangle)}")

