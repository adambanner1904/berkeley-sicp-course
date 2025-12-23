package T4DataAbstraction


object Book {

  // exercise 2.6
  // Church numerals. Not sure how to translate the scheme here to Scala but
  // I remember that this was in the Scala course so will investigate this again there.

  def main(args: Array[String]): Unit = {

    val list = List(1, 2, 3, 4)
    println(list(1))
    println(list.length)

    val odds = List(1, 3, 5, 7)
    val squares = List(1, 4, 9, 16, 25)
    println(odds.append(squares))
    println(odds.last)
    println(odds.reverse)
//    println(odds.map(x => x * x))
//    odds.foreach(println)
  }
}
