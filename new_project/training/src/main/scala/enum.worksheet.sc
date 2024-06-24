object WeekDay extends Enumeration {
  type WeekDay = Value
  val Monday, Tuesday, Wednesday, Thursday, Friday, Saturday, Sunday = Value
}

// Utilisation de l'énumération
import WeekDay._

val today: WeekDay = WeekDay.Monday
println(today) // Affiche "Monday"

//Exemple 2
enum  PrimaryColor:
    case Red, Blue, Green

//Enumerer toutes les valeurs possibles
PrimaryColor.values
//Trouver une valeur de la liste
PrimaryColor.valueOf("Green")

def isProblematicForColorBlindPeople(color: PrimaryColor): Boolean =
    color match
        case PrimaryColor.Blue => false
        case PrimaryColor.Red => true
        case PrimaryColor.Green => true 
end isProblematicForColorBlindPeople
    
val color = PrimaryColor.Blue
isProblematicForColorBlindPeople(color)
