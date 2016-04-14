package hashing


object Clustering {
  
  def independent(A: Array[Boolean], prob: Double) {
    for (i <- 0 until A.length) {
      val p = math.random
      A(i) = (p < prob)
    }
  }

  def linearProbing(A: Array[Boolean], prob: Double) {
    val s = (prob * A.length).toInt
    for (i <- 1 to s) {
      var h = (math.random * A.length).toInt
      while (A(h)) h = (h + 1) % A.length
      A(h) = true
    }
  }
  
  def evaluate(A: Array[Boolean]) : Double = {
    var result = 0
    for (i <- 0 until A.length) {
      var h = i
      var count = 1
      while (A(h)) {
	h = (h + 1) % A.length
	count += 1
      }
      result += count
    }
    result.toDouble / A.length
  }

  def display(A: Array[Boolean]) {
    for (i <- 0 until A.length)
      print(if (A(i)) "#" else " ")
    println()
  }

  def main(args: Array[String]) {
    val size = args(0).toInt
    val prob = args(1).toDouble
    val r = args(2).toInt

    var resultA = 0.0
    var resultB = 0.0
    for (i <- 1 to r) {
      val A = new Array[Boolean](size)
      independent(A, prob)
      if (r == 1) display(A)
      resultA += evaluate(A)
      val B = new Array[Boolean](size)
      linearProbing(B, prob)
      if (r == 1) display(B)
      resultB += evaluate(B)
    }
    println("Average A: " + resultA / r)
    println("Average B: " + resultB / r)
  }
}

