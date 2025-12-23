package T1FunctionalProgramming

import language.StringFunctions.*

import scala.annotation.tailrec

object Lecture {
  def pi = 3.1415
  def square(x: Double): Double = x * x

  def plural(wd: String): String =
    if last(wd) == "y" then word(butlast(wd), "ies")
    else word(wd, "s")

  @tailrec
  def pigl(wd: String): String =
    def vowel(l: String): Boolean =
      Set("a", "e", "i", "o", "u").contains(l)

    if vowel(first(wd)) then word(wd, "ay")
    else pigl(word(bf(wd), first(wd)))



  @main def run(): Unit = {
    println(plural("fly"))
    println(plural("book"))
    println(pigl("scheme"))

  }
}
