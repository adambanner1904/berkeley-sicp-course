package T5HierarchicalData.RepresentingSets

import scala.annotation.tailrec

@tailrec
def elementOfSet[A](e: A, set: List[A]): Boolean = {
  if set.isEmpty then false
  else if set.head == e then true
  else elementOfSet(e, set.tail)
}

def adjoinSet[A](e: A, set: List[A]): List[A] =
  if elementOfSet(e, set) then set
  else e :: set

def intersection[A](set1: List[A], set2: List[A]): List[A] = {
  if set1.isEmpty | set2.isEmpty then Nil
  else if elementOfSet(set1.head, set2) then set1.head :: intersection(set1.tail, set2)
  else intersection(set1.tail, set2)
}

def union[A](set1: List[A], set2: List[A]): List[A] = {
  if set1.isEmpty then set2
  else if set2.isEmpty then set1
  else if elementOfSet(set1.head, set2) then union(set1.tail, set2)
  else set1.head :: union(set1.tail, set2)
}

object SetAsList:
  def main(args: Array[String]): Unit = {
    val set = List(1, 2, 3)
    val anotherSet = List(3, 4, 5)
    println(elementOfSet(1, set))
    println(elementOfSet(1, anotherSet))
    println(intersection(set, anotherSet))
    println(union(set, anotherSet))
  }
