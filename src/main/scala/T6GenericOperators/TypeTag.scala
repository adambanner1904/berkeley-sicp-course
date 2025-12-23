package T6GenericOperators

type TypeTag[Type, A] = (Type, A)

object TypeTag:
  // selectors
  def typeTag[Type](tt: TypeTag[Type, _]): Type = tt._1
  def contents[A](tt: TypeTag[_, A]): A = tt._2

  // constructor
  def attachTag[Type, A](t: Type, contents: A): TypeTag[Type, A] = (t, contents)
