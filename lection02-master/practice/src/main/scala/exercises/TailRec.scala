package exercises

import org.apache.logging.log4j.core.tools.picocli.CommandLine.RunFirst

import scala.annotation.tailrec


object TailRec {

  //  На вход дан список чисел
  //  числа последовательно складываются
  //  необходимо найти число, которое первый раз повторно встречается в рез-те суммирования
  //  если список кончается, а число не найдено, в конец списка дописываем исходный список и продолжаем вычисления
  //  рез-том функции должно первое число, которое встречается дваджы впервые
  //  нпр дан список List(1, -1)
  //  первым значением, встретившимся дважды будет 0
  //  1 + (-1) | 0
  //  0 + 1    | 1
  //  1 + (-1) | 0

  //  должна быть использована аннотация @tailrec
  //  while и var запрещены
  //  подумайте об исключениях
  //  try catch не использовать
  //  2 балла



  @tailrec
  def secondOccurrenceIter(summa: IndexedSeq[Int], nStep: Int, mn: Int, mx: Int): Option[Int] = {
    val step = summa(summa.size-2)
    val newmin = mn+step*nStep
    val newmax = mx+step*nStep
    if ((step > 0 && newmin > mx) || (step < 0 && newmax < mn)) None
    else {
      if (nStep == 0) {
        val tt = for {i <- summa.indices if i != summa.indexOf(summa(i)) } yield summa(i)
        if (tt.nonEmpty)
          {
            //println(tt.head+"!!")
            Some(tt.head)
          }
        else secondOccurrenceIter(summa, nStep + 1, mn, mx)
      }
      else {
        val tt = for {i <- summa if summa.contains(i + step * nStep)} yield i + step * nStep
        if (tt.nonEmpty)
          {
            //println(tt.head+"!!")
            Some(tt.head)
          }
        else secondOccurrenceIter(summa, nStep + 1, mn, mx)
      }
    }
  }

  def secondOccurrence(inp: List[Int]): Option[Int] = {
    //for { i <- inp } print(i+" ")
    if (inp.size < 2)
      {
        if (inp.size == 1 && inp.head == 0)
          {
            //println("0!!")
            Some(0)
          }
        else None
        //None
      }
    else {
      val f = { x: Int => if (x != inp.size-1) inp.take(x+2).sum else inp.head + inp.sum  }
      val summa = inp.indices.map(f)
      //for { i <- summa } print(i+" ")
      secondOccurrenceIter(summa, 0, summa.min, summa.max)
    }
  }



  /*
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

  @tailrec
  def secondOccurrenceItr(inp: List[Int], s: Set[Int], cur: Int, ptr: Int): Option[Int] = {
    if (inp.size < 2) None
    else {
      val newsm = cur + inp(ptr)
      if (contains(s, newsm)) Some(newsm)
      else secondOccurrenceItr(inp, union(s, singletonSet(newsm)), newsm, (ptr + 1) % inp.size)
    }
  }


  def secondOccurrence(inp: List[Int]): Option[Int] = {
    val sm = inp.head + inp(1)
    secondOccurrenceItr(inp, singletonSet(sm), sm, 2%inp.size)
  }

  */

  def main(args: Array[String]): Unit = {
    val l = List(7, 7, -2, -7, -4)
    println(secondOccurrence(l))
    val ll = List(50, -48)
    println(secondOccurrence(ll))
    val lll = List(4, -1, -1, -1, -1)
     println(secondOccurrence(lll))
    val llll = List(0)
    println(secondOccurrence(llll))
    val lllll = List(1, 2, 3, 4, 5, -16)
    println(secondOccurrence(lllll))
  }
}