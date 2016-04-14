package Recurse

/**
 * Created by DGA on 2016/4/14.
 */
object Factorial {


  // Evaluate n!
  def factorial(n :BigInt) : BigInt = {
    if (n <= 1)     // base case
      1
    else
      n * factorial(n - 1)
  }

  def main(args: Array[String]) {
    for (i <- 1 to 25)
    println(i + "! = " + factorial(i))
  }
}
