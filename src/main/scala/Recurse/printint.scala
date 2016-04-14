
val DIGIT_TABLE = "0123456789abcdef"
val MAX_BASE    = DIGIT_TABLE.length
  
// Print n in any base, recursively
// Precondition: n >= 0, 2 <= base <= MAX_BASE
def printIntRec(n : Int, base : Int)
{
  if (n >= base)
    printIntRec(n / base, base)
  val digit = n % base
  print(DIGIT_TABLE(digit))
}

// Driver routine
def printInt(n : Int, base : Int) 
{
  if (base <= 1 || base > MAX_BASE)
    print("Cannot print in base " + base)
  else {
    if (n < 0) {
      print( "-" )
      printIntRec(-n, base);
    } else
      printIntRec(n, base);
  }
}
  
// Simple test program
for (i <- 0 to 17) {
  print(1000 + " in base " + i + ": ")
  printInt(1000, i);
  println();
}

