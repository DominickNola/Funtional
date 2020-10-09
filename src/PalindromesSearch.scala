import java.util._
import scala.collection.convert.ImplicitConversions.`list asScalaBuffer`
import java.io.FileWriter

object PalindromesSearch {
  var target_count = 0
  var third_input = ""
  val filename = "OUTPUT_FILE_NAME.txt"
  val fw = new FileWriter(filename, true) //the true will append the new data
  var seeResults = ""
  var targetInt = 0

  // process the sum number(n) and the target number(m)
  def process_list(sumNum: Int, targetNum: Int, currentCombo: ArrayList[Integer]): Unit = {
    if (targetNum == 0 && currentCombo.contains(targetInt)) {
        // gets the combinations of n from the below else/while loop when target number == 0 and the combo contains m,
        // it then produces the permutations of each combination
        currentCombo.permutations.foreach(eachPerm => {
          // tests for palindrome and prints each one to an file called OUTPUT_FILE_NAME.txt if "y" is informed.
          if (eachPerm == eachPerm.reverse) {
            var i = 0
            while (i < eachPerm.length) {
              if (i < eachPerm.length - 1) {
//                print(eachPerm(i) + ", ")
                if (seeResults == "y")
                  fw.write(eachPerm(i) + ", ") //appends the string to the file
              } else {
//                print(eachPerm(i))
                if (seeResults == "y")
                  fw.write(eachPerm(i) + " ")
              }
              i += 1
            }
//            println()
            fw.write("\n")
            // keeps count of found palindromic sequences
            target_count += 1
          }
        })
    } else if (sumNum > targetNum) {
    } else {
      // finds the combinations of n recursively. When combinations of n are found, the permutations are found above.
      var countDown = sumNum
      while (countDown <= targetNum) {
        val newList = new ArrayList[Integer](currentCombo)
        newList.add(countDown)
        process_list(countDown, targetNum - countDown, newList)
        countDown += 1
      }
    }
  }

  def main(args: Array[String]): Unit = {
    // 1: Get Input form Terminal command-line and give suggestions if empty
    if (args.length <= 1 ) {
      println("Use: java PalindromesSearch n m [y]\n" +
        "[y]: when informed, all palindromic sequences must be saved to a file")
      System.exit(1)
    }
    val BigNumString = args(0)
    val BigNumInt = BigNumString.toInt
    if (args.length == 3) {
      seeResults = args(2)
    }
    val BigNum2String = args(1)
    targetInt = BigNum2String.toInt

//    val scanner_obj: Scanner = new Scanner(System.in)
    println("Welcome to the palindromic sequence project!")
    if (BigNumString == "") {
      println("Use: java PalindromesSearch n m [y]\n" +
        "[y]: when informed, all palindromic sequences must be saved to a file")
      System.exit(1)
    }

    // 2: Send empty array to the list processor to fill with current solution.
    val CurrentSolution = new ArrayList[Integer]()
    println("Parameter n = " + BigNumInt + "\nParameter m = " + targetInt)
    if(seeResults == "y")
      println( "Generating palindromic sequences.." )
    val time1 = System.nanoTime()
    process_list(1, BigNumInt, CurrentSolution)
    val time2 = System.nanoTime()
    if (seeResults == "y") {
      println("Done!")
//      fw.write("Done!\n")
    }
    val time = (time2 - time1) / 1000000000

    // parameter and time output
//    fw.write("\nParameter n = " + BigNumInt + ",\nParameter m = " + BigNum2Int + "\n")
    println("Number of filtered palindromic sequences found: " + target_count.toString)
//    fw.write("Number of targeted palindromic sequences found: " + target_count.toString + "\n")
    println("It took me " + time + "s")
//    fw.write("It took me " + time.toString + "s\n")
    fw.close()

    // output times
    // input 35 = 70 min(4240s) 9-7-20
    // input 34 = 41 min(2496s) 9-7-20
    // input 33 = 24 min(1457s) 9-7-20
  }
}
