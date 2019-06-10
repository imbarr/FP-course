package exercises

import org.scalatest.FlatSpec

//2.5 балла
//Необходимо реализовать prepend и append и result
//
//Метод prepend принимает на вход список s, добавляемый в начало
//Вернуть надо новый объект, сконструировав его от новой функции
//Эта функция должна возвращать список s , добавленный в начало к списку, возвращаемому из calculate
//
//Метод append принимает на вход список s, добавляемый в конец.
//Вернуть надо новый объект, сконструировав его от новой функции.
//Эта функция должна возвращать результат применения функции calculate к конкатенации списка  s и аргумента этой функции.
//
//Метод result применяет все накопленные операции и отдаёт итоговый список.
//
//val l1 = List(1,2,3)
//val l2 = List(4,5,6)
//val dl = new DiffListImpl[Int](identity)
//
//val result = dl.append(l2).prepend(l1).result // List(1,2,3,4,5,6)

object DiffList {

  abstract class DiffList[A](calculate: List[A] => List[A]) {
    def prepend(s: List[A]): DiffList[A]

    def append(s: List[A]): DiffList[A]

    def result: List[A]
  }

  final class DiffListImpl[A](listFunc: List[A] => List[A]) extends DiffList[A](listFunc) {
    def prepend(s: List[A]): DiffList[A] = new DiffListImpl[A](s ::: listFunc(_))

    def append(s: List[A]): DiffList[A] = new DiffListImpl[A](listFunc(_) ::: s)

    def result: List[A] = listFunc(Nil)
  }

}

class DiffListTests extends FlatSpec {
  import DiffList._

  "DiffList" should "work like in the example" in {
    val l1 = List(1,2,3)
    val l2 = List(4,5,6)
    val dl = new DiffListImpl[Int](identity)
    val result = dl.append(l2).prepend(l1).result
    assert(result == List(1,2,3,4,5,6))
  }
}
