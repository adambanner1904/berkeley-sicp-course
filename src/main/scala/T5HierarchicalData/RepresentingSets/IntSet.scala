package T5HierarchicalData.RepresentingSets

enum IntSet:
  case Branch(entry: Int, left: IntSet, right: IntSet)
  case Empty

  def elementOfSet(x: Int): Boolean = this match
    case Empty => false
    case Branch(entry, left, right) =>
      if x == entry then true
      else if x < entry then left.elementOfSet(x)
      else right.elementOfSet(x)

  def adjoinSet(x: Int): IntSet = this match
    case Empty => Branch(x, Empty, Empty)
    case Branch(entry, left, right) =>
      if x == entry then this
      else if x < entry then Branch(entry, left.adjoinSet(x), right)
      else Branch(entry, left, right.adjoinSet(x))

  // exercise 2.63
  def toList_v1: List[Int] = this match
    case Empty => Nil
    case Branch(entry, left, right) => left.toList_v1 ::: (entry :: right.toList_v1)

  def toList_v2: List[Int] =
    def iter(tree: IntSet, acc: List[Int]): List[Int] = tree match
      case Empty => acc
      case Branch(entry, left, right) => iter(left, entry :: iter(right, acc))

    iter(this, Nil)

object IntSet:
  def listToTree(list: List[Int]): IntSet = partialTree(list, list.length)._1

  private def partialTree(elements: List[Int], n: Int): (IntSet, List[Int]) =
    if n == 0 then (Empty, elements)
    else
      val leftSize: Int = (n - 1) / 2
      val (leftTree, nonLeftElements) = partialTree(elements, (n - 1) / 2)
      val (rightTree, remainingElements) = partialTree(nonLeftElements.tail, n - (leftSize + 1))
      (Branch(nonLeftElements.head, leftTree, rightTree), remainingElements)

  def main(args: Array[String]): Unit = {
    val tree = listToTree(List(1, 2, 3, 4, 5, 6, 7))
    println(tree)
    println(tree.toList_v1)
    println(tree.toList_v2)
  }