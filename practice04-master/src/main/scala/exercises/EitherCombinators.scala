package exercises

//2.5 балла
object EitherCombinators {

  sealed trait Either[+E, +A] {
    def map[B](f: A => B): Either[E, B] =
      this match {
        case left: Left[E] => left
        case Right(a) => Right(f(a))
      }

    def flatMap[EE >: E, B](f: A => Either[EE, B]): Either[EE, B] =
      this match {
        case left: Left[E] => left
        case Right(a) => f(a)
      }

    def orElse[EE >: E, B >: A](b: => Either[EE, B]): Either[EE, B] =
      this match {
        case _: Left[E] => b
        case right: Right[A] => right
      }

    def map2[EE >: E, B, C](b: Either[EE, B])(f: (A, B) => C): Either[EE, C] =
      for {
        a <- this
        bb <- b
      } yield f(a, bb)
  }

  case class Left[+E](get: E) extends Either[E, Nothing]

  case class Right[+A](get: A) extends Either[Nothing, A]

  object Either {
    def traverse[E, A, B](es: List[A])(f: A => Either[E, B]): Either[E, List[B]] =
      es.foldLeft[Either[E, List[B]]](Right(Nil)) { (acc, value) =>
        f(value) match {
          case Right(result) => acc.map(result :: _)
          case Left(error) => Left(error)
        }
      }

    def sequence[E, A](es: List[Either[E, A]]): Either[E, List[A]] = ???
  }

}
