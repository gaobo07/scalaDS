package implementing


trait Stack[T] {
  def push(el: T): Stack[T]
  def top: T
  def pop(): T
  def isEmpty: Boolean
}

