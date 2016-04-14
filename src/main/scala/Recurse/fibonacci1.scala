
def fib(n : Int) : Long = {
  if (n == 0)     // base cases
    0
  else if (n == 1)
    1
  else
    fib(n - 1) + fib(n - 2) 
}
  
for (i <- 1 to 45)
  println("Fib(" + i + ") = " + fib(i))

