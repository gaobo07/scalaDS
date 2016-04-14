package avl


object TreeTest {

  // Test program 1
  def test(t: TreeMap[Int, Int], nums: Int) {

    println("Height of tree t1 is " + t.height)
    println("Size of tree t1 is " + t.size)

    print("\nRemoving numbers from t1:")
    for (i <- 1 until nums by 2)
      t -= i
    
    println("\nHeight of tree t1 is " + t.height)
    println("Size of tree t1 is " + t.size)

    printf("First key: %d, last key: %d\n", t.firstKey, t.lastKey)
    
    if (t.firstKey != 2 || t.lastKey != nums - 2)
      println("firstKey/lastKey error!")
    
    println("Testing that all numbers are in the map:")
    for (i <- 2 until nums by 2) {
      if (!(t contains i) || t(i) != i)
	printf("Error: %d not in map or not mapping to itself\n", i)
    }
    
    println("Testing that numbers were deleted:")
    for (i <- 1 until nums by 2) {
      if (t contains i)
	printf("Error: %d still in the map\n", i)
    }

    println("Height of tree t1 is " + t.height)
    println("Size of tree t1 is " + t.size)
    println()
  }

  // Test program 2
  def sieve(t: TreeMap[Int, Int], num: Int) {
    t -= 1

    val h = t.height

    while (!t.isEmpty) {
      val k = t.firstKey
      var m = k
      while (m <= num) {
	if (t contains m)
	  t -= m
	m += k
      }
      print(k + " ")
    }
    println()

    println("Height of tree t2 was " + h)
  }

  def main(args: Array[String]) {
    if (args.length != 2) {
      println("Usage: scala TreeTest <num> <gap>")
      sys.exit(1)
    }

    val n = args(0).toInt
    val gap = args(1).toInt

    var t1: TreeMap[Int, Int] = new AVLTree
    var t2: TreeMap[Int, Int] = new AVLTree

    println("Inserting numbers into two trees.")
    var i = gap % n
    while (i != 0) {
      t1 += (i -> i)
      t2 += (i -> i)
      i = (i + gap) % n
    }

    test(t1, n)
    sieve(t2, n)
  }
}
