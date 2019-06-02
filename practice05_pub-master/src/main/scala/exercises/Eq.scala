package exercises

import org.scalatest.{FlatSpec, NonImplicitAssertions}

/**
  * Реализуйте инстансы для typeclass Eq в объекте EqInstances:
  * -- для примитивных типов (обязательно должна быть реализация для boolean, int, long)
  * -- для класса exercises.Rational
  * -- для классов для всех возможных их комбинаций в Option, List, Map
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
  import EqSyntax._

  class BasicEq[T] extends Eq[T] {
    override def eqv(a: T, b: T): Boolean = a == b
  }

  implicit val intEq = new BasicEq[Int]
  implicit val booleanEq = new BasicEq[Boolean]
  implicit val longEq = new BasicEq[Long]

  implicit val rationalEq: Eq[Rational] = (a: Rational, b: Rational) => {
    a.numerator * b.denumerator === a.denumerator * b.numerator
  }

  implicit def optionEq[T: Eq]: Eq[Option[T]] = (a: Option[T], b: Option[T]) =>
    (a, b) match {
      case (None, None) => true
      case (Some(first), Some(second)) => first === second
      case _ => false
    }

  implicit def listEq[T: Eq]: Eq[List[T]] = (a: List[T], b: List[T]) =>
    a.length == b.length && (a zip b).forall { case (first, second) => first === second }

  implicit def mapEq[K: Eq, V: Eq]: Eq[Map[K, V]] = (a: Map[K, V], b: Map[K, V]) => {
    def subset(a: Map[K, V], b: Map[K, V]): Boolean =
      a.forall { case (key, value) => b.get(key) === Some(value) }

    subset(a, b) && subset(b, a)
  }
}

object EqSyntax {

  implicit class EqOps[A: Eq](val a: A) {
    def ===(other: A): Boolean = implicitly[Eq[A]].eqv(a, other)

    def eqv(other: A): Boolean = a === other

    def !==(other: A): Boolean = !(a === other)
  }

}

class EqTests extends FlatSpec with NonImplicitAssertions {

  import EqInstances._
  import EqSyntax._

  "Eq" should "work like in the example" in {
    assert(1 eqv 1)
    assert(!(1 === 2))
    assert(1 !== 2)
    assertDoesNotCompile(""" 1 === "some-string" """)
    assertDoesNotCompile(""" 1 !== Some(2) """)
    assert(List(true) === List(true))
  }

  it should "work with another examples" in {
    assert(Map(1 -> 2L, 2 -> 3L) === Map(1 -> 2L, 2 -> 3L))
    assert(Map(1 -> 2L, 2 -> 3L) !== Map(1 -> 2L, 2 -> 4L))
  }
}
