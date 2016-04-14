package implementing

// Implementation of Stack
// using a growing array

import scala.reflect.ClassTag

object GrowStack {
  private val DEFAULTSIZE = 32
}

class GrowStack[T : ClassTag] extends Stack[T] {
  private var data = new Array[T](GrowStack.DEFAULTSIZE)
  private var tos = 0
  
  private def grow() {
    val old = data
    data = new Array[T](2 * old.length)
    for (i <- 0 until tos)
      data(i) = old(i)
  }

  def push(el: T): Stack[T] = {
    if (tos == data.length)
      grow()
    data(tos) = el
    tos += 1
    this
  }

  def top: T = {
    if (isEmpty)
      throw new NoSuchElementException("Empty stack")
    data(tos-1)
  }

  def pop(): T = {
    val el = top
    tos -= 1
    el
  }

  def isEmpty: Boolean = (tos == 0)

  override def toString: String = {
    "GrowStack(" + (data take tos mkString ", ") + ")"
  }
}
