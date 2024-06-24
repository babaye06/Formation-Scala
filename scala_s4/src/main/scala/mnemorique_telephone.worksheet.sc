import java.{util => ju}
// On veut pouvoir diviser un numero en toutes les séquences possibles
def split(digits: String): Seq[Seq[String]] =
  if digits.isEmpty() then Seq(Nil)
  else
    for
      splitPoint <- 1 to digits.length
      (firstDigits, remainingDigits) = digits.splitAt(splitPoint)
      digitsSequence <- split(remainingDigits)
    yield firstDigits +: digitsSequence
end split

split("8379")

// On veut renvoyer toutes les phrases possibls correspondant à ce numero
def phrases(digits: String): Seq[Seq[String]] =
  if digits.isEmpty() then Seq(Nil)
  else
    for
      splitPoint <- 1 to digits.length
      (firstDigits, remainingDigits) = digits.splitAt(splitPoint)
      word <- index.getOrElse(firstDigits, Nil)
      words <- phrases(remainingDigits)
    yield word +: words
end phrases

val index: Map[String, Set[String]] = Map(
  "63" -> Set("of", "me"),
  "66" -> Set("on", "no")
)

phrases("63")

phrases("636")

phrases("6366")

// Construction un veritable index
val keys: Map[Char, String] = Map(
  '2' -> "ABC",
  '3' -> "DEF",
  '4' -> "GHI",
  '5' -> "JKL",
  '6' -> "MNO",
  '7' -> "PQRS",
  '8' -> "TUV",
  '9' -> "WXYZ"
)

val letterToDigit: Map[Char, Char] =
  for
    (digit, letters) <- keys
    letter <- letters
  yield letter -> digit

def wordToDigits(word: String): String =
  word.toUpperCase.map(letterToDigit)
end wordToDigits

//Class finale
class Mnemonics(dictionary: Set[String]):

  val keys: Map[Char, String] = Map(
    '2' -> "ABC",
    '3' -> "DEF",
    '4' -> "GHI",
    '5' -> "JKL",
    '6' -> "MNO",
    '7' -> "PQRS",
    '8' -> "TUV",
    '9' -> "WXYZ"
  )
  private val letterToDigit: Map[Char, Char] =
    for
      (digit, letters) <- keys
      letter <- letters
    yield letter -> digit
    
  private def wordToDigits(word: String): String =
    word.toUpperCase.map(letterToDigit)

  private val index: Map[String, Set[String]] =
    dictionary.groupBy(wordToDigits)

  def ofPhoneNumber(digits: String): Seq[Seq[String]] =
    if digits.isEmpty then Seq(Nil)
    else
      for
        splitPoint <- 1 to digits.length
        (firstDigits, remainingDigits) = digits.splitAt(splitPoint)
        word <- index.getOrElse(firstDigits, Nil)
        words <- ofPhoneNumber(remainingDigits)
      yield word +: words

end Mnemonics

val dictionary = Set(
  "Scala",
  "rocks",
  "is",
  "fun",
  "love",
  "thank",
  "me",
  "you",
  "of"
)

val mnemonics = Mnemonics(dictionary)

mnemonics.ofPhoneNumber("7225276257")
// res6: Seq[Seq[String]] = Vector(List(Scala, rocks))
mnemonics.ofPhoneNumber("7225247386")
// res7: Seq[Seq[String]] = Vector(List(Scala, is, fun))
mnemonics.ofPhoneNumber("7225284265968")
// res8: Seq[Seq[String]] = Vector(List(Scala, thank, you))
mnemonics.ofPhoneNumber("968568363")
// res9: Vector(List(you, love, me), List(you, love, of))