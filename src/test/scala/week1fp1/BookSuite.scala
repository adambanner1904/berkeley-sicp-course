package week1fp1
import week1fp1.Book.*
class BookSuite extends munit.FunSuite:
  test("sumOfThreeSquares"):
    assertEquals(sumOfThreeSquares(1, 2, 3), 14)