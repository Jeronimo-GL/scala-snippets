import $dep.`org.typelevel::cats-core:2.2.0`

import cats.Show

import cats.instances.int._
import cats.instances.string._


/*
  Aquí hacemos un uso explicito del interface.
 */

val showInt = Show.apply[Int]
showInt.show(10)


/*
 si queremos usar systax
*/
import cats.syntax.show._

10.show
