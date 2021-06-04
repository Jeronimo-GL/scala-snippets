import $dep.`org.typelevel::cats-core:2.2.0`

// Nos lo traemos todo
import cats._
import cats.implicits._


import java.util.Date

// Solo tenemos que implementar los traits que queremos. En este caso show.

// implicit val dateShow: Show[Date] = new Show[Date] {
//   def show(date: Date):String =
//     s"${date.getTime} ms since epoch"
// }

val now = new Date()


/* Cats nos da método para hacerlo más rápido. Ojo que se pueden tener dos
   implicits con la misma signature
 */

implicit val dateShow2: Show[Date] =
  Show.show(date => s"${date.getTime} ms since epoch")


now.show
