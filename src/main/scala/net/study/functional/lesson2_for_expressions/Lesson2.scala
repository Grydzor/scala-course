package net.study.functional.lesson2_for_expressions

object Lesson2 extends App{

  val seqInt = Seq(1,2,3,4,5)

//  seqInt.foreach(println)

//  for( x <- seqInt ){
//    println(x)
//  }
  val result = seqInt.map(x => x*x)

  val result2 = for{
    n <- seqInt
  } yield n * n

  val flatResult = seqInt.flatMap { n =>
    seqInt.map(m => s"$m * $n = ${m * n}")
  }

//  flatResult.foreach(println)

  val param1 = Some(1)
  val param2 = Some(2)
  val param3 = Some(3)

  val forOptComprehensionResult = for {
    a <- param1
    b <- param2
    c <- param3
  } yield {
    a * b * c
  }

  forOptComprehensionResult.foreach(println)
}
