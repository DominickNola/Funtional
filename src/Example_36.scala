object Example_36 {

  def main(args: Array[String]): Unit = {
    val array = Array(3, 8, 2, 1, 9)
    array.foreach(println)
    val evensOnly = array.filter(_ % 2 == 0)
    println(evensOnly.mkString(","))
  }
}