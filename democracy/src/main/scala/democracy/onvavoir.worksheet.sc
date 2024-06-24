enum Grade:
  case Bad, Mediocre, Inadequate, Passable, Good, VeryGood, Excellent
end Grade

val liste = Seq(
    Grade.Good, Grade.Passable, Grade.Excellent, Grade.VeryGood,
    Grade.Bad, Grade.Inadequate, Grade.Mediocre, Grade.Bad, Grade.Mediocre, 
    Grade.VeryGood, Grade.Passable, Grade.Excellent, Grade.Good, Grade.Bad
)

val liste2 = Seq(
  Grade.Inadequate
)
liste.apply(13)
liste.size % 2 
median(liste)
median(liste2)

def median(liste: Seq[Grade]): Grade = 
    val new_liste = liste.sortBy(_.ordinal)
    val size = new_liste.size
    if liste.size == 1 then
      new_liste.head
    else if size %2 == 0 then
        new_liste.apply(((size-1)/2))
    else
        new_liste.apply(((size+1)/2)-1)
end median

case class Candidate(name: String)

case class Ballot(grades: Map[Candidate, Grade])

val ballot0 = Ballot(Map(
  Candidate("Barack Obama") -> Grade.Bad,
  Candidate("Donald Trump") -> Grade.Mediocre
))
val ballot1 = Ballot(Map(
  Candidate("Barack Obama") -> Grade.Good,
  Candidate("Donald Trump") -> Grade.Excellent
))
val ballot2 = Ballot(Map(
  Candidate("Barack Obama") -> Grade.Inadequate,
  Candidate("Donald Trump") -> Grade.Passable
))
val ballot3 = Ballot(Map(
  Candidate("Barack Obama") -> Grade.Inadequate,
  Candidate("Donald Trump") -> Grade.Good
))
val ballot4 = Ballot(Map(
  Candidate("Barack Obama") -> Grade.Inadequate,
  Candidate("Donald Trump") -> Grade.Passable
))

val urne = Seq(
    ballot0, ballot1, ballot2,ballot3,ballot4
)

val depouillement: Seq[(Candidate, Grade)] =
    urne.flatMap(_.grades.toSeq)

val depouillement_filtre: Map[Candidate, Seq[Grade]] =
    depouillement.groupMap(_._1)(_._2)

val bestMedianGrade: Grade =
  depouillement_filtre.values          // Sélectionnez les collections de grades
    .filter(_.nonEmpty)                // Filtrez les collections non vides
    .map(median)                       // Calculez la valeur médiane de chaque collection
    .maxBy(_.ordinal) 

val bestCandidates: Map[Candidate, Seq[Grade]] =
        depouillement_filtre.filter { case (_, grades) =>
          median(grades) == bestMedianGrade // Vérifie si la médiane des notes est égale à la meilleure note médiane
  }

val (bestCandidate, _) = bestCandidates.head
bestCandidate