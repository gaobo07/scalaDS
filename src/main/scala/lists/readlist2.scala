
import scala.compat.Platform.currentTime
import scala.io.Source

val fname = args(0)

class Node(val head: String, val tail: Node) {
  def length: Int = if (tail == null) 1 else 1 + tail.length
  def display() {
    println(head)
    if (tail != null) tail.display()
  }
}

def readWords(): Node = {
  val F = Source.fromFile(fname)
  var list: Node = null
  for (line <- F.getLines()) {
    list = new Node(line, list)
  }
  list
}

println("Starting...")
val t0 = currentTime
val A = readWords()
val t1 = currentTime
printf("Reading all %d words took %d milliseconds\n", A.length, t1 - t0)

A.display()
