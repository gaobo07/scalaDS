// compute fib(n-1) and fib(n)
// Precondition: n >= 1
def fib(n : Int) : (Long, Long) = 
{
  if (n == 1) {
    (0, 1)
  } else {
    val (a, b) = fib(n-1)
    val c = a + b
    (b, c)
  }
}
  
for (i <- 1 to 95)
  println("Fib(" + i + ") = " + fib(i)._2)

