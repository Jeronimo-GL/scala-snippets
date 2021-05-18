import $dep.`org.typelevel::cats-core:2.2.0`

import cats.Monoid
import cats.instances.string._     // para tener el monoide de strings
import cats.syntax.semigroup._     // para tener|+|


val sum:String  = "hola" |+| "mundo"

/*
  Pero no podemos hacer lo mismo con enteros por ejemplo hasta que no 
  importemos la instancia para enteros.
 */


/*
  Si queremos hacerlo por ejemplo para una clase que represente órdenes de compra
  Tenemos que definir nuestro type class y la instancia. Una vez que hacemos eso ya podemos
  aprovecharnos del sysntax definido por cats
 */


// type class
case class Order(totalCost: Double, quantity: Double)


// instance

implicit val monoidOrder: Monoid[Order] = new Monoid[Order] {
  // definimos las dos operaciones que tiene que tener para ser un momoide

  def combine(o1: Order, o2:Order) =
    Order(
      o1.totalCost + o2.totalCost,
      o1.quantity + o2.quantity
    )

  def empty = Order(0,0)
}


// Ahora ya podemos usar la operación de "suma"

val op = Order(1,1) |+| Order(1,2)

val lops = List(Order(1,1), Order(1,2))

lops.foldLeft(Monoid[Order].empty)( _ |+| _)
