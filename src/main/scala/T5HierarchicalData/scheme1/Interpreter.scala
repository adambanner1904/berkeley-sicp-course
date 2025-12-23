package T5HierarchicalData.scheme1

trait Interpreter:
  def eval(expr: Expression): AnyVal
  def main(args: Array[String]): Unit

