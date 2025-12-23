package T5HierarchicalData


enum Expr:
  case Number(value: Double)
  case Variable(value: Char)
  case Sum(addend: Expr, augend: Expr)
  case Product(multiplier: Expr, multiplicand: Expr)
  case Power(base: Expr, exponent: Expr)

  def differentiate(variable: Variable): Expr = this match
    case Number(_) => Number(0)
    case v @ Variable(_) if v != variable => Number(0)
    case Variable(_) => Number(1)
    case Sum(u, v) => makeSum(u.differentiate(variable), v.differentiate(variable))
    case Product(u, v) =>
      makeSum(
        makeProduct(u, v.differentiate(variable)),
        makeProduct(v, u.differentiate(variable))
      )
    case Power(u, n) => makeProduct(
      n,
      makeProduct(
        Power(u, makeSum(n, Number(-1))),
        u.differentiate(variable)
      )
    )


  override def toString: String = this match
    case Number(value) => value.toString
    case Variable(value) => value.toString
    case Sum(a, b) => s"$a + $b"
    case Product(a, b) => s"$a * $b"
    case Power(b, n) => s"$b ** $n"

import Expr.*
def makeSum(a: Expr, b: Expr): Expr = (a, b) match
  case (Number(0), _) => b
  case (_, Number(0)) => a
  case (Number(aVal), Number(bVal)) => Number(aVal + bVal)
  case _ => Sum(a, b)

def makeProduct(a: Expr, b: Expr): Expr = (a, b) match
  case (Number(0), _) | (_, Number(0)) => Number(0)
  case (Number(1), _) => b
  case (_, Number(1)) => a
  case (Number(aVal), Number(bVal)) => Number(aVal * bVal)
  case _ => Product(a, b)

def makePower(b: Expr, n: Expr): Expr = (b, n) match
  case (_, Number(0)) => Number(1)
  case (_, Number(1)) => b
  case _ => Power(b, n)

object SymbolicDifferentiation:
  def main(args: Array[String]): Unit = {
    import Expr.*
    val expression = Sum(Variable('x'), Number(3))
    println(expression.differentiate(Variable('x')))
    val expression2 = Product(Variable('x'), Variable('y'))
    println(expression2.differentiate(Variable('x')))
    val expression3 = Power(Variable('x'), Variable('n'))
    println(expression3)
    println(expression3.differentiate(Variable('x')))
  }