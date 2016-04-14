package stack

/**
 * Created by DGA on 2016/4/14.
 * Think about a stack of books or dishes, one can only access the top of the stack
 */


trait Stack[T] {

  def push(el:T):Stack[T]
  def top:T
  def pop():T
  def isEmpty:Boolean


}
