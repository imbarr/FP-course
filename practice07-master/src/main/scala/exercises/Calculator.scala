package exercises

import examples.States.State
import org.scalatest.FlatSpec

import scala.util.Try

//Напишите калькулятор, который позволяет вычислять сложные выражения без расстановки скобок
//нпр
//
//4 2 - 3 *  видим 4, кладём 4 на стек
//2 2 3 *  видим 2, кладём 2 на стек
//- 3 *  видим -, достаём 4 и 2 со стека, вычисляем 4 - 2 и кладём на стек 2 вместо 4 2 -
//2 3 *  видим 2, кладём 2 на стек
//3 *  видим 3, кладём 3 на стек
//*  видим *, достаём 2 и 3 со стека, вычисляем 2 * 3 и кладём на стек 6 вместо 2 3
//
//реализзуйте метод calcSimple для простых выражений
//реализуйте calcNested для сложных выражений со скобками
//
//5 баллов

object FromStringInteger {
  def unapply(s: String): Option[Int] = Try(s.toInt).toOption
}


object Calculator {

  type CalcState[A] = State[List[Int], A]

  def operand(num: Int): CalcState[Int] = State(stack => (num, num :: stack))

  def operator(func: (Int, Int) => Int): CalcState[Int] = State {
    case first :: second :: left =>
      val result = func(first, second)
      (result, result :: left)
    case _ => throw new IllegalArgumentException("Not enough elements in the stack")
  }

  def calcSimple(sym: String): CalcState[Int] =
    sym match {
      case FromStringInteger(number) => operand(number)
      case "+" => operator(_ + _)
      case "*" => operator(_ * _)
    }

  def calcNested(input: List[String]): CalcState[Int] =
    input match {
      case head :: tail => tail.foldLeft(calcSimple(head))((state, s) => for(_ <- state; c <- calcSimple(s)) yield c)
      case _ => throw new IllegalArgumentException("Input is empty")
    }
}

class CalculatorSpec extends FlatSpec {
  import Calculator._

  "calcSimple" should "work with example" in {
    val simpleExpr = for {
      _ <- calcSimple("1")
      _ <- calcSimple("2")
      _ <- calcSimple("+")
      _ <- calcSimple("3")
      _ <- calcSimple("4")
      _ <- calcSimple("+")
      answer <- calcSimple("*")
    } yield answer

    assert(simpleExpr.run(Nil)._1 == 21)
  }

  "calcNested" should "work with example" in {
    val nestedExpr = for {
      _ <- calcNested(List("1", "2", "+"))
      _ <- calcNested(List("3", "4", "+"))
      answer <- calcSimple("*")
    } yield answer

    assert(nestedExpr.run(Nil)._1 == 21)
  }
}
