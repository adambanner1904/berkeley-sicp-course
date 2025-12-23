package T5HierarchicalData


import T4DataAbstraction.List
import T4DataAbstraction.List.{Cons, Nil}

enum Tree[+A]:
  case Branch(value: A, children: List[Tree[A]])
  case Leaf(value: A)
  case Empty

  def reverse: Tree[A] = this match
    case Empty => Empty
    case Leaf(value) => this
    case Branch(value, children) => Branch(value, children.reverse.map(_.reverse))



