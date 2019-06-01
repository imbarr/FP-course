package exercises

import examples.Streaming._


//2 балла
object StreamingCombi {

  def take[I](n: Int): Process[I,I] = ???

  def drop[I](n: Int): Process[I,I] = ???

  def takeWhile[I](f: I => Boolean): Process[I,I] = ???

  def dropWhile[I](f: I => Boolean): Process[I,I] = ???

}
