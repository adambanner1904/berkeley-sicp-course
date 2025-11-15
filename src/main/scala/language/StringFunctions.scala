package language


object StringFunctions {
  private def sentenceOrWord(s: String, ifWord: String => String, ifSentence: Seq[String] => String) =
    val list: Array[String] = s.split(" ")
    if list.length <= 1 then ifWord(s)
    else ifSentence(list)
  def first(s: String): String =
    // Depending on if the string is of the form "hello" or "now here" i.e. has spaces or not.
    // It gives the first character or first word
    sentenceOrWord(s, ifWord = _.head.toString, ifSentence = _.head)

  def last(s: String): String =
    // same as first
    sentenceOrWord(s, ifWord = _.last.toString, ifSentence = _.last)

  def butfirst(s: String): String =
    sentenceOrWord(s, ifWord = _.tail, ifSentence = _.tail.mkString(" "))

  def butlast(s: String): String =
    sentenceOrWord(s, ifWord = _.init, ifSentence = _.init.mkString(" "))

  def bf(s: String): String = butfirst(s)
  def bl(s: String): String = butlast(s)

  def word(strings: String*): String = strings.mkString("")
  def sentence(strings: String*): String = strings.mkString(" ")

}
