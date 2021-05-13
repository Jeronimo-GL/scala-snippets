package cats.json

// Ejercicio del capitulo 1.3

trait Printable[A] {
  def format(value: A) : String
}

object PrintableInstances {
  implicit val printableString: Printable[String] = new Printable[String] {
    def format(value: String): String = {
      value
    }
  }

  implicit val printableInt: Printable[Int] = new Printable[Int] {
    def format(value: Int): String = {
      value.toString()
    }
  }
}


object Printable {
  def format[A](value:A)(implicit p: Printable[A]):String = {
    p.format(value)
  }

  def print[A](value: A)(implicit p: Printable[A]):Unit = {
    println(p.format(value))
  }
}

object Exercise_1 {
  def run():Unit = {
    import PrintableInstances._

    println(Printable.format(42))
    println(Printable.format("Hola mundo"))

    Printable.print("Hello world")
  }
}

object MainPrintable extends App {
//  Exercise_1.run()
  CatApp.run()
}


/*
   Para poder utilizar los métodos del objeto Printable (print y format), solo necesitamos
   definir la type instance (catPrintable) que se pueda utilizar Cat con printable implementando
   el método format definido en el type class
*/
object CatApp  {

  case class Cat(name: String, age: Int, color: String)
  import PrintableInstances._

  // Con interface object
  implicit val catPrintable: Printable[Cat] = new Printable[Cat] {
    def format(c: Cat): String = {
      val name = Printable.format(c.name)
      val age = Printable.format(c.age)
      val color = Printable.format(c.color)
      s"$name is $age years old $color cat"
    }
  }


  // Con interface syntax

  import Printable._

  object PrintableSyntax {
    implicit class PrintableOps[A](value: A) {
      def format(implicit pr: Printable[A]):String = {
        pr.format(value)
      }

      def print(implicit pr: Printable[A]):Unit = {
        println(format(pr))
      }
    }
  }

  def run():Unit = {
    val garfield = Cat("Garfield", 5, "orange")
    Printable.print(garfield)

    import PrintableSyntax._

    // Ojo con no ponerle los paréntesis. Si se los pones, espera que le pases el catPrintable
    garfield.print
  }
}
