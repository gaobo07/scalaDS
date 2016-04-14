
val fib = new Array[Long](100)

// Compute all Fibonacci numbers from 0 to n 
def fibonacci(n : Int)
{
  fib(0) = 0
  fib(1) = 1
  for (i <- 2 to n)
    fib(i) = fib(i-1) + fib(i-2)
}
  
fibonacci(95)
for (i <- 1 to 95)
  println("Fib(" + i + ") = " + fib(i))

