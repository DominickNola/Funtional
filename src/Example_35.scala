import scala.collection.mutable.ArrayBuffer

object Example_35 {

  def map(array: Array[Int], func: Int => Int) = {
    val out = new Array[Int](array.length)
    for (i <- 0 until array.length)
      out(i) = func(array(i))
    out
  }

  def filter(array: Array[Int], func: Int => Boolean) = {
    val out = ArrayBuffer[Int]()
    for (el <- array) {
      if (func(el))
        out += el
    }
    out.toArray[Int]
  }

  def increment(el: Int) = el + 1

  def double(el: Int) = el * 2

  def main(args: Array[String]) = {
    val array = Array(3, 8, 2, 1, 9)
    println(array.mkString(","))
    val arrayIncremented = map(array, increment) // or you can use "arrayIncremented = array.map(increment)"
    println(arrayIncremented.mkString(","))
    val arrayDoubled = map(array, double) // or you can use "arrayDoubled = array.map(double)"
    println(arrayDoubled.mkString(","))

    val evensOnly = filter(array, _ % 2 == 0)
    println(evensOnly.mkString("'"))
  }
}