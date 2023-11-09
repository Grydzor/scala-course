package net.study.functional.lesson11_collection

import scala.annotation.tailrec

object SelfWork extends App {

  /// odd sum event sum
  /// calculate odd sum and even sums from thr current list

  val nums = List(1, 2, 3, 4, 5, 7, 9, 11, 14, 12, 16)

  val oddSum = nums.filter(el => el % 2==1).sum
  val evenSum = nums.filter(el => el % 2==0).sum
  println(oddSum)
  println(evenSum)

  ////////////////////// write program to calculate the number of occurrences of each member in List

  val list = List(10, 5, 5, 11, 2, 2, 3, 6, 7, 8, 8, 1, 3, 15)

  val mapNumbers = list.groupBy(identity).mapValues(_.size)
  println(mapNumbers)

  /////////////////// write program which recognize palindrome in any List

  def is_Palindrome[A](list: List[A]): Boolean = {
    list == list.reverse
  }

  /////////////////// write function to calculate factorial using tail recursion

  def anotherFactorial(n: Int): BigInt = {
    @tailrec
    def factHelper(x: Int, accumulator: BigInt): BigInt =
      if (x <= 1) accumulator
      else factHelper(x - 1, x * accumulator)

    factHelper(n, 1)
  }

  ////////////////////////////////////
  /// Find the most 3 biggest companies
  /// Calculate total capitalization of all companies
  case class Company(name: String, capitalization: Double)

  val companies = Seq(
    Company("Apple", 2720),
    Company("Microsoft", 2570),
    Company("Saudi Arabian Oil", 2140),
    Company("Alphabet (Google)", 1590),
    Company("Amazon", 1490),
    Company("Nvidia", 1050),
    Company("Meta", 0.801),
    Company("Berkshire Hathaway", 0.760),
    Company("Tesla", 0.653),
    Company("Eli Lilly", 0.526)
  )

  println(companies.sortBy(comp => comp.capitalization).reverse.take(3))


  /////////////////// calculate turnover per regions excluding partner servers

  val EASTERN  = "EASTERN"
  val WESTERN  = "WESTERN"
  val SOUTHERN = "SOUTHERN"
  val NORTH    = "NORTH"

  val domains = Map(
    "firstDomain.com.ua" -> EASTERN,
    "secondDomain.com.ua" -> WESTERN,
    "thirdDomain.com.ua" -> WESTERN,
    "fourthDomain.com.ua" -> NORTH,
    "fifthDomain.com.ua" -> SOUTHERN,
    "sixthDomain.com.ua" -> EASTERN,
    "seventhDomain.com.ua" -> SOUTHERN,
    "eightDomain.com.ua" -> NORTH,
  )

  val ips = Map(
    "192.168.1.1" -> EASTERN,
    "192.168.1.2" -> EASTERN,
    "192.168.1.3" -> NORTH,
    "192.168.1.4" -> WESTERN,
    "192.168.1.5" -> NORTH,
    "192.168.1.6" -> EASTERN,
    "192.168.1.7" -> WESTERN,
    "192.168.1.8" -> SOUTHERN,
  )

  case class DailyTurnOver(host: String, isPartner: Boolean, turnover: Int)

  val servers = Seq(
    DailyTurnOver("firstDomain.com.ua", true, 10000),
    DailyTurnOver("thirdDomain.com.ua", false, 15000),
    DailyTurnOver("192.168.1.4", false, 15000),
    DailyTurnOver("192.168.1.7", true, 20000),
    DailyTurnOver("fourthDomain.com.ua", false, 13000),
    DailyTurnOver("192.168.1.5", false, 30000),
    DailyTurnOver("eightDomain.com.ua", false, 17000),
    DailyTurnOver("seventhDomain.com.ua", false, 10000),
    DailyTurnOver("sixthDomain.com.ua", false, 10000),
    DailyTurnOver("192.168.1.8", false, 9000),
  )

  val combinedMap = domains ++ ips
  servers.filter(p => !p.isPartner)
    .map(p => p.copy(host = combinedMap.get(p.host).get))
    .groupBy(p => p.host).mapValues(_.size)
}