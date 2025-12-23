package T5HierarchicalData

import language.CommonFunctions.{fib, isPrime, square}
import language.Extensions.*
import language.Types.Predicate
import LeafTree.*

import scala.annotation.tailrec

object Book:

  def filter[A](predicate: Predicate[A], seq: List[A]): List[A] = seq match
    case Nil => seq
    case head :: tail if predicate(head) => head :: filter(predicate, tail)
    case _ :: tail => filter(predicate, tail)

  def accumulate[E, R](op: (E, R) => R, initial: R, seq: List[E]): R = seq match
    case Nil => initial
    case head :: tail =>
      op(head, accumulate(op, initial, tail))

  def enumerateInterval(low: Int, high: Int): List[Int] =
    if low > high then Nil
    else low :: enumerateInterval(low + 1, high)

  def enumerateTree(tree: LeafTree[Int]): List[Int] = tree match
    case Leaf(value) => List(value)
    case Branch(left, right) => enumerateTree(left) ::: enumerateTree(right)

  def sumOddSquares(tree: LeafTree[Int]): Double =
    accumulate[Int, Int](_ + _, 0, map(square, filter(_.isOdd, enumerateTree(tree))))

  def evenFibs(n: Int): List[Int] =
    accumulate[Int, List[Int]](_ :: _, Nil, filter(_.isEven, map(fib, enumerateInterval(0, n))))

  def listFibSquares(n: Int): List[Int] =
    accumulate[Int, List[Int]](_ :: _, Nil, map(square, (map(fib, enumerateInterval(0, n)))))

  def productOfSquaresOfOddElements(sequence: List[Int]): Int =
    accumulate[Int, Int](_ * _, 1, map(square, filter(_.isOdd, sequence)))

  case class Record(occupation: String, salary: Int)

  def salaryOfHighestPaidProgrammer(records: List[Record]): Int =
    accumulate[Int, List[Int]](_ :: _, Nil, map[Record, Int](_.salary, filter[Record](_.occupation == "Programmer", records))).max

  // exercise 2.33
  def map[A, B](f: A => B, seq: List[A]): List[B] =
    println(s"Mapping function onto $seq")
    val acc = foldLeft[A, List[B]]((x, y) => f(x) :: y, Nil, seq)
    println(s"Map returns $acc")
    acc


  def append[E](seq1: List[E], seq2: List[E]): List[E] =
    accumulate[E, List[E]](_ :: _, seq2, seq1)

  def length[A](seq: List[A]): Int =
    accumulate[A, Int]((_, y) => 1 + y, 0, seq)

  // exercise 2.34
  def hornersEval(x: Double, coefficientList: List[Double]): Double =
    accumulate[Double, Double]((higherTerm, thisCoefficient) => higherTerm * x + thisCoefficient, 0.0, coefficientList)

  // exercise 2.35
  def countLeaves[A](tree: LeafTree[A]): Int =
    accumulate[A, Int]((_, b) => b + 1, 0, tree.fringe)

  // exercise 2.36
  def accumulateN[E, R](op: (E, R) => R, init: R, lists: List[List[E]]): List[R] =
    if lists.head.isEmpty then Nil
    else accumulate[E, R](op, init, lists.map(_.head)) :: accumulateN(op, init, lists.map(_.tail))

  // exercise 2.37
  type Vector = List[Int]
  type Matrix = List[Vector]

  def dotProduct(v: Vector, w: Vector): Int =
    accumulate[Int, Int](_ + _, 0, v.flatMap(x => w.map(y => x * y)))

  def matrixXVector(m: Matrix, v: Vector): Vector =
    m.map(vector => dotProduct(v, vector))

  // exercise 2.38
  def foldLeft[E, R](op: (E, R) => R, init: R, list: List[E]): R =
    @tailrec
    def iter(result: R, rest: List[E]): R =
      if rest.isEmpty then result
      else iter(op(rest.head, result), rest.tail)

    iter(init, list)

  def reverseFoldRight[A](list: List[A]): List[A] =
    accumulate[A, List[A]]((x, y) => y ::: List(x), Nil, list)

  def reverseFoldLeft[A](list: List[A]): List[A] =
    foldLeft[A, List[A]](_ :: _, Nil, list)

  def flatmap[A, B](proc: A => List[B], list: List[A]): List[B] = {
    println(s"Flat mapping function onto $list")
    val acc = foldLeft[List[B], List[B]](_ ::: _, Nil, map[A, List[B]](proc, list))
    println(s"Flat map returns $acc")
    acc
  }

  def primeSum(pair: (Int, Int)): Boolean = isPrime(pair._1 + pair._2)

  def makePairSum(pair: (Int, Int)): (Int, Int, Int) = (pair._1, pair._2, pair._1 + pair._2)

  def primeSumPairs(n: Int): Seq[(Int, Int, Int)] =
    map(
      makePairSum,
      filter[(Int, Int)](
        primeSum,
        uniquePairs(n)
      )
    )


  def permutations(s: List[Int]): List[List[Int]] = {
    println(s"Calling permutations on $s")
    if s.isEmpty then List(Nil)
    else flatmap[Int, List[Int]](
      x => map[List[Int], List[Int]](
        p => x :: p,
        permutations(s.filterNot(i => i == x))
      ),
      s
    )
  }

  def uniquePairs(n: Int): List[(Int, Int)] =
    flatmap[Int, (Int, Int)](
      i => map[Int, (Int, Int)](
        j => (i, j),
        enumerateInterval(1, i - 1)
      ),
      enumerateInterval(1, n)
    )

  private type Triple = (Int, Int, Int)
  def uniqueTriples(n: Int): List[Triple] = {
    val numbers = enumerateInterval(1, n)
    flatmap[Int, Triple](
      i => flatmap[Int, Triple](
        j => map[Int, Triple](
          k => (i, j, k),
          numbers
        ),
        numbers
      ),
      numbers
    )
  }

//  def queens(boardSize: Int): List[?] =
//    def queenCols(k: Int): List[?] =
//      if k.isZero then Nil
//      else filter(
//        positions => isSafe(k, positions),
//        flatmap(
//          restOfQueens => map(
//            newRow => adjoinPosition(newRow, k, restOfQueens),
//            enumerateInterval(1, boardSize)
//          ),
//          queenCols(k - 1)
//        )
//      )
//
//    queenCols(boardSize)


  def main(args: Array[String]): Unit = {
//    val tree: LeafTree[Int] = Branch(Branch(Leaf(1), Leaf(2)), Branch(Leaf(3), Leaf(4)))
//    println(sumOddSquares(tree))
//    println(evenFibs(10))
//    println(listFibSquares(10))
//    println(productOfSquaresOfOddElements(List(1, 2, 3, 4, 5)))
//    println(append(List(1, 2, 3), List(4, 5, 6))) // append
//    println(length(append(List(1, 2, 3), List(4, 5, 6)))) // length
//    println(hornersEval(2, List(1, 3, 0, 5, 0, 1)))
//    println("Hello World")
//    println(countLeaves(tree))
//    val lol = List(List(1, 2, 3), List(4, 5, 6), List(7, 8, 9), List(10, 11, 12))
//    println(accumulateN[Int, Int](_ + _, 0, lol)) // works lol
//    val matrix: Matrix = List(List(1, 2, 3, 4), List(5, 6, 7, 8), List(9, 10, 11, 12))
//    val vector: Vector = List(1, 2, 3)
//    println(matrixXVector(matrix, vector))
//    println(accumulate[Int, Double](_ / _, 1, List(1, 2, 3)))
//    println(foldLeft[Int, Double](_ / _, 1, List(1, 2, 3)))
//    println(accumulate[Int, List[Int]](_ :: _, Nil, List(1, 2, 3)))
//    println(foldLeft[Int, List[Int]](_ :: _, Nil, List(1, 2, 3)))
//    println(reverseFoldRight(List(1, 2, 3)))
//    println(reverseFoldLeft(List(1, 2, 3)))
//    println(makePairs(6))
//      println(permutations(List(1, 2)))
//    println(primeSumPairs(4))
    val sumToFive = filter[Triple](
      t => t.toList.sum == 5,
      uniqueTriples(3)
    )
    println(sumToFive)
  }

