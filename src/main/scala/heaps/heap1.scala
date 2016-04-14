package heaps


import scala.reflect.ClassTag

class Heap[T <% Ordered[T] : ClassTag ] {
  private val DEFAULT_CAPACITY = 100

  // Array storing elements in the heap
  private var A: Array[T] = new Array[T](DEFAULT_CAPACITY)
  // Number of elements in heap
  private var currentSize = 0

  // Returns the number of items in this heap.
  def size: Int = currentSize

  // Returns the smallest item in the heap.
  def findMin: T = {
    if (currentSize == 0)
      throw new NoSuchElementException
    else
      A(1)
  }
  
  // Adds an item to this heap.
  def insert(x: T) {
    if (currentSize + 1 == A.length)
      doubleArray()

    currentSize += 1
    var hole = currentSize
    A(0) = x
    
    // Percolate up
    while (x < A(hole/2)) {
      A(hole) = A(hole/2)
      hole /= 2
    }
    A(hole) = x
  }
    
  // Removes the smallest item in the heap.
  def deleteMin(): T = {
    val minItem = findMin
    A(1) = A(currentSize)
    currentSize -= 1
    percolateDown(1)
    minItem
  }

  // Is one child of node hole smaller than element inHole?
  // Returns index of child, or 0 if no child is smaller
  private def smallerChild(hole: Int, inHole: T): Int = {
    if (2 * hole <= currentSize) {
      var child = 2 * hole
      if (child != currentSize && A(child + 1) < A(child))
	child += 1
      if (A(child) < inHole)
	child
      else
	0
    } else
      0
  }

  // Internal method to percolate down in the heap.
  private def percolateDown(i: Int) {
    val inHole = A(i)
    var hole = i
    var child = smallerChild(hole, inHole)
    while (child != 0) {
      A(hole) = A(child)
      hole = child
      child = smallerChild(hole, inHole)
    }
    A(hole) = inHole
  }
  
  // Internal method to extend A.
  private def doubleArray()
  {
    val B = new Array[T](2 * A.length)
    for (i <- 0 until A.length)
      B(i) = A(i)
    A = B
  }
}

// --------------------------------------------------------------------

object HeapTest {

  def main(args: Array[String]) {
    println("Testing Heap[String]")
    val SH = new Heap[String]
    SH.insert("Hello")
    println(SH.findMin)
    SH.insert("World")
    println(SH.findMin)
    SH.insert("CS206")
    println(SH.findMin)
    SH.insert("Scala")
    println(SH.deleteMin())
    println(SH.deleteMin())
    SH.insert("KAIST")
    println(SH.deleteMin())
    println(SH.deleteMin())
    println(SH.deleteMin())
  }
}
