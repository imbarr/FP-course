package exercises

//  while, return и var запрещены
//  0.5 балла
object Compose {

  def compose[A, B, C](f: B => C, g: A => B): A => C = { x: A => f(g(x)) }


  //  реализуйте функцию
  def composeFunc[R, T](f: R => T => T, g: (T => T) => R, h: R => String) = compose(compose(h, g), f)

  //  с помощью функции composeFunc и for'а выведите рез-т
  val f: (Int => Double => Double) = (i: Int) => { (d: Double) => i * (math.Pi * d) }
  val g: ((Double => Double) => Int) = (f: (Double => Double)) => f(42).toInt
  val h: Int => String = (i: Int) => (i * 2).toString
  val ints = List(1, 2, 3, 4)

  val rslt: List[String] =
      for{
         i <- ints
      } yield composeFunc(f, g, h)(i)

}

object ComposeApp extends App {
  import Compose._
  println(rslt)
}