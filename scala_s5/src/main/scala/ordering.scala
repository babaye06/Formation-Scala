import scala.math.Ordering
// Ordering est un trait  qui fournit des fonctionnalités pour la comparaison d'objets. 
// Il est utilisé pour définir un ordre sur les éléments d'un type donné, ce qui permet de les trier ou de les comparer.


// On veut eviter cela car on doit indiquer le type a chaque fois 
sort(xs) (Ordering.Int)
sort(strings)(Ordering.String)

// Donc on definit sort et xs comme suit 
def sort [A](xs: List[A])(using ord: Ordered[A]): List[A]= ...

val xs: List[Int] = ...

// Ainsi on pourra l'utiliser comme suit 
sort(xs)
sort[Int](xs)

// Correspondant à 
sort[Int](xs)(using Ordering.Int)

object Ordering:
    val Int = new Ordered[Int]:  //Quand on fait ordering.int on parle de ce int la 
        def compare(x: Int, y: Int) =
            if x < y then -1 else if x > y then 1 else 0

// Using CLauses Syntax Reference 

def f(x: Int)(using a: A, b: B) = ...
f(x)(using a, b)

// Context bounds, shorter syntax for context parameters
def sort[A: Ordering](xs: List[A]): List[A] = ...

//Equivalent à 
def sort[A](xs: List[A])(using Ordering[A]): List[A]