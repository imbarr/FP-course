package exercises

import monix.eval.Task
import monix.execution.Scheduler
import scala.concurrent.Future
import scala.util.{Failure, Success, Try}

//1) Реализуйте конвертер из Task => Future в FutureOps
//2) Реализуйте конвертер из Future => Task в TaskOps
//2) Реализуйте tryF вида (=> Try[T]) => Future[T]

object Converter {

  object exercises {
    type S[_] = Scheduler

    def tryF[T](t: => Try[T]): Future[T] = {
      ???
    }

    implicit class FutureOps[A](self: => Future[A]) {
      def toTask: Task[A] = ???
    }

    implicit class TaskOps[A](val self: Task[A]) extends AnyVal {
      def unsafeToF[_: S](): Future[A] = ???
    }
  }
}
