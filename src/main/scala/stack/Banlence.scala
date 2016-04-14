package stack

/**
 * Created by DGA on 2016/4/14.
 */
import scala.io.StdIn.readLine

object Balanced {

  def matching(open: Char, close: Char): Boolean = {
    (open == '(' && close == ')') ||
      (open == '{' && close == '}') ||
      (open == '[' && close == ']')
  }

  def balancedSymbols(s: String): Boolean = {
    val stack: Stack[Char] =
      new scala.collection.mutable.Stack[Char] with Stack[Char]

    for (ch <- s) {
      if (ch == '(' || ch == '{' || ch == '[') {
        stack.push(ch)
      } else if (ch == ')' || ch == '}' || ch == ']') {
        if (stack.isEmpty || !matching(stack.top, ch))
          return false
        stack.pop()
      }
      // all other characters ignored
    }
    stack.isEmpty
  }

  def main(args: Array[String]) {
    println("Enter a string to check (or empty line to terminate)");
    while (true) {
      val s = readLine("> ")
      if (s == null || s.trim.isEmpty)
        return
      println(balancedSymbols(s))
    }
  }
}
