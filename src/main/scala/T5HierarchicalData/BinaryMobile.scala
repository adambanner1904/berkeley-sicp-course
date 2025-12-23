package T5HierarchicalData

enum BinaryMobile:
  case Mobile(left: Branch, right: Branch)
  case Branch(length: Double, structure: Mobile | Leaf)
  case Leaf(weight: Double)

  def totalWeight: Double = this match
    case Leaf(weight) => weight
    case Mobile(left, right) => left.totalWeight + right.totalWeight
    case Branch(length, structure) => structure.totalWeight

  def torque: Double = this match
    case Leaf(_) => 0.0
    case Mobile(_, _) => 0.0
    case Branch(length, structure) => length * structure.totalWeight

  def isBalanced: Boolean = this match
    case Mobile(left, right) => left.torque == right.torque && left.isBalanced && right.isBalanced
    case Branch(_, structure) => structure.isBalanced
    case Leaf(_) => true

object BinaryMobile:
  import BinaryMobile.*
  def main(args: Array[String]): Unit = {
    val bm = Mobile(Branch(1, Mobile(Branch(1, Leaf(2)), Branch(1, Leaf(2)))), Branch(1, Leaf(4)))
    println(bm)
    println(bm.totalWeight)
    println(bm.isBalanced)

  }