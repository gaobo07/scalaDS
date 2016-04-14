package hashing


class LinearProbing[Value] extends HashMap[Int, Value] {

  private case class Entry(val key: Int, var value: Value) 

  private val SIZE = 100
  private val table = new Array[Entry](SIZE)
  
  private def findSlot(key: Int): Int = {
    var h = hash(key)
    while (true) {
      val n = table(h)
      if (n == null || n.key == key) 
	return h
      h += 1
      if (h == SIZE) 
	h = 0
    }
    0 // make compiler happy
  }

  def update(key: Int, value: Value) {
    val h = findSlot(key)
    if (table(h) != null)
      table(h).value = value
    else
      table(h) = Entry(key, value)
  } 

  def += (mapping: (Int, Value)) {
    val (key, value) = mapping
    update(key, value)
  }

  def contains(key: Int): Boolean = (table(findSlot(key)) != null)

  def apply(key: Int): Value = {
    val h = findSlot(key)
    if (table(h) != null)
      table(h).value
    else
      throw new NoSuchElementException
  } 

  def printTable() {
    for (i <- 0 until 100) {
      printf("%2d: : ", i);
      val n = table(i)
      if (n != null)
	print(n.key)
      println()
    }
  }

  private def hash(key: Int): Int = {
    // ((key) % 100)
    (key % 100)
  }
}
