package implementing


trait Queue[T] {
  def clear(): Unit
  def isEmpty: Boolean
  def head: T
  def dequeue(): T
  def enqueue(elem: T*): Unit
}

