package net.study.functional.lesson1_basic

object Lesson1 extends App {

  val myString: String = "name" + 1
  var changable: String = "value"
  changable = myString

  println(changable)
  println(sum(5,10))

  def sum(int: Int, int2: Int): Int = int + int2
  val arsum = (int: Int, int2: Int) => int + int2

  val range = 1 to 10
  range.foreach(println(_))

  var capital = Map("US" -> "Washington", "France" -> "Paris")
  capital += ("Japan" -> "Tokyo")
  println(capital)

  case class MyClass(index: Int, name: String)
  val aClass = MyClass(1, "20")
  println(aClass.name)

}
