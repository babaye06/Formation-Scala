
@main def main(): Unit =
    
    println(10 compareTo 15)
    println(10 compareTo 10)
    println(10 compareTo 5)

    println("-" * 50)

trait OurComparable[T]{
    def compareTo(t: T): Int
  }