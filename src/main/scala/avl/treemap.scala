package avl

trait TreeMap[Key, Value] {
  def isEmpty: Boolean
  def contains(key: Key): Boolean
  def apply(key: Key): Value
  def += (mapping: (Key, Value)): Unit
  def -= (key: Key): Unit
  def firstKey: Key
  def lastKey: Key
  def height: Int
  def size: Int
}

