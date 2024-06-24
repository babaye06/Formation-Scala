
//Dans l'exemple de la feuille ordering
//Ordering.Int peut etre une 'given definition'

object Ordering:
    given Int: Ordering[Int] with
        def compare(x: Int, y: Int) =
            if x < y then -1 else if x > y then 1 else 0
// this code defines a given instance of type Ordering[Int, named Int]

// An alias can be used to define a given instance that is equal to some expression 
object IntOrdering extends Ordering[Int]:
    def compare(x: Int, y: Int): Int = 
        if x < y then -1 else if x > y then 1 else 0
        
given intOrdering: Ordering[Int] = IntOrdering  