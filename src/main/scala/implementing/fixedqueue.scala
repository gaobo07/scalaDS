package implementing

// Implementation of Queue
// using a fixed size array

import scala.reflect.ClassTag

object FixedQueue {
  val MAXSIZE = 8
}

class FixedQueue[T : ClassTag] extends Queue[T] {
  private val data = new Array[T](FixedQueue.MAXSIZE)
  private var front = 0  // where next element is dequeued
  private var rear = 0   // where next element is enqueued

  def isEmpty: Boolean = (front == rear)

  // we always keep one slot empty to distinguish full from empty
  def isFull: Boolean = ((rear + 1) % data.length == front)

  def enqueue(elems: T*) {
    for (el <- elems) {
      if (isFull)
	throw new IndexOutOfBoundsException("FixedQueue overflow")
      data(rear) = el
      rear = (rear + 1) % data.length
    }
  }

  def head: T = {
    if (isEmpty)
      throw new NoSuchElementException("Empty queue")
    data(front)
  }

  def dequeue(): T = {
    val el = head
    front = (front + 1) % data.length
    el
  }

  def clear() {
    front = 0
    rear = 0
  }

  override def toString: String = 
    "FixedQueue([" + data.mkString(",") + "], " + rear + ", " + front + ")"
}  
