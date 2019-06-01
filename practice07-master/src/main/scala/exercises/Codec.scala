package exercises

import Codec._

//  Аналогично contramap определите функцию imap
//с его помощью определите Codec для класса Box
//
//3 балла


trait Codec[A] { self =>

  def encode(value: A): String

  def decode(value: String): A

  def imap[B](dec: A => B, enc: B => A): Codec[B] = ???
}

object Codec {

  implicit class StringSyntax(val s: String) extends AnyVal {
    def decode[A](implicit C: Codec[A]): A = C.decode(s)
  }

  implicit class CodecSyntax[A](val a: A) extends AnyVal {
    def encode(implicit c: Codec[A]): String = c.encode(a)
  }

  implicit val stringCodec: Codec[String] =
    new Codec[String] {
      def encode(value: String): String = value
      def decode(value: String): String = value
    }

  implicit val intCodec: Codec[Int] =
    stringCodec.imap(_.toInt, _.toString)

  implicit val booleanCodec: Codec[Boolean] =
    stringCodec.imap(_.toBoolean, _.toString)

}

case class Box[A](value: A)

object Box {

  implicit def boxCodec[A](implicit C: Codec[A]): Codec[Box[A]] = ???

}


object Codecs extends App {

  println(Box(1).encode)
  // 1

  println("12346".decode[Box[Int]])
  // Box(123456)

}
