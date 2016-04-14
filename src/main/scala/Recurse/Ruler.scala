package Recurse

/**
 * Created by DGA on 2016/4/14.
 */
object Ruler {
  /* This program uses the StdDraw library from the book:
     "Introduction to Programming in Java" by Sedgewick and Wayne
     StdDraw is described in Section 1.5 of the book, available here
     "http://www.cs.princeton.edu/introcs/15inout/".
     The library can be downloaded as "stdlib.jar" from
     "http://www.cs.princeton.edu/introcs/stdlib/".
     Javadoc documentation for StdDraw is at
     "http://www.cs.princeton.edu/introcs/stdlib/javadoc/StdDraw.html".
  */

  val theSize = 511

  def drawRuler(left : Int, right : Int, level : Int)
  {
    if (level < 1)
      return

    val mid = (left + right) / 2

    drawRuler(mid + 1, right, level - 1)
    drawRuler(left, mid - 1, level- 1)

    StdDraw.line(mid, 0.0, mid, level / 8.0)

    // speed of display
    StdDraw.show(100)
  }



  def main(args: Array[String]) {
    StdDraw.setCanvasSize(theSize + theSize / 10 + 1, 128)
    StdDraw.setXscale(0.0, theSize + 1.0)
    StdDraw.show(0)
    drawRuler(0, theSize - 1, 8)
    StdDraw.show(100000)
  }
}
