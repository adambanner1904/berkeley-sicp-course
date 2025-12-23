package T1FunctionalProgramming
import T1FunctionalProgramming.Book.*
class BookSuite extends munit.FunSuite:
  test("sumOfThreeSquares"):
    assertEquals(sumOfThreeSquares(1, 2, 3), 14.0)