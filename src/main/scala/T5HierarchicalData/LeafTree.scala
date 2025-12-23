package T5HierarchicalData

import T3Processes.Book.fIterative
import language.CommonFunctions.*

enum LeafTree[+A]:
  case Branch(left: LeafTree[A], right: LeafTree[A])
  case Leaf(value: A)

  def reverse: LeafTree[A] = this match
    case Leaf(value) => this
    case Branch(left, right) => Branch(left.reverse, right.reverse)

  def fringe: List[A] =
    def iter(in: LeafTree[A], out: List[A]): List[A] = in match
      case Leaf(value) => value :: out
      case Branch(left, right) => out ::: left.fringe ::: right.fringe

    iter(this, Nil)

  def map[B](f: A => B): LeafTree[B] = this match
    case Leaf(value) => Leaf(f(value))
    case Branch(left, right) => Branch(left.map(f), right.map(f))



object LeafTree:
  def main(args: Array[String]): Unit = {
    val tree: LeafTree[Double] = Branch(Branch(Leaf(1), Leaf(2)), Branch(Leaf(3), Leaf(4)))
    println(tree)
    println(tree.reverse)
    println(tree.map(x => x * x).fringe)
  }