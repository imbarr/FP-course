package exercises

import examples.States.State

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


object Calculator {

  type CalcState[A] = State[List[Int], A]

  def operand(num: Int): CalcState[Int] = ???

  def operator(func: (Int, Int) => Int): CalcState[Int] = ???

  def calcSimple(sym: String): CalcState[Int] = ???

  val simpleExpr = for {
    _ <- calcSimple("1")
    _ <- calcSimple("2")
    answr <- calcSimple("+")
  } yield answr


  println(simpleExpr.run(Nil)._1 == 3)



  def calcNested(input: List[String]): CalcState[Int] = ???

  val nestedExpr = for {
    _ <- calcNested(List("1", "2", "+"))
    _ <- calcNested(List("3", "4", "+"))
    answr <- calcSimple("*")
  } yield answr

  println(nestedExpr.run(Nil)._1 == 21)

}
