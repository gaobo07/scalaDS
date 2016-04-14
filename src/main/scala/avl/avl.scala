package avl

// --------------------------------------------------------------------
// Mutable AVL Trees
// --------------------------------------------------------------------

case class Node(var key: Int, var value: Int,
		var height: Int, 
		var left: Node, var right: Node) {

  def leftHeight: Int = if (left == null) -1 else left.height
  def rightHeight: Int = if (right == null) -1 else right.height

  // recompute the height from subtree height
  // returns true if node is unbalanced
  def recomputeHeight(): Boolean = {
    val left = leftHeight
    val right = rightHeight
    height = (left max right) + 1
    val diff = right - left
    (diff < -1 || 1 < diff)
  }

}

class AVLTree extends TreeMap[Int, Int] {

  private var root: Node = null

  def firstKey: Int = findMin(root).key
  def lastKey: Int = findMax(root).key

  def apply(key: Int): Int = valueAt(find(key, root))
  def contains(key: Int): Boolean = find(key, root) != null
  def isEmpty: Boolean = (root == null)
  def height: Int = if (root == null) -1 else root.height
  def size: Int = if (root == null) 0 else size(root)

  def += (mapping: (Int, Int)) {
    val (key, value) = mapping
    root = put(key, value, root)
  }

  def -= (key: Int) { root = remove(key, root) }

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

  private def size(t: Node): Int = {
    val left = if (t.left == null) 0 else size(t.left)
    val right = if (t.right == null) 0 else size(t.right)
    left + right + 1
  }
  
  override def toString: String = if (root == null) "" else root.toString

  private def put(key: Int, value: Int, t: Node): Node = {
    if (t == null)
      Node(key, value, 0, null, null)
    else {
      if (key < t.key)
	t.left = put(key, value, t.left)
      else if (key > t.key)
	t.right = put(key, value, t.right)
      else
	// key == t.key
	t.value = value
      if (t.recomputeHeight())
	restructure(t)
      else
	t
    }
  }

  private def restructure(z: Node): Node = {
    if (z.rightHeight > z.leftHeight) {
      if (z.right.leftHeight > z.right.rightHeight)
	z.right = rotateRight(z.right)
      rotateLeft(z)
    } else { // z.leftHeight > z.rightHeight
      if (z.left.rightHeight > z.left.leftHeight)
	z.left = rotateLeft(z.left)
      rotateRight(z)
    }
  }

  private def rotateLeft(t: Node): Node = {
    val root = t.right
    t.right = root.left
    t.recomputeHeight()
    root.left = t
    root.recomputeHeight()
    root
  }

  private def rotateRight(t: Node): Node = {
    val root = t.left
    t.left = root.right
    t.recomputeHeight()
    root.right = t
    root.recomputeHeight()
    root
  }

  private def remove(key: Int, t: Node): Node = {
    if (t == null)
      null 
    else {
      if (key < t.key)
	t.left = remove(key, t.left)
      else if (key > t.key)
	t.right = remove(key, t.right)
      else if (t.left != null && t.right != null) { // Two children
	val u = findMin(t.right)
	t.key = u.key
	t.value = u.value
	t.right = removeMin(t.right)
      } else
	return (if (t.left != null) t.left else t.right)
      if (t.recomputeHeight())
	restructure(t)
      else
	t
    }
  }
  
  private def removeMin(t: Node): Node = {
    if (t.left != null) {
      t.left = removeMin(t.left)
      if (t.recomputeHeight())
	restructure(t)
      else
	t
    } else
      t.right
  }    
  
}

