package exercises

import org.scalatest.FlatSpec

import scala.annotation.tailrec

object Combinators1 {
//  2.5 балла
//  Есть цепочка hefEgGeGFEgGgeHE
//  в данной цепочке есть различные типы частиц
//  f, e, h, g положительно заряженные частицы
//  F, E, H, G отрицательно заряженные частицы
//  если частицы одного типа с разной полярностью стоят вместе в цепочке, они реагируют и исчезают
//  проход слева направо
//
//  hefEgGeGFEgGgeHE <- gG прореагировали
//  hefEeGFEgGgeHE <- Ee прореагировали
//  hefGFEgGgeHE <- Gg или gG
//  hefGFEgeHE <- итоговая цепочка, в которой 10 частиц
//
//  Напишите функцию, используя комбинаторы стандартной библиотеки,
//  которая проведёт полную реакцию и вернёт кол-во частиц в итоговой цепочке
//  в примере выше это 10

  def react(chain: String): Int =
    simplify("", chain).length

  @tailrec
  private def simplify(start: String, end: String): String = {
    end match {
      case "" => start
      case _ =>
        start match {
          case "" => simplify(end.head.toString, end.tail)
          case _ =>
            if (canBeSimplified(start.last, end.head))
              simplify(start.init, end.tail)
            else
              simplify(start + end.head, end.tail)
        }
    }
  }

  private def canBeSimplified(first: Char, second: Char): Boolean =
    first.isLower && second.isUpper && first == second.toLower ||
    first.isUpper && second.isLower && first == second.toUpper
}

class Combinators1Tests extends FlatSpec {
  import Combinators1._

  "react" should "work with trivial chain" in {
    assert(react("") == 0)
  }

  it should "work with not simplifiable chain" in {
    val chain = "hefEgFeGFEgEgeHE"
    assert(react(chain) == chain.length)
  }

  it should "work with once simplifiable chain" in {
    assert(react("heEG") == 2)
  }

  it should "work with fully simplifiable chain" in {
    assert(react("fehgGHEF") == 0)
  }

  it should "work with complex simplifiable chain" in {
    assert(react("HehgGHEHHgGeE") == 3)
  }
}