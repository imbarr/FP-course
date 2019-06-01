package exercises
//  while, return и var запрещены
//  try catch не использовать
//  1 балл

object Poly {

  type Set[T] = T => Boolean

  def contains[T](s: Set[T], elem: T): Boolean = s(elem)

  def singletonSet[T](elem: T): Set[T] = {
      case `elem` => true
      case _ => false
  }

  def union[T](s: Set[T], t: Set[T]): Set[T] = {
      case x if s(x) || t(x) => true
      case _ => false
  }

  def intersect[T](s: Set[T], t: Set[T]): Set[T] = {
      case x if s(x) && t(x) => true
      case _ => false
  }

  def diff[T](s: Set[T], t: Set[T]): Set[T] = {
      case x if s(x) && !t(x) => true
      case _ => false
  }

  def filter[T](s: Set[T], p: T => Boolean): Set[T] = {
      case x if s(x) && p(x) => true
      case _ => false
  }

}