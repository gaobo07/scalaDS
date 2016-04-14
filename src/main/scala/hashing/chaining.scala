package hashing


class Chaining[Value] extends HashMap[Int, Value] {

  private case class Node(val key: Int, var value: Value, val next: Node)
  
  private val SIZE = 100
  private val table = new Array[Node](SIZE)

  def update(key: Int, value: Value) {
    val hashCode = hash(key)
    var n = table(hashCode)
    while (n != null) {
      if (n.key == key) {
	n.value = value
	return
      }
      n = n.next
    }
    // add new node
    table(hashCode) = Node(key, value, table(hashCode))
  }

  def += (mapping: (Int, Value)) {
    val (key, value) = mapping
    update(key, value)
  }

  private def findNode(key: Int): Node = {
    val hashCode = hash(key)
    var n = table(hashCode)
    while (n != null) {
      if (n.key == key)
	return n
      n = n.next
    }
    null
  }

  def contains(key: Int): Boolean = (findNode(key) != null)

  def apply(key: Int): Value = {
    val n = findNode(key)
    if (n != null)
      n.value
    else
      throw new NoSuchElementException
  } 

  def printTable() {
    for (i <- 0 until 100) {
      printf("%2d: : ", i);
      var n = table(i)
      while (n != null) {
	print(n.key + ", ")
	n = n.next
      }
      println()
    }
  }

  private def hash(key: Int): Int = {
    // ((key) % 100)
    (key % 100)
  }
}
