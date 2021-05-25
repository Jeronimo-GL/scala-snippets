package akka_streams

import akka.stream._
import akka.stream.scaladsl._
import akka.actor.ActorSystem
import akka.NotUsed
import scala.concurrent.Future
import scala.concurrent.duration._
import akka.Done
import java.nio.file.Paths
import akka.util.ByteString

object Main extends App {
  zipAndThrotle()

  /**
    * Contar hasta 100
    */
  def holaMundo() {
    implicit val system: ActorSystem = ActorSystem("QuickStart")

    println("Hello from akka streams")

    /*
     Los sources tienen dos tipos parametrizados:
     - El tipo que emiten
     - Valor materializado: Permite generar un segundo valor auxiliar. Si no se necesita se puede
     usar NotUsed.
     */
    val source: Source[Int, NotUsed] = Source(1 to 100)

    /*
     Arriba solo hemos definido un stream con un source. Si queremos que jecute algo tenemos que ponerlo
     en marcha. Contamos con varios métodos para hacer run. Este es el más sencillo.

     Nos devuelve un futuro con el que podemos saber cuando ha terminado.
     */
    val done: Future[Done] = source.runForeach(i => println(i))

    /*
     Cuando el stream genera un evento complete al que podemos suscribirnos. 
     El método onComplete necesita un executionContext implícito así que llamamos a system.dispatcher
     para obtener el ExecutionContext por defecto.
     */
    implicit val ec = system.dispatcher
    done.onComplete(_ => system.terminate())
  }

  def factoriales(){
    implicit val system: ActorSystem = ActorSystem("QuickStart")
    val source: Source[Int, NotUsed] = Source(1 to 100)

    /*
     Scan es como foldLeft, tiene un valor inicial y va acumulando según la función que se le indique. La diferencia
     es que scan si que emite el valor acumulado a cada paso.
     */
    val factorials = source.scan(BigInt(1))((acc, next) => acc * next)

    /*
     Map sencillo para pasar los BigInts a ByteString.
     Todavía no se ha ejecutado nada. Solo hemos definido las operaciones.
     */
    val mm = factorials.map(num => ByteString(s"$num\n"))


    /*
     Al llamar a runWith ejecutamos el stream añadiendoles un Sink que sale del paquete FileIO usando el 
     método toPath
     */
    val result: Future[IOResult] = mm.runWith(FileIO.toPath(Paths.get("factorials.txt")))

    implicit val ec = system.dispatcher
    result.onComplete{res =>
      println(s"${res.get.getCount} bytes written")
      system.terminate()
    }
  }


  /* 
   En este caso hacemos un zip, que nos permite unir dos streams haciendo una cremallera con
   los datos que llegan por los dos lados. Entiendo que sin ninguna lógica, se unen según llegan.
   
   También usamos throttle para controlar el flujo de paso por el stream.
   
   Sobre los implícitos.
   Cuando vamos a ejecutar un stream tenemos que crear un ActorSystem para que gestione el stream.
   Solo es necesario cuando se ejecuta, en nuestro caso runForEach. Si creamos el stream en un objeto/clase
   no necesitamos el ActorSystem.

   Si queremos detectar el final de la ejecución podemos poner un onComplete en el futuro que nos devuelve 
   runForEach, que necesita un implicit al ExecutionContext del actor system.
   */
  def zipAndThrotle(){
    implicit val system: ActorSystem = ActorSystem("QuickStart")
    implicit val ec = system.dispatcher

    val factorials = Source(1 to 100)
      .scan(BigInt(1))((acc, next) => acc * next)
      .zipWith(Source(0 to 100))((num, idx) => s"$idx! = $num")
      .throttle(10, 1.second)
      .runForeach(println)

   factorials.onComplete(_ => system.terminate())
  }
}


