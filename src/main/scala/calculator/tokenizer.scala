package calculator

// --------------------------------------------------------------------
// Tokenizer takes a string, and returns it as a sequence of tokens
// with improved error reporting
// --------------------------------------------------------------------

import scala.io.StdIn.readLine

class SyntaxError(val pos: Int, val msg: String) extends Exception

// --------------------------------------------------------------------

class Tokenizer(val str: String) {
  private var pos = 0
  private var current = computeToken()
  private var tokenStart = 0

  // returns the position of the current token in the string
  def tokenPos = tokenStart

  // Returns the current token in the string 
  def token = current

  // Advance position to the next token.
  def next() { current = computeToken() }

  // --------------------------------------------------------------------

  // compute next token in the string,
  // advances (internal) position beyond it.
  private def computeToken(): Token = {
    while (pos < str.length && " \n\t\r".indexOf(str(pos)) >= 0) {
      pos += 1
    }

    tokenStart = pos

    // once we reach end of string, always return stop token
    if (pos == str.length)
      return Token.Stop()

    val begin = pos
    var ch = str(pos)

    // Case 1: a (variable) name
    if ('a' <= ch && ch <= 'z' || 'A' <= ch && ch <= 'Z' || ch == '_') {
      while (pos < str.length) {
	ch = str(pos)
	if (!('a' <= ch && ch <= 'z' || 'A' <= ch && ch <= 'Z' || 
	      '0' <= ch && ch <= '9' || ch == '_'))
	  return Token.Identifier(str.substring(begin, pos))
	pos += 1
      }
      // loop ended at end of string
      return Token.Identifier(str.substring(begin, pos))
    }
    
    // Case 2: a number
    if ('0' <= ch && ch <= '9' || ch == '.') {
      while (pos < str.length) {
	ch = str(pos)
	if (!('0' <= ch && ch <= '9' || ch == '.'))
	  return Token.Number(str.substring(begin, pos).toDouble)
	pos += 1
      }
      // loop ended at end of string
      return Token.Number(str.substring(begin, pos).toDouble)
    }

    // Case 3: a single character
    val s = str.substring(pos, pos + 1)
    pos += 1
    return Token.Symbol(s)
  }
}

// --------------------------------------------------------------------

object TestTokenizer {
  def main(args: Array[String]) {
    println("Welcome to the Tokenizer test routine")
    var oneLine = readLine("> ")
    while (oneLine != null && oneLine != "") {
      val tok = new Tokenizer(oneLine)
      while (!tok.token.isStop) {
	println("Token: " + tok.token.toString)
	tok.next()
      }
      println("Token: " + tok.token.toString) // show stop token
      oneLine = readLine("> ")
    }
  }
}

// --------------------------------------------------------------------
