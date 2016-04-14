package calculator

// --------------------------------------------------------------------
// A Token represents a token in the input expression.  A token is
// either a number, an identifier, a fixed syntax element, or "stop".
// --------------------------------------------------------------------

object TokenType extends Enumeration {
  val Number, Identifier, Symbol, Stop = Value
}

class Token(val ttype: TokenType.Value, val text: String, 
	    val number: Double) {
  import TokenType._
  def isNumber: Boolean = { ttype == Number }
  def isIdentifier: Boolean = { ttype == Identifier }
  def isSymbol(s: String): Boolean = { ttype == Symbol && text == s }
  def isStop: Boolean = { ttype == Stop }
  override def toString: String = ttype match {
    case Number => "Number(%g)".format(number)
    case Identifier => "Identifier(" + text + ")"
    case Symbol => "Symbol(" + text + ")"
    case Stop => "Stop"
  }
}

object Token {
  def Number(num: Double): Token = new Token(TokenType.Number, null, num)
  def Stop(): Token = new Token(TokenType.Stop, null, 0.0)
  def Identifier(id: String): Token = new Token(TokenType.Identifier, id, 0.0)
  def Symbol(s: String): Token = new Token(TokenType.Symbol, s, 0.0)
}

