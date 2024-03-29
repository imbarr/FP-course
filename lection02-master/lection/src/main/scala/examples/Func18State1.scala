package examples

object Func18State1 {
import RNG._

  trait RNG {
    def nextInt: (Int, RNG)
  }

  object RNG {

    case class SimpleRNG(seed: Long) extends RNG {
      def nextInt: (Int, RNG) = {
        val newSeed = (seed * 0x5DEECE66DL + 0xBL) & 0xFFFFFFFFFFFFL
        val nextRNG = SimpleRNG(newSeed)
        val n = (newSeed >>> 16).toInt
        (n, nextRNG)
      }
    }
  }

  val rng = SimpleRNG(42)

  val (n1, rng2) = rng.nextInt
//  (16159453,SimpleRNG(1059025964525))
  val (n2, rng3) = rng2.nextInt
//  (-1281479697,SimpleRNG(197491923327988))

  val x = (n1, rng2)
  val y = rng.nextInt

  x == y
//  yeeeah!

}
