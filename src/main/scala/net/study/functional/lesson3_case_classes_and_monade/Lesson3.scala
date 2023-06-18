package net.study.functional.lesson3_case_classes_and_monade

object Lesson3 extends App{
  case class Person(name: String, age: Int)

  val personA = Person.apply("Sasha", 20)
  val personB = Person("Sasha", 20)

  println(personA == personB)
  println(personA equals personB)
  println(personA eq personB)
  println(personA eq personA)

  val maybeInt: Option[Int] = Option(1)
  val maybeInt2: Option[Int] = None

  val result = maybeInt2.getOrElse({
    println("Calc")
    1
  })

  println(result)


}
