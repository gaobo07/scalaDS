
import scala.compat.Platform.currentTime
import scala.io.Source

val fname = args(0)

class Node(val head: String, val tail: Node)

def readWords(): Node = {
  val F = Source.fromFile(fname)
  var list: Node = null
  for (line <- F.getLines()) {
    list = new Node(line, list)
  }
  list
}

def displayList(L: Node) {
  if (L != null) {
    println(L.head)
    displayList(L.tail)
  }
}

println("Starting...")
val t0 = currentTime
val A = readWords()
val t1 = currentTime
printf("Reading all words took %d milliseconds\n", t1 - t0)

displayList(A)

