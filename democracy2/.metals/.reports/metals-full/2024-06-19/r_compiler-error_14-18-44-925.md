file://<WORKSPACE>/democracy/src/main/scala/democracy/MajorityJudgement.scala
### file%3A%2F%2F%2Fhome%2Fbtchinianga%2Flinux_workspace%2FFormation_scala%2Fdemocracy2%2Fdemocracy%2Fsrc%2Fmain%2Fscala%2Fdemocracy%2FMajorityJudgement.scala:17: error: `;` expected but `:` found
object Grade:
            ^

occurred in the presentation compiler.

presentation compiler configuration:
Scala version: 2.12.19
Classpath:
<HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/org/scala-lang/scala-library/2.12.19/scala-library-2.12.19.jar [exists ]
Options:



action parameters:
uri: file://<WORKSPACE>/democracy/src/main/scala/democracy/MajorityJudgement.scala
text:
```scala
package democracy

/**
 * A grade to assign to a candidate. There are seven possible grades (from
 * the worst to the best): `Bad`, `Mediocre`, `Inadequate`, `Passable`, `Good`,
 * `VeryGood`, and `Excellent`.
 *
 * Grades can be compared by using their `ordinal` method:
 *
 * {{{
 *   Grade.Mediocre.ordinal < Grade.Good.ordinal
 * }}}
 */



object Grade:
  /**
   * @return The median grade of a collection of grades.
   *
   * The median grade can be computed by sorting the collection
   * and taking the element in the middle. If there are an even
   * number of grades, any of the two grades that are just before
   * or after the middle of the sequence are correct median values.
   *
   * Grades can be compared by using their `ordinal` method.
   *
   * Hints: use the following operations:
   * - `sortBy` and `ordinal` to sort the collection of grades,
   * - `size` to compute the number of elements,
   * - `apply` to select an element at a specific index.
   */
  def median(grades: Seq[Grade]): Grade =
    grades.sortBy(grades => grades.ordinal)
    val median = (grades.size + 1)/2
    if grades.size == 1 then
      grades.head
    else
      if grades.size % 2 == 0 then
        grades.apply((grades.size/2)-1)
      else
        grades.apply(median)
end Grade

/**
 * A candidate in an election.
 * @param name (unique) name of the candidate (e.g., “Barack Obama”)
 */
case class Candidate(name: String)

/**
 * A ballot, which assigns a grade to each candidate of an election.
 * @param grades The grades assigned to each candidate
 */
case class Ballot(grades: Map[Candidate, Grade])

/**
 * An election is defined by a simple description and a set of possible
 * candidates.
 * @param description  Description of the election (e.g., “Presidential Election”)
 * @param candidates Possible candidates
 */
case class Election(description: String, candidates: Set[Candidate]):
  /**
   * @return The candidate that wins this election, according to the Majority
   *         Judgement voting process.
   *
   * @param ballots The ballots for this election
   *
   * The ballots ''must'' assign a grade to each of the `candidates` of this
   * election.
   */
  def elect(ballots: Seq[Ballot]): Candidate =
    assert(ballots.nonEmpty)
    assert(ballots.forall(_.grades.keySet == candidates))

    // Re-structure the data to get all the grades assigned to
    // each candidate by all the voters

    // First step: use the operation `flatMap` to flatten the ballots
    // into a single sequence containing the grades assigned to each
    // candidate by the voters.
    val allGrades: Seq[(Candidate, Grade)] =
      ballots.flatMap(ballot => ballot.grades)

    // Second step: use the operation `groupMap` to transform the
    // collection of pairs of `(Candidate, Grade)` into a `Map`
    // containing all the grades that were assigned to a given
    // `Candidate`.
    val gradesPerCandidate: Map[Candidate, Seq[Grade]] =
      allGrades.groupMap((candidate,_) => candidate)((_,grade) => grade)
  
    findWinner(gradesPerCandidate)
  end elect

  /**
   * @return The winner of this election, according to the Majority Judgement
   *         voting process.
   *
   * @param gradesPerCandidate The grades that have been assigned to each
   *                             candidate by the voters.
   */
  def findWinner(gradesPerCandidate: Map[Candidate, Seq[Grade]]): Candidate =
    // In case all the candidates have an empty collection of grades (this
    // can happen because of the tie-breaking algorithm, see below), the winner
    // is chosen by lottery from among the candidates.
    if gradesPerCandidate.forall((candidate, grades) => grades.isEmpty) then
      val candidatesSeq = gradesPerCandidate.keys.toSeq
      val randomIndex   = util.Random.between(0, candidatesSeq.size)
      candidatesSeq(randomIndex)
    else
      // Otherwise, find the highest median grade assigned to a candidate.
      // Use the operation `values` to select the collections of grades,
      // then use the operation `filter` to keep only the non empty grades,
      // then use the operation `map` to compute the median value of each collection
      // of grades, and finally use the operation `maxBy` to find the highest
      // median grade.
      val bestMedianGrade: Grade =
        gradesPerCandidate.values.filter(el => el.nonEmpty).map(grade => median(grade)).maxBy(medianGrade => medianGrade.ordinal)

      // Use the operation `filter` to select all the candidates that got the
      // same best median grade (as the case may be)
      val bestCandidates: Map[Candidate, Seq[Grade]] =
        (gradesPerCandidate.filter(l => l._2.nonEmpty)).filter(medianGrade => median(medianGrade._2) == bestMedianGrade)

      // In case only one candidate got the best median grade, it’s the winner!
      if bestCandidates.size == 1 then
        // Use the operation `head` to retrieve the only element
        // of the collection `bestCandidates`
        bestCandidates.head._1
      else
        // Otherwise, there is a tie between several candidates. The tie-breaking
        // algorithm is the following:
        // “If more than one candidate has the same highest median-grade, the winner is
        // discovered by removing (one-by-one) any grades equal in value to the shared
        // median grade from each tied candidate's total. This is repeated until only one
        // of the previously tied candidates is currently found to have the highest
        // median-grade.” (source: https://en.wikipedia.org/wiki/Majority_judgment)
  
        // Use the operation `map` to transform each element of the `bestCandidates`.
        // And use the operation `diff` to remove one `bestMedianGrade` from the
        // grades assigned to the candidates.
        val bestCandidatesMinusOneMedianGrade: Map[Candidate, Seq[Grade]] =
          bestCandidates.map(grade =>(grade._1, grade._2 diff Seq(bestMedianGrade)))
  
        // Finally, call `findWinner` on the reduced collection of candidates,
        // `bestCandidatesMinusOneMedianGrade`.
        findWinner(bestCandidatesMinusOneMedianGrade)
  end findWinner

end Election

```



#### Error stacktrace:

```
scala.meta.internal.parsers.Reporter.syntaxError(Reporter.scala:16)
	scala.meta.internal.parsers.Reporter.syntaxError$(Reporter.scala:16)
	scala.meta.internal.parsers.Reporter$$anon$1.syntaxError(Reporter.scala:22)
	scala.meta.internal.parsers.Reporter.syntaxError(Reporter.scala:17)
	scala.meta.internal.parsers.Reporter.syntaxError$(Reporter.scala:17)
	scala.meta.internal.parsers.Reporter$$anon$1.syntaxError(Reporter.scala:22)
	scala.meta.internal.parsers.ScalametaParser.scala$meta$internal$parsers$ScalametaParser$$expectAt(ScalametaParser.scala:389)
	scala.meta.internal.parsers.ScalametaParser.scala$meta$internal$parsers$ScalametaParser$$expectAt(ScalametaParser.scala:393)
	scala.meta.internal.parsers.ScalametaParser.expect(ScalametaParser.scala:395)
	scala.meta.internal.parsers.ScalametaParser.accept(ScalametaParser.scala:411)
	scala.meta.internal.parsers.ScalametaParser.acceptStatSep(ScalametaParser.scala:443)
	scala.meta.internal.parsers.ScalametaParser.acceptStatSepOpt(ScalametaParser.scala:445)
	scala.meta.internal.parsers.ScalametaParser.statSeqBuf(ScalametaParser.scala:4094)
	scala.meta.internal.parsers.ScalametaParser.bracelessPackageStats$1(ScalametaParser.scala:4288)
	scala.meta.internal.parsers.ScalametaParser.$anonfun$batchSource$11(ScalametaParser.scala:4300)
	scala.meta.internal.parsers.ScalametaParser.autoEndPos(ScalametaParser.scala:366)
	scala.meta.internal.parsers.ScalametaParser.$anonfun$batchSource$10(ScalametaParser.scala:4300)
	scala.meta.internal.parsers.ScalametaParser.tryParse(ScalametaParser.scala:206)
	scala.meta.internal.parsers.ScalametaParser.$anonfun$batchSource$1(ScalametaParser.scala:4292)
	scala.meta.internal.parsers.ScalametaParser.atPos(ScalametaParser.scala:319)
	scala.meta.internal.parsers.ScalametaParser.autoPos(ScalametaParser.scala:363)
	scala.meta.internal.parsers.ScalametaParser.batchSource(ScalametaParser.scala:4261)
	scala.meta.internal.parsers.ScalametaParser.$anonfun$source$1(ScalametaParser.scala:4255)
	scala.meta.internal.parsers.ScalametaParser.atPos(ScalametaParser.scala:319)
	scala.meta.internal.parsers.ScalametaParser.autoPos(ScalametaParser.scala:363)
	scala.meta.internal.parsers.ScalametaParser.source(ScalametaParser.scala:4255)
	scala.meta.internal.parsers.ScalametaParser.entrypointSource(ScalametaParser.scala:4259)
	scala.meta.internal.parsers.ScalametaParser.parseSourceImpl(ScalametaParser.scala:119)
	scala.meta.internal.parsers.ScalametaParser.$anonfun$parseSource$1(ScalametaParser.scala:116)
	scala.meta.internal.parsers.ScalametaParser.parseRuleAfterBOF(ScalametaParser.scala:58)
	scala.meta.internal.parsers.ScalametaParser.parseRule(ScalametaParser.scala:53)
	scala.meta.internal.parsers.ScalametaParser.parseSource(ScalametaParser.scala:116)
	scala.meta.parsers.Parse$.$anonfun$parseSource$1(Parse.scala:30)
	scala.meta.parsers.Parse$$anon$1.apply(Parse.scala:37)
	scala.meta.parsers.Api$XtensionParseDialectInput.parse(Api.scala:22)
	scala.meta.internal.semanticdb.scalac.ParseOps$XtensionCompilationUnitSource.toSource(ParseOps.scala:15)
	scala.meta.internal.semanticdb.scalac.TextDocumentOps$XtensionCompilationUnitDocument.toTextDocument(TextDocumentOps.scala:179)
	scala.meta.internal.pc.SemanticdbTextDocumentProvider.textDocument(SemanticdbTextDocumentProvider.scala:54)
	scala.meta.internal.pc.ScalaPresentationCompiler.$anonfun$semanticdbTextDocument$1(ScalaPresentationCompiler.scala:462)
```
#### Short summary: 

file%3A%2F%2F%2Fhome%2Fbtchinianga%2Flinux_workspace%2FFormation_scala%2Fdemocracy2%2Fdemocracy%2Fsrc%2Fmain%2Fscala%2Fdemocracy%2FMajorityJudgement.scala:17: error: `;` expected but `:` found
object Grade:
            ^