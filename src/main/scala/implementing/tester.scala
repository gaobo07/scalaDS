package implementing


object StackTester {
  def reverseString(stack: Stack[Char], s: String) {
    for (ch <- s)
      stack.push(ch)
    while (!stack.isEmpty)
    print(stack.pop())
    println()
  }

  def matching(open: Char, close: Char): Boolean = {
    (open == '(' && close == ')') || 
    (open == '{' && close == '}') ||
    (open == '[' && close == ']')
  }
  
  def balancedSymbols(stack: Stack[Char], s: String): Boolean = {
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
    for (s <- args) {
      reverseString(new FixedStack[Char], s)
      reverseString(new GrowStack[Char], s)
      reverseString(new LinkedStack[Char], s)
      reverseString(new scala.collection.mutable.Stack[Char] 
		    with Stack[Char], s)
    }
    for (s <- Array("{{[(){}]}([{}{}{}])}",
		    "{{[(){}]})[{}{}{}])}")) {
      println(s + " is balanced: " + balancedSymbols(new FixedStack[Char], s))
      println(s + " is balanced: " + balancedSymbols(new GrowStack[Char], s))
      println(s + " is balanced: " + balancedSymbols(new LinkedStack[Char], s))
      println(s + " is balanced: " + 
	      balancedSymbols(new scala.collection.mutable.Stack[Char] 
			      with Stack[Char], s))
    }
  }
}
