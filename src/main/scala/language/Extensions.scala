package language

object Extensions:
  extension (n: Int)
    def isEven: Boolean = n % 2 == 0
    def isOdd: Boolean = n % 2 != 0
    def isZero: Boolean = n == 0
  extension (n: Double)
    def isEven: Boolean = n % 2 == 0
    def isOdd: Boolean = n % 2 != 0
    def isZero: Boolean = n == 0
    
    
