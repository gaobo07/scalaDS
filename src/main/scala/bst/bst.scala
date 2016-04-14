package bst

// --------------------------------------------------------------------
// Immutable Binary Search Tree
// --------------------------------------------------------------------

case class Node(val key: Int, val value: Int,
		val left: Node, val right: Node)

class BinarySearchTree(private val root: Node) extends TreeMap[Int, Int] {

  // construct an empty search tree
  def this() { this(null) }

  def firstKey: Int = findMin(root).key
  def lastKey: Int = findMax(root).key

  def apply(key: Int): Int = valueAt(find(key, root))
  def contains(key: Int): Boolean = find(key, root) != null
  def isEmpty: Boolean = (root == null)
  def height: Int = if (root == null) -1 else height(root)
  def size: Int = if (root == null) 0 else size(root)

  def + (mapping: (Int, Int)): BinarySearchTree = {
    val (key, value) = mapping
    new BinarySearchTree(insert(key, value, root))
  }

  def - (key: Int): BinarySearchTree = {
    new BinarySearchTree(remove(key, root))
  }
  
  // --------------------------------------------------------------------
  // Internal methods
  // --------------------------------------------------------------------

  private def valueAt(t: Node): Int = {
    if (t == null) 
      throw new NoSuchElementException
    else
      t.value
  }
  
  private def findMin(n: Node): Node = {
    var t = n
    if (t != null)
      while (t.left != null)
	t = t.left
    t
  }
    
  private def findMax(n: Node): Node = {
    var t = n
    if (t != null)
      while (t.right != null)
	t = t.right
    t
  }
    
  private def find(key: Int, n: Node): Node = {
    var t = n
    while (t != null && key != t.key) {
      if (key < t.key)
	t = t.left
      else
	t = t.right
    }
    t
  }

  private def height(t: Node): Int = {
    val left = if (t.left == null) -1 else height(t.left)
    val right = if (t.right == null) -1 else height(t.right)
    (left max right) + 1
  }
  
  private def size(t: Node): Int = {
    val left = if (t.left == null) 0 else size(t.left)
    val right = if (t.right == null) 0 else size(t.right)
    left + right + 1
  }
  
  private def insert(key: Int, value: Int, n: Node): Node = {
    if (n == null)
      Node(key, value, null, null)
    else {
      if (key < n.key)
	Node(n.key, n.value, insert(key, value, n.left), n.right)
      else if (key > n.key)
	Node(n.key, n.value, n.left, insert(key, value, n.right))
      else // key == n.iKey
	Node(key, value, n.left, n.right)
    }
  }

  private def remove(key: Int, n: Node): Node = {
    if (n == null)
      null
    else {
      if (key < n.key) 
	Node(n.key, n.value, remove(key, n.left), n.right)
      else if (key > n.key)
	Node(n.key, n.value, n.left, remove(key, n.right))
      else if (n.left != null && n.right != null) { // Two children
	val u = findMin(n.right)
	Node(u.key, u.value, n.left, removeMin(n.right))
      } else if (n.left == null)
	n.right
      else
	n.left
    }
  }
  
  private def removeMin(n: Node): Node = {
    if (n == null)
      null
    else if (n.left != null)
      Node(n.key, n.value, removeMin(n.left), n.right)
    else
      n.right
  }   
 
  override def toString: String = if (root == null) "" else root.toString
}

