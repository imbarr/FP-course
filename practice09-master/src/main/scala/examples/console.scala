package examples

trait Program[F[_]] {
  def finish[A](a: => A): F[A]

  def chain[A, B](fa: F[A], afb: A => F[B]): F[B]

  def map[A, B](fa: F[A], ab: A => B): F[B]
}

object Program {
  def apply[F[_]](implicit F: Program[F]): Program[F] = F
}

trait Console[F[_]] {
  def putStrLn(line: String): F[Unit]
  def getStrLn: F[String]
}

object Console {
  def apply[F[_]](implicit F: Console[F]): Console[F] = F

  case class IO[A](unsafeRun: () => A) { self =>
    def map[B](f: A => B): IO[B] = IO(() => f(self.unsafeRun()))

    def flatMap[B](f: A => IO[B]): IO[B] = IO(() => f(self.unsafeRun()).unsafeRun())
  }
}

trait Value[F[_]] {
  def value(i: Int): F[Int]
}

object Value {
  def apply[F[_]](implicit F: Value[F]): Value[F] = F
}

case class IO[A](run: () => A) { self =>
  def map[B](f: A => B): IO[B] = IO(() => f(self.run()))

  def flatMap[B](f: A => IO[B]): IO[B] = IO(() => f(self.run()).run())
}

object IO {
  def point[A](a: => A): IO[A] = IO(() => a)

  implicit val ProgramIO = new Program[IO] {
    def finish[A](a: => A): IO[A] = ???

    def chain[A, B](fa: IO[A], afb: A => IO[B]): IO[B] = ???

    def map[A, B](fa: IO[A], ab: A => B): IO[B] = ???
  }

  implicit val ConsoleIO = new Console[IO] {
    def putStrLn(line: String): IO[Unit] = ???

    def getStrLn: IO[String] = ???
  }

  implicit val ValueIO = new Value[IO] {
    def value(i: Int): IO[Int] = ???
  }
}