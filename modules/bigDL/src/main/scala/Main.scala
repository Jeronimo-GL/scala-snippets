import com.intel.analytics.bigdl.utils.Engine
// Hay que usar NumericFloat o Numeric Double para elegir el tipo de los tensores.
import com.intel.analytics.bigdl.tensor.Tensor
import com.intel.analytics.bigdl.numeric.NumericFloat
import com.intel.analytics.bigdl.utils.T



object Main {
  def main(args: Array[String]) = {
    println("Hola mundo")
    holaBigDL()
  }

  def holaBigDL()={

    println("Hola BigDL")
    Engine.init
    val tensor = Tensor(2, 3)
    println(s"Tensor vacio de 2x3:\n ${tensor}")

    val a = Tensor(T(
      T(1f, 2f, 3f),
      T(4f, 5f, 6f)))
    println(s"Tensor a:\n${a}")

    println(s"+1 a todo el tensor:\n ${a+1}")

    println(s"Tensor traspuesto:\n ${a.t}")
  }

  def holaDesdeSpark() = {
    import org.apache.spark.sql.SparkSession
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
