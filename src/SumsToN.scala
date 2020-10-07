import java.util.Scanner
import java.util._
import scala.collection.convert.ImplicitConversions.`list asScalaBuffer`
import scala.collection.mutable
import scala.util.control.Breaks.{break, breakable}

object SumsToN {
  var count = 0
  var target_count = 0
  var third_input = ""

  def process_list(mainList: ArrayList[ArrayList[Integer]], x: Int, y: Int,
                   currentList: ArrayList[Integer]): Unit = {

    if (y == 0) {
      mainList.add(currentList)
      return
    } else if (x > y) {
      return
    } else {
      var j = x
      while (j <= y) {
        val newList = new ArrayList[Integer](currentList)
        newList.add(j)
        process_list(mainList, j, y - j, newList)
        j += 1
      }
    }
  }

  def permutation(list: ArrayList[Integer], second_input: Int): Unit = {
    val perm_list = list.permutations.toList
//    println(perm_list)
    var i = 0
    while ( i <= perm_list.length - 1) {
      if (isPalindrome(perm_list(i))) {
        breakable {
          for (q <- 0 until perm_list(i).length - 1) {
            if (second_input == perm_list(i)(q)) {
              if (third_input == "y") {
                for (a <- 0 until perm_list(i).length - 1) {
                  print(perm_list(i)(a) + ", ")
                }
                print(perm_list(i).last)
                println()
              }
              target_count += 1
              break
            }
          }
        }
        count += 1
      }
      i += 1
    }
  }:Unit

  def isPalindrome(list: mutable.Buffer[Integer]):Boolean = {
    list == list.reverse
  }

  def main(args: Array[String]): Unit = {

    val scanner_obj: Scanner = new Scanner(System.in)
    println("Welcome to the palindromic sequence project!")
    println("Enter n(sum total) and m(target int) to see the results: ")
    val n = scanner_obj.nextLine()
    if (n.equals("")) {
      println("Use: java PalindromesSearch n m [y]\n" +
        "[y]: when informed, all palindromic sequences must be saved to a file")
      System.exit(1)
    }
    val o = n.toInt
    val m = scanner_obj.nextLine()
    if (m.equals("")) {
      println("Use: java PalindromesSearch n m [y]\n" +
        "[y]: when informed, all palindromic sequences must be saved to a file")
      System.exit(1)
    }
    val p = m.toInt
    third_input = scanner_obj.nextLine()
    val SolutionsList = new ArrayList[ArrayList[Integer]]()
    val CurrentSolution = new ArrayList[Integer]()

    process_list(SolutionsList, 1, o, CurrentSolution)

    println("Parameter n = " + n + ",\nParameter m = " + m)
    val time1 = System.nanoTime()

//    println(SolutionsList)

    var k = 0
    while (k <= SolutionsList.length - 1) {
      permutation(SolutionsList.get(k), p)
      k += 1
    }

    println("Done!")
    val time2 = System.nanoTime()
    val time = (time2 - time1) / 1000000000
    println("It took me " + time.toString + "s")
    println("Number of total palindromic sequences found: " + count.toString)
    println("Number of targeted palindromic sequences found: " + target_count.toString)
  }
}
