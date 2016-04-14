package hashing


trait HashMap[Key, Value] {
  def update(key: Key, value: Value): Unit
  def += (mapping: (Key, Value)): Unit
  def contains(key: Key): Boolean
  def apply(key: Key): Value
  def printTable(): Unit
}
