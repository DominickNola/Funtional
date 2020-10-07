import scala.collection.IterableOnce.iterableOnceExtensionMethods
import scala.collection.mutable.ArrayBuffer
import scala.math

/*
 * CS3210 - Principles of Programming Languages - Fall 2020
 * Instructor: Thyago Mota
 * Description: Activity 13 - Functional Programming
 */

object FunctionalProgramming {

  /* TODO #1
  Write the function *values* specified below:
  values(fun: (Int) => Int, low: Int, high: Int): Seq[(Int, Int)]
  Function *values* returns a sequence of integer pairs, where the first value ranges from
  [low, high] and the second value is *fun* of the first value.
  For example:
  values(x => x * x, -2, 3) should produce the sequence: (-2, 4), (-1, 1), (0, 0), (1, 1), (2, 4), (3, 9).
   */

  def values(fun: Int => Int, low: Int, high: Int): Seq[(Int, Int)] = {
    // create array buffer to store tuples
    val seq = new ArrayBuffer[(Int, Int)]
    for (value <- low to high) {
      seq += ((value, value))
    }
    seq.toSeq
  }


  /* TODO #3
  Using Scala’s foldLeft function, implement the factorial function without an explicit loop.
  Hint: use the to() function to generate a range.
   */

  def factorial(a: Int):Unit = {
    (a to 1 by -1).foldLeft(1)(_ * _)
  }:Unit

  def perm(a: String):Unit = {
    println("// 1")
    println(a)
    println(List(1, 2, 3).permutations.toList)
    println(a.permutations.toList)
  }:Unit

  // pass a list to a function
  def perm2(list: Int*) = {
    println("// 2")
//    println(list.permutations.toList)
    val x = list.permutations.toList
    println(x)
    val z1 = x(1)
    println(z1)
    println(isPalindrome(z1))
    val z2 = x(2)
    println(z2)
    println(isPalindrome(z2))

    //    println(x(1)(2))


    //    println(isPalindrome(pals:_*))
//    println(x.toString())
    // list.foreach(println(_))
  }:Unit

  def isPalindrome[A](list: Seq[Int]):Boolean = {
    list == list.reverse
  }

  def main(args: Array[String]): Unit = {
    // testing *values* function
     val seq = values(x => x * x, -2, 3)
     println(seq.mkString(", "))

    /* TODO #2
    Using Scala’s *reduceLeft* function, find the largest element of an array.
    Hint: use Math’s *max* function.
    */
    val array = Array(2, 7, 1, 8, 3)
     println("Largest element is " + array.reduceLeft(math.max))

    // testing *factorial* function
    println("5! = " + factorial(5))

    println(perm("123"))

    val list = List(2,1,2)
    println(perm2(list:_*))

  }
}