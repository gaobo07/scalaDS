// Solve Towers of Hanoi
// Precondition: n smallest discs are the top discs on pole source.
// Postcondition: n smallest discs are the top discs on pole destination.
def solveHanoi(n : Int, source : Char, destination : Char, spare : Char)
{
  if (n == 1)
    println("Move disc 1 from " + source + " to " + destination)
  else {
    solveHanoi(n-1, source, spare, destination)
    println("Move disc " + n + " from " + source + " to " + destination)
    solveHanoi(n-1, spare, destination, source)
  }
}

if (args.length != 1) {
  println("Missing argument")
  sys.exit(1)
}

val n = args(0).toInt
solveHanoi(n, 'A', 'B', 'C')

