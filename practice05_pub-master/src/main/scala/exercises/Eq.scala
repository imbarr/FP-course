package exercises

/**
  * Реализуйте инстансы для typeclass Eq в объекте EqInstances:
  *  -- для примитивных типов (обязательно должна быть реализация для boolean, int, long)
  *  -- для класса exercises.Rational
  *  -- для классов для всех возможных их комбинаций в Option, List, Map
  *
  * Определите синтаксис для typeclass Eq в объекте EqSyntax.
  *
  * После определения всех инстансов и синтаксиса должны выполняться следующие проверки:
  *
  * import EqInstances._
  * import EqSyntax._
  *
  * 1 eqv 1 // возвращает true
  * 1 === 2 // возвращает false
  * 1 !== 2 // возвращает true
  * 1 === "some-string" // не компилируется
  * 1 !== Some(2) // не компилируется
  * List(true) === List(true) // возвращает true
  */
trait Eq[A] {
  def eqv(a: A, b: A): Boolean
}

object EqInstances {
}

object EqSyntax {
  implicit class EqOps[A](val a: A) extends AnyVal {

  }
}

object Tests {
  import EqInstances._
  import EqSyntax._

  //1 eqv 1 // возвращает true
  //1 === 2 // возвращает false
  //1 !== 2 // возвращает true
  //1 === "some-string" // не компилируется
  //1 !== Some(2) // не компилируется
  //List(true) === List(true) // возвращает true
}
