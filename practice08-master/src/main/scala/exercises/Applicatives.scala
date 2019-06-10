package exercises

object Applicatives {

  object OptionApp {

    //2. Рейлизуйте все функции аппликатива Option
    implicit val optionApp: Applicative[Option] =
      new Applicative[Option] {
        def pure[A](a: A): Option[A] = Some(a)

        def apply[A, B](fa: Option[A])(ff: Option[A => B]): Option[B] =
          for {
            a <- fa
            f <- ff
          } yield f(a)
      }
  }

  object ListApp {

    //3. Рейлизуйте все функции аппликатива List
    implicit val listApp: Applicative[List] = new Applicative[List] {
      def pure[A](a: A): List[A] = List(a)

      def apply[A, B](fa: List[A])(ff: List[A => B]): List[B] =
        for {
          a <- fa
          f <- ff
        } yield f(a)
    }
  }
}
