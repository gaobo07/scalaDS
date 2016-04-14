// --------------------------------------------------------------------
//  Create an N-by-N plasma fractal using the midpoint displacement method.
//  Also called a fractal cloud. Try different colors to get a cloud.
//  Also used to generate random terrain where "color" becomes the
//  altitude.
// --------------------------------------------------------------------

import java.awt.Color;

// centered at (x, y), of given size, using given standard
// deviation for computing the displacement, and with upper left,
// upper right, lower lower, and lower right hues c1, c2, c3, c4
def plasma(x: Double, y: Double, size: Double, stddev: Double,
           c1: Double, c2: Double, c3: Double, c4: Double)
{
  // base case
  if (size <= 0.001) 
    return

  // calculate new color of midpoint using random displacement
  val displacement = StdRandom.gaussian(0, stddev)
  val cM = (c1 + c2 + c3 + c4) / 4.0 + displacement

  // draw a colored square
  val color = Color.getHSBColor(cM.toFloat, .8f, .8f)
  StdDraw.setPenColor(color)
  StdDraw.filledSquare(x, y, size)

  val cT = (c1 + c2) / 2.0    // top
  val cB = (c3 + c4) / 2.0    // bottom
  val cL = (c1 + c3) / 2.0    // left
  val cR = (c2 + c4) / 2.0    // right

  plasma(x - size/2, y - size/2, size/2, stddev/2, cL, cM, c3, cB)
  plasma(x + size/2, y - size/2, size/2, stddev/2, cM, cR, cB, c4)
  plasma(x - size/2, y + size/2, size/2, stddev/2, c1, cT, cL, cM)
  plasma(x + size/2, y + size/2, size/2, stddev/2, cT, c2, cM, cR)
}

// choose intial corner colors at random betwen 0 and 1
val c1 = Math.random
val c2 = Math.random
val c3 = Math.random
val c4 = Math.random

// controls variation in color
val stddev = 1.0

plasma(.5, .5, .5, stddev, c1, c2, c3, c4)
StdDraw.show(100000)

// --------------------------------------------------------------------
