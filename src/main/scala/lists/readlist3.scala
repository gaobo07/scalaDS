
import scala.compat.Platform.currentTime
import scala.io.Source

val fname = args(0)

class Node(val head: String, val tail: Node) {
  def length: Int = {
    var p = tail
    var count = 1
    while (p != null) {
      count += 1
      p = p.tail
    }
    count
  }
  def display() {
    var p = this
    while (p != null) {
      println(p.head)
      p = p.tail
    }
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

