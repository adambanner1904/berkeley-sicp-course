package week1fp1

import language.SchemeSyntax.*

object Book:
  // exercise 1.2
  // (/ (+ 5
  //       4
  //       (- 2
  //          (- 3
  //             (+ 6
  //                (/ 4 5)))))
  //    (* 3
  //       (- 6 2)
  //       (- 2 7)))
  def square(x: Int): Int = x * x
  def sumOfThreeSquares(x: Int, y: Int, z: Int): Int =
    add(square(x), square(y), square(z))

  """Exercise 1.7
    | For small numbers say 1 x 10^6 any difference between it and
    | then next guess will be under the limit anyway and so will just return.
    |
    |
    |""".stripMargin