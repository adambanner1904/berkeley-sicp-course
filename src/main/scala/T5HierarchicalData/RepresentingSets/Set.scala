package T5HierarchicalData.RepresentingSets

trait Set[A]:
  def union(s: Set[A]): Set[A]
  def intersection(s: Set[A]): Set[A]
  def elementOfSet(e: A): Boolean
  def adjoinElement(e: A): Set[A]