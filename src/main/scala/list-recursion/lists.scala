def display1[T](L: List[T]) {
  if (L != Nil) {
    print(L.head); print(" "); display1(L.tail)
  }
}

def display2[T](L: List[T]) {
  L match {
    case Nil =>
    case x :: xs => print(x); print(" "); display2(xs)
  }
}

def length1[T](L: List[T]): Int = {
  L match {
    case Nil => 0
    case x :: xs => 1 + length1(xs)
  }
}

// return (length of list L) + plus
def lengthHelper[T](L: List[T], plus: Int): Int = {
  L match {
    case Nil => plus
    case x :: xs => lengthHelper(xs, plus + 1)
  }
}

def length2[T](L: List[T]): Int = lengthHelper(L, 0)

def take[T](L: List[T], n: Int): List[T] = {
  if (n <= 0)
    Nil
  else
    L match {
      case Nil => Nil
      case x :: xs => x :: take(xs, n-1)
    }
}

def drop[T](L: List[T], n: Int): List[T] = {
  if (n <= 0)
    L
  else 
    L match {
      case Nil => Nil 
      case x :: xs => drop(xs, n-1)
    }
}
  
def concat[T](L1: List[T], L2: List[T]): List[T] = {
  L1 match {
    case Nil => L2
    case x :: xs => x :: concat(xs, L2)
  }
}

