import scala.compat.Platform.currentTime

// Naive (cubic) maximum contiguous subsequence sum algorithm.
// seqStart and seqEnd represent the actual best sequence.
def maxSubSum1(a: Array[Int]): (Int, Int, Int) = {
  var maxSum = 0
  var start = 0
  var end = 0
  for (i <- 0 until a.length) {
    for (j <- i until a.length) {
      var sum = 0
      for (k <- i to j)
	sum += a(k)
      if (sum > maxSum) {
	maxSum = sum
	start = i
	end   = j
      }
    }
  }
  (maxSum, start, end)
}

// Quadratic maximum contiguous subsequence sum algorithm.
// seqStart and seqEnd represent the actual best sequence.
def maxSubSum2(a: Array[Int]): (Int, Int, Int) = {
  var maxSum = 0
  var start = 0
  var end = 0
  for (i <- 0 until a.length) {
    var sum = 0
    for (j <- i until a.length) {
      sum += a(j)
      if (sum > maxSum) {
	maxSum = sum
	start = i
	end   = j
      }
    }
  }
  (maxSum, start, end)
}

// Recursive maximum contiguous subsequence sum algorithm.
// Finds maximum sum in subarray spanning a[left..right].
def maxSumRec(a: Array[Int], left: Int, right: Int): Int = {
  if (left == right) { // base case
    if (a(left) > 0) 
      a(left)
    else 
      0
  } else {
    val center = (left + right) / 2
    val maxLeftSum  = maxSumRec(a, left, center)
    val maxRightSum = maxSumRec(a, center + 1, right)
    
    var maxLeftBorderSum = 0
    var maxRightBorderSum = 0
    var leftBorderSum = 0
    var rightBorderSum = 0
    
    for (i <- center to left by -1) {
      leftBorderSum += a(i)
      if (leftBorderSum > maxLeftBorderSum)
	maxLeftBorderSum = leftBorderSum
    }
    for (i <- center + 1 to right) {
      rightBorderSum += a(i)
      if (rightBorderSum > maxRightBorderSum)
	maxRightBorderSum = rightBorderSum
    }
    
    maxLeftSum max maxRightSum max (maxLeftBorderSum + maxRightBorderSum)
  }
}

// Driver 
def maxSubSum3(a: Array[Int]): Int = 
  if (a.length > 0) maxSumRec(a, 0, a.length - 1) else 0


def getTimingInfo(n: Int, alg: Int) {
  val test = new Array[Int](n)

  val startTime = currentTime
  var totalTime = 0L

  var rounds = 0
  while (totalTime < 1000) {
    for (j <- 0 until test.length)
      test(j) = (math.random * 100).toInt - 50
      
    alg match {
      case 1 => maxSubSum1(test)
      case 2 => maxSubSum2(test)
      case 3 => maxSubSum3(test)
    }
      
    totalTime = currentTime - startTime
    rounds += 1
  }
  
  printf("Algorithm #%d N = %6d time = %9d microsec\n",
	 alg, n, totalTime * 1000 / rounds)
} 
  
val A = Array(4, -3, 5, -2, -1, 2, 6, -2)

val (res1, start1, end1) = maxSubSum1(A)
val (res2, start2, end2) = maxSubSum2(A)
val res3 = maxSubSum3(A)
printf("Alg 1: Max sum is %d; it goes from %d to %d\n", res1, start1, end1)
printf("Alg 2: Max sum is %d; it goes from %d to %d\n", res2, start2, end2)
printf("Alg 3: Max sum is %d\n", res3)
    
// Get some timing info
var n = 10
while (n <= 1000000) {
  for (alg <- 1 to 3) {
    if (alg != 1 || n < 15000)
      getTimingInfo(n, alg)
  }
  n *= 10
}

