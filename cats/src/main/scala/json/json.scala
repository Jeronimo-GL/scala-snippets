package cats.json


/*
  Un AST  sencillo para representar estructuras JSON
*/
sealed trait Json

final case class JsObject(get: Map[String, Json]) extends Json
final case class JsString(get: String) extends Json
final case class JsNumber(get: Double) extends Json
final case object JsNull extends Json


/*
  Nuestro type class.
  Lo vamos a pasar para convertir nuestros tipos de datos a una estructura JSON
  del estilo Person -> JSON 
*/


trait JsonWriter[A] {
  def write(value: A): Json
}

/*
  Nuestro type class instance
  Vamos a escribir un type class instance para pasar de Person a JSON,
  pero primero tiene que pasar también de Strin a JsString.

  Vamos a encapsular todas las instances en un objeto JsonWriterInstances.
  Las instances se llaman implict values
*/

final case class Person(name: String, email: String)

object JsonWriterInstances {

  implicit val stringWriter: JsonWriter[String] =  new JsonWriter[String] {
    def write(value: String): Json =
      JsString(value)
  }

  implicit val PersonWriter: JsonWriter[Person] = new JsonWriter[Person] {
    def write(value: Person): Json =
      JsObject(Map[String, Json](
        "name" -> JsString(value.name),
        "email" -> JsString(value.email)
      ))
  }
}

/*
  Hay dos formas habituales de utilizar estos interfaces, con interface objects o interface synytax.
*/

/*          Interface objects
            =================

  Definimos un objeto a través de cual llamaremos a los interfaces de type class.

   En este caso el objeto Json tiene un método toJson. Que acepta objetos y a través del parámetro implícito 
   es capaz de generar objetos JSON
*/

object Json {
  def toJson[A](value: A)(implicit w: JsonWriter[A]):Json =
    w.write(value)
}

/*            Interace Syntax
              ===============

  Otra solución es implementas extension methods. En la práctica los extension methods permiten extender la
  funcionalidad de otras clases, añadiendo métodos que les permiten hacer cosas que inicialmente no podían,

 */

object JsonSyntax {
  implicit class JsonWriterOps[A](value: A) {
    def toJson(implicit w: JsonWriter[A]): Json =
      w.write(value)
  }
}

object Main extends App {

  import JsonWriterInstances._

  val p:Person = Person("Jero", "me@meail.com")
  val jp:Json = Json.toJson(p)

  println("Interface objects")
  println(p)
  println(jp)

  // también hay que importar JsonWriterInstances
  import JsonSyntax._

  println("Interface systax")

  val jjp: Json = p.toJson
  println(jjp)
}


