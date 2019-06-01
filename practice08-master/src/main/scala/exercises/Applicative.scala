package exercises

import simulacrum._

import scala.language.higherKinds

//1. Рейлизуйте функции Applicative через apply и pure

@typeclass trait Applicative[F[_]] extends Functor[F] { self =>
  def pure[A](a: A): F[A]

  def apply[A, B](fa: F[A])(ff: F[A => B]): F[B]

  def apply2[A, B, C](fa: F[A], fb: F[B])(ff: F[(A, B) => C]): F[C] = ???

  override def map[A, B](fa: F[A])(f: A => B): F[B] = ???

  def map2[A, B, Z](fa: F[A], fb: F[B])(f: (A, B) => Z): F[Z] = ???

  def map3[A, B, C, Z](fa: F[A], fb: F[B], fc: F[C])(f: (A, B, C) => Z): F[Z] =
    ???

  def map4[A, B, C, D, Z](fa: F[A], fb: F[B], fc: F[C], fd: F[D])(
      f: (A, B, C, D) => Z): F[Z] = ???

  def tuple2[A, B](fa: F[A], fb: F[B]): F[(A, B)] = ???

  def tuple3[A, B, C](fa: F[A], fb: F[B], fc: F[C]): F[(A, B, C)] = ???

  def flip[A, B](ff: F[A => B]): F[A] => F[B] = ???

  def compose[G[_]](
      implicit G: Applicative[G]): Applicative[Lambda[X => F[G[X]]]] =
    new Applicative[Lambda[X => F[G[X]]]] {
      def pure[A](a: A): F[G[A]] = ???

      def apply[A, B](fga: F[G[A]])(ff: F[G[A => B]]): F[G[B]] = ???
    }
}
