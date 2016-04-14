package heaps


import scala.reflect.ClassTag

// Can provide an (unordered) array to store the elements,
// or null to create an empty heap
class Heap[T : ClassTag](els: Array[T], val lessThan: (T,T) => Boolean) {
  private val DEFAULT_CAPACITY = 100

  // Array storing elements in the heap
  private var A: Array[T] = 
    if (els != null) els else new Array[T](DEFAULT_CAPACITY)
  // Number of elements in heap
  private var currentSize = if (els != null) els.length-1 else 0
  if (els != null)
    buildHeap()

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
    while (lessThan(x, A(hole/2))) {
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

  // Establish heap order property from an arbitrary arrangement of
  // items. Runs in linear time.
  private def buildHeap() {
    for (i <- currentSize / 2 to 1 by -1)
      percolateDown(i)
  }

  // Is one child of node hole smaller than element inHole?
  // Returns index of child, or 0 if no child is smaller
  private def smallerChild(hole: Int, inHole: T): Int = {
    if (2 * hole <= currentSize) {
      var child = 2 * hole
      if (child != currentSize && lessThan(A(child + 1), A(child)))
	child += 1
      if (lessThan(A(child), inHole))
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

object HeapSort {

  def heapSort(A: Array[Int]) {
    val heap = new Heap[Int](A, (x, y) => x < y)
    for (i <- A.length - 1 until 1 by -1)
      A(i) = heap.deleteMin()
  }

  def main(args: Array[String]) {
    println("Testing Heap[String]")
    val SH = new Heap[String](null, (x, y) => x < y)
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
    
    println("Testing HeapSort")
    val A = Array(0, 13, 5, 7, 99, 24, 12, 9, 1, 17, 57, 34, 74, 14)
    println(A mkString " ")
    heapSort(A)
    println(A mkString " ")
    
    val B = new Array[Int](1000000)
    for (i <- 1 until B.length)
      B(i) = (math.random * 1000000).toInt
    println(B mkString " ")
    heapSort(B)
    println(B mkString " ")
  }
}

