package exercises

import Parkable._
import Parking._

//Определите функцию contramap
//определите необходимые вам инстансы для Parkable (исходите из типов)
//определить с их помощью Parkable для NormalCar, так, чтобы вывелись сообщения из примеров

//2 балла

trait Parkable[A] { self =>

  def park(value: A): String

  def contramap[B](func: B => A): Parkable[B] = ???
}

object Parkable {
  implicit class ParableOps[A](val a: A) extends AnyVal {
    def park(implicit P: Parkable[A]): String = P.park(a)
  }
}

final case class NormalCar(brand: String, number: Int)

object Parking {
  val parkByBrand: Parkable[String] =
    new Parkable[String] {
      def park(value: String): String =
        "Park a car brand " + value
    }

  val parkByNumber: Parkable[Int] =
    new Parkable[Int] {
      def park(value: Int): String =
        "Park a car with # " + value
    }

}

object Parks extends App {
  println(NormalCar("VAZ", 777).park(parkByBrand.contramap((c: NormalCar) => c.brand)))
  //Park a car brand VAZ

//  println(NormalCar("VAZ", 777).park(parkByNumber.contramap(???)))
  //Park a car with # 777
}
