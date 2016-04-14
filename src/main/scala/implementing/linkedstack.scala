package implementing

// Implementation of Stack
// using linked nodes

class LinkedStack[T] extends Stack[T] {
  private class Node(val el: T, val next: Node)
  private var tos: Node = null
  
  def isEmpty: Boolean = (tos == null)

  def push(el: T): Stack[T] = {
    tos = new Node(el, tos)
    this
  }

  def top: T = {
    if (isEmpty)
      throw new NoSuchElementException("Empty stack")
    tos.el
  }

  def pop(): T = {
    val el = top
    tos = tos.next
    el
  }

  override def toString: String = {
    val s = new StringBuilder
    s ++= "LinkedStack("
    var p = tos
    while (p != null) {
      s ++= p.el.toString + ", "
      p = p.next
    }
    s ++= ")"
    s.toString
  }
}

