import java.util.Scanner
import java.util._
import scala.collection.convert.ImplicitConversions.`list asScalaBuffer`
import scala.collection.mutable
import java.io._
import java.io.FileWriter

object PalindromesSearch {
  var count = 0
  var target_count = 0
  var third_input = ""
  val filename = "OUTPUT_FILE_NAME.txt"
  val fw = new FileWriter(filename, true) //the true will append the new data

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
    val indexing: mutable.Buffer[Integer] = list.map(z => z)
    indexing.permutations.foreach(x => {
      val perms = x
      if(isPalindrome(perms) && perms.contains(second_input)){
        if (third_input == "y") {
          var i = 0
          while (i < perms.length) {
            if (i < perms.length - 1) {
              print(perms(i) + ", ")
              fw.write(perms(i) + ", ") //appends the string to the file
            } else {
              print(perms(i))
              fw.write(perms(i) + " ")
            }


            i += 1
          }
//          print(perms.last)
//          fw.write(perms.length - 1)
//          fw.write("\n")
          println()
          fw.write("\n")
        }
      target_count += 1
      }
    })

  }:Unit

  def isPalindrome(list: mutable.Buffer[Integer]):Boolean = {
    list == list.reverse
  }

  def main(args: Array[String]): Unit = {


    // 1: Get Input
    val scanner_obj: Scanner = new Scanner(System.in)
    println("Welcome to the palindromic sequence project!")
    println("Enter n(sum total) and m(target int) to see the results: ")
    val nextLine_1 = scanner_obj.nextLine()
    if (nextLine_1.equals("")) {
      println("Use: java PalindromesSearch nextLine_1 nextLine_2 [y]\nextLine_1" +
        "[y]: when informed, all palindromic sequences must be saved to a file")
      System.exit(1)
    }
    val nextInt = nextLine_1.toInt
    val nextLine_2 = scanner_obj.nextLine()
    if (nextLine_2.equals("")) {
      println("Use: java PalindromesSearch nextLine_1 nextLine_2 [y]\nextLine_1" +
        "[y]: when informed, all palindromic sequences must be saved to a file")
      System.exit(1)
    }
    val nextInt_2 = nextLine_2.toInt
    third_input = scanner_obj.nextLine()
    val SolutionsList = new ArrayList[ArrayList[Integer]]()
    val CurrentSolution = new ArrayList[Integer]()

    // 2: Send empty arrays to the list processor to fill...works very fast
    process_list(SolutionsList, 1, nextInt, CurrentSolution)

    println("Parameter n = " + nextLine_1 + ",\nParameter m = " + nextLine_2)
    fw.write("\nParameter n = " + nextLine_1 + ",\nParameter m = " + nextLine_2 + "\n")
    if(third_input == "y") println("Generating palindromic sequences..")
    val time1 = System.nanoTime()

//        println(SolutionsList)

    // 3: Get all the permutations from the unique combinations and filter
    //    using second input nextInt_2
    var k = 0
    while (k <= SolutionsList.length - 1) {
      permutation(SolutionsList.get(k), nextInt_2)
      k += 1
    }

    println("Done!")
    fw.write("Done!\n")
    val time2 = System.nanoTime()
    val time = (time2 - time1) / 1000000000
    println("Number of targeted palindromic sequences found: " + target_count.toString)
    fw.write("Number of targeted palindromic sequences found: " + target_count.toString + "\n")
    println("It took me " + time.toString + "s")
    fw.write("It took me " + time.toString + "s\n")
    fw.close()
  }
}
