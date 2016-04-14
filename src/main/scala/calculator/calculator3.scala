package calculator

// Calculator3, with better error reporting

import scala.io.StdIn.readLine

object Calculator3 {

  // an expression is a sum of terms
  def parseExpression(tok: Tokenizer): Double = {
    var result = parseTerm(tok)
    var t = tok.token
    while (t.isSymbol("+") || t.isSymbol("-")) {
      val mult = if (t.isSymbol("+")) +1 else -1
      tok.next()
      result += mult * parseTerm(tok)
      t = tok.token
    }
    result
  }
  
  // a term is a product of factors
  def parseTerm(tok: Tokenizer): Double = {
    var result = parseFactor(tok)
    var t = tok.token
    while (t.isSymbol("*") || t.isSymbol("/")) {
      val divide = t.isSymbol("/")
      tok.next()
      val rhs = parseFactor(tok)
      result = if (divide) (result / rhs) else (result * rhs)
	t = tok.token
    }
    result
  }
  
  // a factor is x^y^z
  def parseFactor(tok: Tokenizer): Double = {
    var t = tok.token
    val sign = if (t.isSymbol("-")) -1 else +1
    if (t.isSymbol("-") || t.isSymbol("+"))
      tok.next()
    
    var result = parseItem(tok)
    t = tok.token
    while (t.isSymbol("^")) {
      tok.next()
      val rhs = parseFactor(tok)
      result = math.pow(result, rhs)
      t = tok.token
    }
    sign * result
  }

  // a single item: number, or expression in parentheses
  def parseItem(tok: Tokenizer): Double = {
    val startPos = tok.tokenPos
    val t = tok.token
    tok.next()
    
    if (t.isSymbol("(")) {
      val result = parseExpression(tok)
      if (!tok.token.isSymbol(")"))
	throw new SyntaxError(tok.tokenPos, "expected operator or ')'")
      tok.next()
      result
    } else if (t.isNumber)
      t.number
      else if (t.isIdentifier) {
	println("Variables are not yet implemented.")
	// Thread.dumpStack()
	0
      } else
	// anything else is an error
	throw new SyntaxError(startPos, "expected number, identifier, or '('")
  }
  
  def main(args: Array[String]) {
    println("Welcome to KAIST SuperCalculator!")
    while (true) {
      val oneLine = readLine("> ")
      // terminate on End Of File 
      if (oneLine == null)
	return
      if (oneLine != "") {
	val tok = new Tokenizer(oneLine)
	// terminate if user says "exit"
	if (tok.token.isIdentifier && tok.token.text == "exit")
	  return
	try {
	  val value = parseExpression(tok)
	  if (!tok.token.isStop)
	    throw new SyntaxError(tok.tokenPos, 
				  "expected operator or end of input")
	  println("==> " + value)
	} catch {
	  case e: SyntaxError => 
	    printf("Syntax error: %s\n%s\n%s\n", e.msg,
		   oneLine, " " * e.pos + "^")
	    // e.printStackTrace()
	}
      }
    }
  }
}
