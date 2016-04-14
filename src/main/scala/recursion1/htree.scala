/* This program uses the StdDraw library from the book:
   "Introduction to Programming in Java" by Sedgewick and Wayne
   StdDraw is described in Section 1.5 of the book, available here
   "http://www.cs.princeton.edu/introcs/15inout/".
   The library can be downloaded as "stdlib.jar" from
   "http://www.cs.princeton.edu/introcs/stdlib/".
   Javadoc documentation for StdDraw is at 
   "http://www.cs.princeton.edu/introcs/stdlib/javadoc/StdDraw.html".
*/

// plot an H, centered on (x, y) of the given side length
def drawH(x : Double, y : Double, size : Double) {
  
  // compute the coordinates of the 4 tips of the H
  val x0 = x - size/2
  val x1 = x + size/2
  val y0 = y - size/2
  val y1 = y + size/2
  
  // draw the 3 line segments of the H
  // left  vertical segment of the H
  StdDraw.line(x0, y0, x0, y1)    
  // right vertical segment of the H
  StdDraw.line(x1, y0, x1, y1)    
  // connect the two vertical segments of the H
  StdDraw.line(x0,  y, x1,  y)    
}

// plot an order n H-tree, centered on (x, y) of the given side length
def draw(n : Int, x : Double, y : Double, size : Double)
{
  if (n == 0) 
    return
  
  drawH(x, y, size)
  
  // compute x- and y-coordinates of the 4 half-size H-trees
  val x0 = x - size/2
  val x1 = x + size/2
  val y0 = y - size/2
  val y1 = y + size/2
  
  // recursively draw 4 half-size H-trees of order n-1
  // order can be rearranged
  draw(n-1, x0, y0, size/2)    // lower left  H-tree
  draw(n-1, x0, y1, size/2)    // upper left  H-tree
  draw(n-1, x1, y0, size/2)    // lower right H-tree
  draw(n-1, x1, y1, size/2)    // upper right H-tree
}

// read in a command line argument N and plot an order N H-tree
val N = args(0).toInt
StdDraw.show(0)
draw(N, .5, .5, .5)
StdDraw.show(100000)

// --------------------------------------------------------------------
