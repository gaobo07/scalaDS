package bst

trait TreeMap[Key, Value] {
  def isEmpty: Boolean
  def contains(key: Key): Boolean
  def apply(key: Key): Value
  def + (mapping: (Key, Value)): TreeMap[Key, Value]
  def - (key: Key): TreeMap[Key, Value]
  def firstKey: Key
  def lastKey: Key
  def height: Int
  def size: Int
}
