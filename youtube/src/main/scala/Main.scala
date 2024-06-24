import scala.
@main def hello(): Unit =
  println("- "*50)
  println(10 compareTo 15)
  println(10 compareTo 10)
  println(10 compareTo 5)
/*
  //Ou alors
  println("- "*50)
  println(Person(10) compareTo Person(15))
  println(Person(10) compareTo Person(10))
  println(Person(10) compareTo Person(5))
*/
  val ordering = implicitly[Ordering[Person]]
  println(List(Person(10), Person(5), Person(15)).sorted)
  println(List(Person(10), Person(5), Person(15)).sorted(Person.Ordering)) 
  println(List(Person(10), Person(5), Person(15)).sorted(Person.Ordering.reverse)) 

final case class Person(age: Int) //extends Ordered[Person]{
//  final override def compareTo(that: Person): Int =
//    this.age compare that.age
//}

object Person {
  implicit val Ordering: Ordering[Person] = new Ordering[Person] {
    final override def compare(t1: Person, t2: Person): Int = {
      println("Yes it's actually this one")
      t1.age compare t2.age
    }
  }
}

trait OurOwnComparable[T]{
  def compareTo(t: T): Int
}