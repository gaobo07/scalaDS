/* This program uses the StdDraw library from the book:
   "Introduction to Programming in Java" by Sedgewick and Wayne
   StdDraw is described in Section 1.5 of the book, available here
   "http://www.cs.princeton.edu/introcs/15inout/".
   The library can be downloaded as "stdlib.jar" from
   "http://www.cs.princeton.edu/introcs/stdlib/".
   Javadoc documentation for StdDraw is at 
   "http://www.cs.princeton.edu/introcs/stdlib/javadoc/StdDraw.html".
*/

import java.awt.Color

val theSize = 256
  
def drawSpace(xCenter : Int, yCenter : Int, boundingDim : Int) 
{
  val side = boundingDim / 2
  
  if (side >= 2) {
    val left =   xCenter - side / 2
    val top =    yCenter - side / 2
    val right =  xCenter + side / 2
    val bottom = yCenter + side / 2
    
    drawSpace(left, top, side)
    drawSpace(left, bottom, side)
    drawSpace(right, top, side) 
    drawSpace(right, bottom, side)
    
    StdDraw.filledSquare(xCenter, yCenter, side / 2)
    
    // Change argument to see the drawing in slow motion 
    // Comment to see result fast
    // StdDraw.show(100);
  }
}

StdDraw.setCanvasSize(theSize + theSize / 10 + 1, 
		      theSize + theSize / 10 + 1)
StdDraw.setXscale(0.0, theSize + 1.0)
StdDraw.setYscale(0.0, theSize + 1.0)
StdDraw.clear(Color.gray)
StdDraw.setPenColor(Color.white)
StdDraw.show(0)
drawSpace(theSize / 2, theSize / 2, theSize)
StdDraw.show(100000)
