package exercises

import org.scalatest.FlatSpec

/**
  * Для сортировки коллекций в стандартной библиотеке scala используются traits scala.math.Ordering,
  * scala.math.Ordered. Изучите как устроены эти трейты и как они используются для сортировки на примере
  * методов List.sorted, List.sortWith. Постройте реализацию Ordering для сортировки класса Rational:
  * a. по возрастанию
  * б. по убыванию
  * в. по возрастанию абсолютного значения
  * и воспользуйтесь ими для сортировки списков.
  */
object RationalOrderings {

  val naturalAscendingOrdering: Ordering[Rational] = (a: Rational, b: Rational) =>
    a.numerator * b.denumerator - a.denumerator * b.numerator

  val naturalDescendingOrdering: Ordering[Rational] = naturalAscendingOrdering.reverse

  val absoluteAscendingOrdering: Ordering[Rational] = (a: Rational, b: Rational) =>
    math.abs(a.numerator * b.denumerator) - math.abs(a.denumerator * b.numerator)

  def descNaturalSort(xs: List[Rational]): List[Rational] =
    xs.sortWith(naturalDescendingOrdering.lt)

  def ascAbsoluteSort(xs: List[Rational]): List[Rational] =
    xs.sorted(absoluteAscendingOrdering)
}

class RationalOrderingsTests extends FlatSpec {
  import RationalOrderings._

  val list = List(Rational(1, 3), Rational(1, 2), Rational(1, 1), -Rational(1, 4))

  "descNaturalSort" should "sort list" in {
    val expected = List(Rational(1, 1), Rational(1, 2), Rational(1, 3), -Rational(1, 4))
    val actual = descNaturalSort(list)
    assert(expected == actual)
  }

  "ascAbsoluteSort" should "sort list" in {
    val expected = List(-Rational(1, 4), Rational(1, 3), Rational(1, 2), Rational(1, 1))
    val actual = ascAbsoluteSort(list)
    assert(expected == actual)
  }
}
