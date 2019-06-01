package exercises


import examples.{Console, IO, Program, Value}

import scala.annotation.tailrec
import scala.util.Try

//Необходимо написать программу, печатает приветственное сообщение, спрашивает число.
//Считывает число N с консоли, и вычисляет тейлрекурсивно считает и возвращает N-ое число Фибоначчи
//результат печатается в консоль, пользователя спрашиваю хочет ли он снова вычислить N-ое число Фибоначчи,
//если Y, то продолжаем, если N, то выполнение останавливается
//

//8 баллов
object ConsoleApp {

  def putStrLn[F[_]: Console](line: String): F[Unit] = Console[F].putStrLn(line)
  def getStrLn[F[_]: Console]: F[String] = Console[F].getStrLn
  def finish[F[_], A](a: => A)(implicit F: Program[F]): F[A] = F.finish(a)
  def parseInt(s: String): Option[Int] = Try(s.toInt).toOption


  @tailrec
  def fibonacciNth[F[_]: Value](n: String): F[Int] = {

     //dummy
    fibonacciNth("0")
  }

  //рендерим рез-т
  def printResults[F[_]: Console](num: Int, name: String): F[Unit] = ???

  implicit class ProgramSyntax[F[_], A](fa: F[A]) {
    def map[B](f: A => B)(implicit F: Program[F]): F[B] = F.map(fa, f)
    def flatMap[B](afb: A => F[B])(implicit F: Program[F]): F[B] = F.chain(fa, afb)
  }

  //метод проверяет необходимо ли продолжать вычисления
  def checkContinue[F[_]: Program: Console](name: String): F[Boolean] = ???

  //цикл вычисляющий число Фибоначчи
  def fibonacciLoop[F[_]: Program: Console: Value](name: String): F[Unit] = ???

  //основная программа
  def main[F[_]: Program: Console: Value]: F[Unit] = ???

  def mainIO: IO[Unit] = main[IO]


  def main(args: Array[String]): Unit = {
    mainIO.run()
  }

}
