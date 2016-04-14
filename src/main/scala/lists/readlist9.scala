import scala.compat.Platform.currentTime
import scala.io.Source

val fname = args(0)

def readWords(): List[String] = {
  val F = Source.fromFile(fname)
  F.getLines().toList
}

println("Starting...")
val t0 = currentTime
val A = readWords()
val t1 = currentTime
printf("Reading all %d words took %d milliseconds\n", A.length, t1 - t0)

println
println("The first ten words on the list are:")
println(A take (10 min A.length) mkString "\n")

