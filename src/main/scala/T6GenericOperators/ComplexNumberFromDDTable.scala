package T6GenericOperators
//
//import TypeTag.*
//import ComplexNumberRepresentation.*
//import language.CommonFunctions.square
//
//import math.{atan2, cos, sin, sqrt}
//import scala.collection.mutable
//
//val operationsTable = mutable.Map[(String, ComplexNumberRepresentation), AnyRef]()
//
//object ComplexNumberFromDDTable {
//  def put(opName: String, t: ComplexNumberRepresentation, item: AnyRef): Option[AnyRef] =
//    operationsTable.put((opName, t), item)
//
//  def get(opName: String, t: ComplexNumberRepresentation): Option[AnyRef] =
//    operationsTable.get((opName, t))
//
//  def installRectangularPackage(): Unit =
//    // internal
//    def realPart(z: ComplexNumber): Double = z._1
//    def imaginaryPart(z: ComplexNumber): Double = z._2
//    def makeFromRealImaginary(x: Double, y: Double): ComplexNumber = (x, y)
//    def magnitude(z: ComplexNumber): Double =
//      sqrt(square(realPart(z)) + square(imaginaryPart(z)))
//    def angle(z: ComplexNumber): Double =
//      atan2(imaginaryPart(z), realPart(z))
//    def makeFromMagAng(r: Double, a: Double) = (r * cos(a), r * sin(a))
//
//    // interface
//    def tag(x: ComplexNumber): TypedComplexNumber = attachTag(Rectangular, x)
//    put("realPart", Rectangular, realPart)
//    put("imaginaryPart", Rectangular, imaginaryPart)
//    put("magnitude", Rectangular, magnitude)
//    put("angle", Rectangular, angle)
//    put("makeFromRealImaginary", Rectangular, (x: Double, y: Double) => tag(makeFromRealImaginary(x, y)))
//    put("makeFromMagAng", Rectangular, (x: Double, y: Double) => tag(makeFromMagAng(x, y)))
//
//  def installPolarPackage(): Unit =
//    // internal
//    def magnitude(z: ComplexNumber): Double = z._1
//    def angle(z: ComplexNumber): Double = z._2
//    def makeFromMagAng(r: Double, a: Double) = (r, a)
//
//    def realPart(z: ComplexNumber): Double = magnitude(z) * cos(angle(z))
//    def imaginaryPart(z: ComplexNumber): Double = magnitude(z) * sin(angle(z))
//    def makeFromRealImaginary(x: Double, y: Double): ComplexNumber =
//      (sqrt(square(x) + square(y)), atan2(y, x))
//
//    // interface
//    def tag(x: ComplexNumber): TypedComplexNumber = attachTag(Polar, x)
//    put("realPart", Polar, realPart)
//    put("imaginaryPart", Polar, imaginaryPart)
//    put("magnitude", Polar, magnitude)
//    put("angle", Polar, angle)
//    put("makeFromRealImaginary", Polar, (x: Double, y: Double) => tag(makeFromRealImaginary(x, y)))
//    put("makeFromMagAng", Polar, (x: Double, y: Double) => tag(makeFromMagAng(x, y)))
//
////  def applyGeneric[A](opName: String, args: TypeTag*): A =
////    val typeTags = args.map(typeTag)
////    val proc = get(op, typeTag)
//
//}
