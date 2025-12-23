package T4DataAbstraction

import scala.annotation.tailrec

enum List[+A]:
  case Cons(head: A, tail: List[A])
  case Nil

  def apply(n: Int): A = this match
    case Nil => throw new RuntimeException("Index out of bounds")
    case Cons(head, tail) =>
      if n == 0 then head
      else tail(n-1)

  def length: Int = this match
    case Nil => 0
    case Cons(_, tail) => 1 + tail.length

  def append[B >: A](other: List[B]): List[B] = this match
    case Nil => other
    case Cons(head, tail) => Cons(head, tail.append(other))

  def last: A = this match
    case Nil => throw new RuntimeException("Cannot call last on an empty list")
    case Cons(head, tail) =>
      tail match
        case Nil => head
        case _ => tail.last

  def reverse: List[A] = {
    @tailrec
    def iter(in: List[A], out: List[A]): List[A] = in match
      case Nil => out
      case Cons(head, tail) => iter(tail, Cons(head, out))
    iter(this, Nil)
  }

  def map[B](f: A => B): List[B] = this match
    case Nil => Nil
    case Cons(head, tail) => Cons(f(head), tail.map(f))

  def foreach(f: A => Unit): Unit = this match
    case Nil => ()
    case Cons(head, tail) =>
      f(head)
      tail.foreach(f)

object List:
  def apply[A](as: A*): List[A] =
    if as.isEmpty then Nil
    else Cons(as.head, apply(as.tail*))

