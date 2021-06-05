package spark

import org.apache.spark.sql.SparkSession

object ReadyMade {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder()
      .master("local[2]")
      .appName("ReadyMade")
      .getOrCreate()
  }
}
