package language

object Types:
  type Function = Double => Double
  type Combiner = (Double, Double) => Double
  type Predicate[A] = A => Boolean
  type Transformer = Function => Function