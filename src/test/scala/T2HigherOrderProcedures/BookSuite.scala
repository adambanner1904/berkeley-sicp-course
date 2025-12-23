package T2HigherOrderProcedures

import T2HigherOrderProcedures.Book.*
import T2HigherOrderProcedures.Book133.*

class BookSuite extends munit.FunSuite:
  test("sumCubes"):
    assertEquals(sumCubes(1, 10), 3025.0)

  test("isPrime"):
    assert(!isPrime(4))
    assert(isPrime(7))
    assert(isPrime(101))

  test("sumPrimes"):
    assertEquals(sumPrimes(4, 10), 12.0)
    assertEquals(sumPrimes(4, 12), 23.0)

  test("gcd"):
    assertEquals(gcd(4, 8), 4)
    assertEquals(gcd(22, 33), 11)

  test("average"):
    assertEquals(average(2.0, 4.0), 3.0)
