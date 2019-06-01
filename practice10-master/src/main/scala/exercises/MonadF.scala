package exercises

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import scala.language.higherKinds

//для Monad[Future]
//1) реализуйте pure
//2) реализуйте flatMap

object MonadF {

  trait Monad[M[_]] {
    def pure[A](a: A): M[A]
    def flatMap[A, B](m: M[A])(f: A => M[B]): M[B]
  }

  object Monad {
    implicit object FutureMonad extends Monad[Future] {
      override def pure[A](a: A): Future[A] = ???

      override def flatMap[A, B](m: Future[A])(f: A => Future[B]): Future[B] =
        ???
    }

  }
}
