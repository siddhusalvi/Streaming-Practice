//import org.apache.spark.SparkConf
//import org.apache.spark.sql.SparkSession
//
//object RunPythonCode extends App {
//
//  val sparkConf = new SparkConf().setAppName("Spark_Consumer_test").set("redis.host", "127.0.0.1").set("redis.port", "6379").set("redis.db", "11").set("redis.auth", "RedisFlutura@2.0")
//    .set("spark.streaming.kafka.maxRetries", "10").set("spark.streaming.kafka.maxRatePerPartition", "500").set("spark.driver.extraJavaOptions", "-XX:+UseG1GC").set("spark.executor.extraJavaOptions", "-XX:+UseG1GC").set("spark.streaming.unpersist", "true")
//
//
//  val sc = spark.sparkContext
////
//  val numbers = "2 2"
//  val command = "python D:/IdeaProjects/Practice/src/main/resources/function.py"
//
//  val operation = sc.parallelize(Array(numbers))
//
//  println(operation.pipe(command).collect())
//}
