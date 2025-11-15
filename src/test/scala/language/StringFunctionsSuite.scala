package language

import language.StringFunctions.*

class StringFunctionsSuite extends munit.FunSuite:

  // word functions
  test("word concatenates all the words into a single word"):
    assertEquals(word("no", "where"), "nowhere")

  test("'first' of a word returns first letter"):
    assertEquals(first(word("no", "where")), "n")
    assertEquals(first("hello"), "h")

  test("'last' of a word returns last letter"):
    assertEquals(last(word("no", "where")), "e")
    assertEquals(last("hello"), "o")

  test("'butfirst' of a word returns all but the first letter of the word"):
    assertEquals(butfirst(word("no", "where")), "owhere")
    assertEquals(butfirst("hello"), "ello")

  test("'butlast' of a word returns all but the last letter of the word"):
    assertEquals(butlast(word("no", "where")), "nowher")
    assertEquals(butlast("hello"), "hell")

  private val helloWorld = sentence("hello", "world")
  private val noWhere = sentence("no", "where")
  private val thisString = sentence("this", "string", "has", "many", "words")
  // sentence functions
  test("'sentence' creates a sentence (space separated functions)"):
    assertEquals(noWhere, "no where")
    assertEquals(helloWorld, "hello world")

  test("'first' of a sentence returns first word"):
    assertEquals(first(noWhere), "no")
    assertEquals(first(helloWorld), "hello")

  test("'last' of a sentence returns last word"):
    assertEquals(last(noWhere), "where")
    assertEquals(last(helloWorld), "world")

  test("'butfirst' of a sentence returns all but the first word of the sentence"):
    assertEquals(butfirst(thisString), "string has many words")
    assertEquals(bf("the fool on the hill"), "fool on the hill")

  test("'butlast' of a sentence returns all but the last word of the sentence"):
    assertEquals(butlast(thisString), "this string has many")

  test("composing first with butfirst"):
    assertEquals(first(bf("a hard days night")), "hard")
    assertEquals(first(first(bf("she loves you"))), "l")