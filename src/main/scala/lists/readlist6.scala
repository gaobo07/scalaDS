import scala.compat.Platform.currentTime
import scala.io.Source

val fname = args(0)

trait List {
  def length: Int
  def head: String
  def tail: List
  def display(): Unit
}

object Empty extends List {
  def length: Int = 0
  def head: String = throw new NoSuchElementException
  def tail: List = throw new NoSuchElementException
  def display() = ()
}

class Node(val head: String, val tail: List) extends List {
  def length: Int = {
    var p = tail
    var count = 1
    while (p != Empty) {
      count += 1
      p = p.tail
    }
    count
  }
  def display() { 
    var p: List = this
    while (p != Empty) {
      println(p.head)
      p = p.tail
    }
  }
}

def readWords(): List = {
  val F = Source.fromFile(fname)
  var list: List = Empty
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
