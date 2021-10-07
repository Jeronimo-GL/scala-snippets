package logging

import org.log4s._

object MainLogging extends App {
  // Con esto usamos el root Logger
  val log = getLogger

  /*
   Si queremos usar un logger en particular hay que poner el valor de 
   la propiedad "name" del logger.
   getLogger("snippets")
   */

  log.trace("Starting app")
  log.debug("Debugging")
  println("Hola logging  zzzzz")
  log.info("Hello from log4s")
  log.trace("Closing app")
}
