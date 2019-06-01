package exercises

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

//для ParList
//0) реализуйте split
//1) реализуйте paralMap
//2) реализуйте через split paralFilter
//3) реализуйте через split paralFold

object Parallel {

  implicit class ParList[A](fl: Future[List[A]]) {

    private def split[B](l: List[B]): (List[B], List[B]) = ???

    def paralMap[B](f: A => B): Future[List[B]] = ???

    def paralFilter(f: A => Boolean): Future[List[A]] = ???

    def paralFold(init: A)(f: (A, A) => A): Future[A] = ???
  }
}
