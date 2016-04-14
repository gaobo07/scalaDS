package implementing

// Implementation of Stack
// using a fixed size array

import scala.reflect.ClassTag

object FixedStack {
  val MAXSIZE = 100
}

class FixedStack[T : ClassTag] extends Stack[T] {
  private val data = new Array[T](FixedStack.MAXSIZE)
  private var tos = 0
  
  def push(el: T): Stack[T] = {
    if (tos == data.length)
      throw new IndexOutOfBoundsException("Fixed stack overflow")
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
    "FixedStack(" + (data take tos mkString ", ") + ")"
  }
}

