import org.apache.spark.sql.SparkSession
import com.intel.analytics.bigdl.utils.Engine

object Main {

  def main(args: Array[String]) = {
    println("Hola mundo")
    holaBigDL()
  }

  def holaBigDL()={
    println("Hola BigDL")
    val nodeNumber = 1
    val coreNumber = 4
    val mult = 64
    val batchSize = nodeNumber * coreNumber * mult
    Engine.init(nodeNumber, coreNumber, false)
    println("Done")
  }

  def holaDesdeSpark() = {
    val spark = SparkSession
      .builder()
      .appName("Spark Pi")
      .master("local[*]")
      .getOrCreate()

    println("Hola BigDL")
    
    val nodeNumber = 1
    val coreNumber = 4
    val mult = 64
    val batchSize = nodeNumber * coreNumber * mult
    Engine.init(nodeNumber, coreNumber, true)

    spark.stop()
  }
}
