import scopt.OParser
import scopt.Read

// We must define our configuration options

// Aparently, scopt does not know how to parse Float by default, but
// it liles Double

object Main {
  case class Configs(name:String, weights:Seq[Double])
  val builder = OParser.builder[Configs]

  val parser = {
    import builder._
    OParser.sequence(
      programName("scSample"),
      head("scSample", "0.0.1"),
      opt[String]('n', "name")
        .required()
        .valueName("Name")
        .action((x,c) => c.copy(name = x))
        .text("Name to print in the screen"),
      opt[Seq[Double]]('w', "wieghts")
        .valueName("Weights")
        .text("Weights vector")
        .action((x,c) => c.copy(weights = x))
    )
  }

  def main(args: Array[String]): Unit = {
    println(sys.env.get("DEV"))
    OParser.parse(parser, args, Configs("default", Seq.empty)) match {
      case Some(config) =>
        println(s"Hello, ${config.name}")
        println(s"Weights: ${config.weights}")
      // do something
      case _ =>
        // arguments are bad, error message will have been displayed
    }
  }
}
