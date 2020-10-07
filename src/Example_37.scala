object Example_37 {

  def reduceLeft(array: Array[Int], function: (Int, Int) => Int): Int = {
    if (array.length == 0)
      0
    else {
      var result = array(0)
      for (i <- 1 until array.length)
        result = function(result, array(i))
      result
    }
  }

  def main(args: Array[String]) = {
    val array = Array(2, 7, 8, 1, 3)
    println(array.reduceLeft(_ * _))
  }
}