package exercises

object DerevoADT {

  trait Derevo[A]

  object Derevo {
    def vetka[A](lft: Derevo[A], rgt: Derevo[A]): Derevo[A] = ???
    def listok[A](a: A): Derevo[A] = ???
  }

  def size[A](t: Derevo[A]) = ???

  def maximum(t: Derevo[Int]) = ???

  def map[A, B](t: Derevo[A])(f: A => B) = ???

  def fold[A, B](t: Derevo[A])(f: A => B)(g: (B, B) => B) = ???

  def depth[A](t: Derevo[A]) = ???


  def depthF[A](t: Derevo[A]) = ???

  def sizeF[A](t: Derevo[A]) = ???

  def maximumF(t: Derevo[Int]) = ???

  def mapF[A, B](t: Derevo[A])(f: A => B) = ???

}