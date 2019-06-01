package exercises

//  while, return и var запрещены
//  1 балл
object PAF {

  //  Напишите частичную функцию, определённую только на листах размера 3 и 4
  //  В кач-ве рез-та функция возвращает сумму эл-тов
  val f: PartialFunction[List[Int], Int] = { case x if x.size == 3 || x.size == 4 => x.sum }

  //  Напишите частичную функцию, которая вернёт сумму эл-тов кратным 3м
  val g: PartialFunction[List[Int], Int] = {
    case x => { val r = for {i <- x if i%3 == 0} yield i
      r.sum }
  }

  val ipt = List(List(1, 2, 3), List(3, 4, 5, 6), List(7, 8, 9, 0))
}

object PAFApp extends App {
  import exercises.PAF.{f, g, ipt}

  println(g(ipt.map(f)))
}