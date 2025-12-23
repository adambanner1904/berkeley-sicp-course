package T5HierarchicalData.scheme1

enum Expression:
  // atomic
  case SelfEvaluating
  case Variable
  // list
  case ProcedureCall
  case SpecialForm