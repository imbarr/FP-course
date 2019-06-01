package exercises

import simulacrum._

@typeclass trait Functor[F[_]] { self =>
  def map[A, B](fa: F[A])(f: A => B): F[B]
}
