package exercises

import org.scalatest.FlatSpec

object Combinators2 {

//    2.5 балла
//    Вам дан список идшников необходимо вычислить контрольную сумму списка
//    Для этого нужно посчитать кол-во букв встречающихся ровно(!) 2 или ровно(!) 3 раза
//    Если есть несколько букв встречающихся ровно 2 или 3 раза, учитывается только одно вхождение
//
//    abcdef все буквы различны
//    bababc  две a и 3 b, учитываются все                    | 2 -> 1, 3 -> 1
//    abbcde две b, больше тройных или парных нет             | 2 -> 1
//    abcccd три c, больше тройных или парных нет             | 3 -> 1
//    aabcdd две a и две b, наличие пары учитывается 1 раз    | 2 -> 1
//    abcdee две e                                            | 2 -> 1
//    ababab три a и три b, наличие тройки учитывается 1 раз  | 3 -> 1
//
//    контрольная сумма = (кол-во пар) * (кол-во троек)
//    checksum = 4 * 3
//
//  Решить задачу использую только комбинаторы
//  Решение  - непрерывная цепочка комбинаторов, никаких промежуточных вычислений(!)
//  Переносы на следующую строчку делать можно)))


  def checksum(stream: Stream[String]): Int = {
    stream.map(
      _.foldLeft(Map[Char, Int]())((map, char) => if (map.contains(char)) map + (char -> (map(char) + 1)) else map + (char -> 1))
    ).map(
      map => List(map.count(_._2 == 2) min 1, map.count(_._2 == 3) min 1)
    ).foldLeft(List(0, 0))((total, pair) => total.zip(pair).map(p => p._1 + p._2))
      .map(_ max 1)
      .product
  }
}

class Combinators2Tests extends FlatSpec {
  import Combinators2._

  "checksum" should "solve example correctly" in {
    val stream = List(
      "abcdef",
      "bababc",
      "abbcde",
      "abcccd",
      "aabcdd",
      "abcdee",
      "ababab"
    ).toStream
    assert(checksum(stream) == 12)
  }

  it should "return 1 for empty stream" in {
    assert(checksum(Nil.toStream) == 1)
  }
}
