// Definition du trait 
trait Greet {
    def greet(): Unit = {
        println("Hello  World")
    }
}

// Utilisation du trait dans une classe
class MyClass extends Greet

//Creation d'une instance de la classe
val obj = new MyClass()

// Appel de la méthode définie dans le trait 
obj.greet()


//Exemple 2
sealed trait Shape
case class Rectangle(width: Int, height: Int) extends Shape
case class Circle(radius: Int) extends Shape

val someShape: Shape = Rectangle(3,2)
val someShapeArea =
    someShape match
        case Rectangle(width,height) => width * height
        case Circle(radius)          => radius * radius * 3.14



//Exemple 3
sealed trait Action
case class Subscribe(channel: Channel) extends Action {
    def subscribeMessage: String =
        s"Subscribed to channel ${channel.name}"
}
case class Unsubscribe(channel: Channel) extends Action {
    def subscribeMessage: String =
        s"Unsubscribed to channel ${channel.name}"
}
case class PostMessage(channel: Channel, message: String) extends Action

case class Channel(name: String)

val subscribeEffectiveScala = 
    Subscribe(Channel("effective-scala"))

val Dofus_forum = Channel("Dofus")
val subscribeAndPostDofus_frm = 
    val subscription = Subscribe(Dofus_forum)
    println(subscription.subscribeMessage)
    subscription
    //PostMessage(Dofus_forum,"Hi I'm Eca om.9")