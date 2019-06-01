package exercises

import examples.States._

//  Написать крестики-нолики
//  На вход список клетка - игрок
//  По списку проходим последовательно
//  Если клетка, на которую пытаемся сходить - занята,
//  ничего не происходит, ход сгорает, идём дальше
//  Пустой инпут - ничья
//  Вывести рез-т игры для заданных входных данных
//  while, return и var запрещены
//  try catch не использовать
//  5 баллов

object States {

  sealed trait Player

  case object X extends Player

  case object O extends Player

  sealed trait Cell

  case object AA extends Cell

  case object AB extends Cell

  case object AC extends Cell

  case object BA extends Cell

  case object BB extends Cell

  case object BC extends Cell

  case object CA extends Cell

  case object CB extends Cell

  case object CC extends Cell

  case class Game(cells: Map[Cell, Player] = Map())

  type GameState = State[Game, (Cell, Player)]

  val winMsg = (plr: Player) => s"Der Spieler $plr hat gewonnen"
  val standOffMsg = "Unentschieden"

  def play(input: List[(Cell, Player)]): GameState = {
    if (input.isEmpty) println("Empty!!")
    //for { i <- input } print("("+i._1+", "+i._2+"); ")
    //println("End")
    //val res = input.foldLeft(Map(): Map[Cell, Player]) { (acc: Map[Cell, Player], i: (Cell, Player)) => if (acc.contains(i._1)) acc else acc + i }
    if (input.isEmpty) State(s => ((null, null), s))
    else State(s => {
      val res = input.foldLeft(s.cells) { (acc: Map[Cell, Player], i: (Cell, Player)) => if (acc.contains(i._1)) acc else acc + i }
      if (result(Game(res)) == standOffMsg)
        {
          for { i <- input } print("("+i._1+", "+i._2+"); ")
          println("End")
        }
      (input.head, Game(res))
      //if (input.size > 1)
      //  {
      //    val (t, tt) = play(input.dropRight(1)).run(s)
      //    if (tt.cells.contains(input.last._1)) (input.last, tt) else (input.last, Game(tt.cells + input.last))
      //  }
     // else
      //  if (s.cells.contains(input.last._1)) (input.last, s) else (input.last, Game(s.cells + input.last))
    })
  }

  def result(g: Game): String = {
    if (g.cells.get(AA).contains(X) && g.cells.get(AB).contains(X) && g.cells.get(AC).contains(X) ||
          g.cells.get(BA).contains(X) && g.cells.get(BB).contains(X) && g.cells.get(BC).contains(X) ||
          g.cells.get(CA).contains(X) && g.cells.get(CB).contains(X) && g.cells.get(CC).contains(X) ||
          g.cells.get(AA).contains(X) && g.cells.get(BB).contains(X) && g.cells.get(CC).contains(X) ||
          g.cells.get(AC).contains(X) && g.cells.get(BB).contains(X) && g.cells.get(CA).contains(X) ||
          g.cells.get(AA).contains(X) && g.cells.get(BA).contains(X) && g.cells.get(CA).contains(X) ||
          g.cells.get(AB).contains(X) && g.cells.get(BB).contains(X) && g.cells.get(CB).contains(X) ||
          g.cells.get(AC).contains(X) && g.cells.get(BC).contains(X) && g.cells.get(CC).contains(X))
          winMsg(X)
    else {
      if (g.cells.get(AA).contains(O) && g.cells.get(AB).contains(O) && g.cells.get(AC).contains(O) ||
        g.cells.get(BA).contains(O) && g.cells.get(BB).contains(O) && g.cells.get(BC).contains(O) ||
        g.cells.get(CA).contains(O) && g.cells.get(CB).contains(O) && g.cells.get(CC).contains(O) ||
        g.cells.get(AA).contains(O) && g.cells.get(BB).contains(O) && g.cells.get(CC).contains(O) ||
        g.cells.get(AC).contains(O) && g.cells.get(BB).contains(O) && g.cells.get(CA).contains(O) ||
        g.cells.get(AA).contains(O) && g.cells.get(BA).contains(O) && g.cells.get(CA).contains(O) ||
        g.cells.get(AB).contains(O) && g.cells.get(BB).contains(O) && g.cells.get(CB).contains(O) ||
        g.cells.get(AC).contains(O) && g.cells.get(BC).contains(O) && g.cells.get(CC).contains(O))
        winMsg(O)
      else standOffMsg
    }
  }

  def main(args: Array[String]): Unit = {
    val game = Game()

    val p = List((AA, O), (CB, X), (BB, O), (AC, X), (BB, O), (CA, X), (CC, O))

    val (_, gm) = play(p).run(game)

    println(result(gm) == winMsg(O))
    //for { i <- gm.cells } println(i._1 == BB)

    val p2 = List()

    val (_, gm2) = play(p2).run(game)

    println(result(gm2) == standOffMsg)

    val cel = (AA, X)
    val game2 = Game(gm.cells)

    //val p3 = List((AA, O), (BB, X), (BB, O), (CC, X), (CC, O))
    val p3 = List()

    val (_, gm3) = play(p3).run(game2)

    println(result(gm3) == standOffMsg)
  }


}