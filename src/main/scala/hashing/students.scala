package hashing


import scala.io.StdIn.readLine

case class Student(name: String, id: Int, dept: String, alias: String)

object Student {
  def readStudents(fname: String): List[Student] = {
    val F = scala.io.Source.fromFile(fname)
    val B = new scala.collection.mutable.ListBuffer[Student]
    for (line <- F.getLines()) {
      val Array(id, name, dept, alias) = line.split("\\|")
      B += Student(name, id.toInt, dept, alias)
    }
    F.close()
    B.toList
  }

  def main(args: Array[String]) {
    val fname = args(0)
    val L = readStudents(fname)
    println(L)

    val t: HashMap[Int, Student] = new Chaining[Student]
    // val t: HashMap[Int, Student] = new LinearProbing[Student]
    for (s <- L)
      t += (s.id -> s)

    while (true) {
      val line = readLine("Enter student id> ")
      if (line == "") {
	t.printTable()
	sys.exit(0)
      }
      val id = line.toInt
      if (t contains id) 
	println(t(id))
      else
	printf("No student with id %d\n", id)
    }
  }
}
