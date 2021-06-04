import $dep.`org.typelevel::cats-core:2.2.0`

import cats.Functor
import cats.instances.list._
import cats.instances.option._

val list1 = List(1,2,3)

val list2 = Functor[List].map(list1)(_ * 2)
val list3 = list1.map(_ * 2)

// Aun no se para qué sirve esto.
val func = (x: Int) => x + 1
val liftedFunc = Functor[Option].lift(func)

liftedFunc(Some(1))
liftedFunc(None)


def plus1(x: Int) = x + 1

val lifted2 = Functor[Option].lift(func)
lifted2(Some(1))

//val ll = Functor[List].

import cats.instances.function._
import cats.syntax.functor._


/*
  Aquí el functor lo usamos como una abstracción. Así podemos aplicar nuestra función doMath a cualquier functor
  que acepte enteros F[Int]. En los ejemplos hemos usado List y Option.

  Esto nos permite usar el método map, incluso cuando el map de scala no funciona (porque no está imnplementado para
  el tipo que nos interesa. Esto nos dará juego después para los for.

  Este truco funciona porque hemos importado la syntax de functor.
*/
def doMath[F[_]](start: F[Int])(implicit functor: Functor[F]): F[Int] =
  start.map(n => n+1 * 2)

doMath(Option(20))
doMath(List(1,2,3))
