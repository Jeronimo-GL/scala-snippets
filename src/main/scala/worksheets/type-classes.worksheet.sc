
// Type class -> Definición de la funcionalidad que queremos crear
trait Greeter[A] {
  def greet(value: A): String
}

// Type class instance -> Implementaciones del type class
// Hay que hace un a por cada tipo que queremos soportar.

/*
 La implementación la podemos encapsular en un objeto como en este caso.
 Pero eso implica que tenemos que importar el objeto para que los implicits 
 estén en scope.
*/
object GreeterImplementation {
  implicit val stringGreeter: Greeter[String] =  new Greeter[String] {
    def greet(value: String) = {
      "Hello " + value
    }
  }
}

import GreeterImplementation._

// type class interface -> expone la funcionalidad del type class al usuario
object GreetInterface {
  def greet[A](value: A)(implicit  w: Greeter[A]) : String = {
    w.greet(value)
  }
}

GreetInterface.greet("Jero")


// Esto falla porque no hay una implementación Greeter con ints
// GreetSingleton.greet(10)

// Si en lugar de un objeto encapsulamos el interfaz en una clase implícita

implicit class GreeterSyntax[A](value: A) {
  def greet(implicit  w: Greeter[A]) : String = {
    w.greet(value)
  }
}

"Paco".greet

/* En realizad Paco es 

 GreeterSyntax[String]("Paco")

 Que es el que tiene el método greet. Que a su vez usa un implict para transformar "Paco"
 Pero no usa GreetInterface, son dos formas alternativas.
*/
