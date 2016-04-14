import scala.compat.Platform.currentTime
import scala.io.Source

val fname = args(0)

trait List[+T] {
  def length: Int
  def head: T
  def tail: List[T]
  def display(): Unit
}

object Empty extends List[Nothing] {
  def length: Int = 0
  def head: Nothing = throw new NoSuchElementException
  def tail: List[Nothing] = throw new NoSuchElementException
  def display() = ()
}

class Node[T](val head: T, val tail: List[T]) extends List[T] {
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
    var p = this
    while (p != Empty) {
      println(p.head)
      p = p.tail
    }
  }
}

def readWords(): List[String] = {
  val F = Source.fromFile(fname)
  var list: List[String] = Empty
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

