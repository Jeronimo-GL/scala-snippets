import org.log4s._

object Main extends App {

  def logWithContext(log: Logger, msg: String,  ctx:Map[String,String]) {
    MDC.withCtx(ctx.toSeq: _*){
      log.info(msg)
    }
  }

  val log = getLogger
  val kmonCtx = Map("a" -> "1", "b" -> "2")
  log.info("Hola desde el log")
  println("Hi from sl4j fluent API")
  MDC.withCtx("project" -> "Prueba de MDC"){
    log.info("Saliendo ...")
  }
  log.info("Sin context extra")

  logWithContext(log, "Hola mundo", kmonCtx)
}
