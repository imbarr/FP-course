package exercises

object DiffList {

  abstract class DiffList[A](calculate: List[A] => List[A]) {
    def prepend(s: List[A]): DiffList[A]

    def append(s: List[A]): DiffList[A]

    def result: List[A]
  }

  final class DiffListImpl[A](listFunc: List[A] => List[A]) extends DiffList[A](calculate = ???) {
    def prepend(s: List[A]): DiffList[A] = ???

    def append(s: List[A]): DiffList[A] = ???

    def result: List[A] = ???
  }

}
