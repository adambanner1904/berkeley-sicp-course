package T6GenericOperators

import language.CommonFunctions.square

// local imports
import ComplexNumberRepresentation.*
import TypeTag.attachTag

type ComplexNumber = (Double, Double)
enum ComplexNumberRepresentation:
  case Rectangular, Polar
type TypedComplexNumber = TypeTag[ComplexNumberRepresentation, ComplexNumber]

object ComplexNumber:
  // selectors
  private def realPart(z: TypedComplexNumber): Double = {
    val (t, cn) = z
    t match
      case Rectangular => cn._1
      case Polar => magnitude(z) * math.cos(angle(z))
  }
  private def imaginaryPart(z: TypedComplexNumber): Double = {
    val (t, cn) = z
    t match
      case Rectangular => cn._2
      case Polar => magnitude(z) * math.sin(angle(z))
  }
  private def magnitude(z: TypedComplexNumber): Double = {
    val (t, cn) = z
    t match
      case Rectangular => math.sqrt(
        square(realPart(z)) + square(imaginaryPart(z))
      )
      case Polar => cn._1
  }
  private def angle(z: TypedComplexNumber): Double = {
    val (t, cn) = z
    t match
      case Rectangular => math.atan2(imaginaryPart(z), realPart(z))
      case Polar => cn._2
  }

  // constructors
  private def makeFromRealImaginary(re: Double, im: Double): TypedComplexNumber =
    attachTag(Rectangular, (re, im))
  private def makeFromMagAng(mag: Double, ang: Double): TypedComplexNumber =
    attachTag(Polar, (mag, ang))


  // operations & arithmetic
  def add(z1: TypedComplexNumber, z2: TypedComplexNumber): TypedComplexNumber =
    makeFromRealImaginary(
      realPart(z1) + realPart(z2),
      imaginaryPart(z1) + imaginaryPart(z2)
    )
  def subtract(z1: TypedComplexNumber, z2: TypedComplexNumber): TypedComplexNumber =
    makeFromRealImaginary(
      realPart(z1) - realPart(z2),
      imaginaryPart(z1) - imaginaryPart(z2)
    )
  def multiply(z1: TypedComplexNumber, z2: TypedComplexNumber): TypedComplexNumber =
    makeFromMagAng(
      magnitude(z1) * magnitude(z2),
      angle(z1) + angle(z2)
    )
  def divide(z1: TypedComplexNumber, z2: TypedComplexNumber): TypedComplexNumber =
    makeFromMagAng(
      magnitude(z1) / magnitude(z2),
      angle(z1) - angle(z2)
    )