package io.weiss.toyrobot

class ToyRobot {
  private var x = 0
  private var y = 0
  private var f: Facing = North

  def move(): Unit = {
    val (newX, newY) = f match {
      case North => (x, y + 1)
      case South => (x, y - 1)
      case East  => (x + 1, y)
      case West => (x - 1, y)
    }

    if (isValidPosition(newX, newY)) {
      x = newX
      y = newY
    }
  }

  def place(newX: Int, newY: Int, newF: Facing): Unit =
    if (isValidPosition(newX, newY)) {
      x = newX
      y = newY
      f = newF
    }

  def left(): Unit =
    f = f match {
      case North => West
      case South => East
      case East  => North
      case West => South
    }

  def right(): Unit =
    f = f match {
      case North => East
      case South => West
      case East  => South
      case West => North
    }

  def report(): (Int, Int, Facing) = (x, y, f)

  private def isValidPosition(x: Int, y: Int) =
    x >= 0 && x < 5 &&
    y >= 0 && y < 5
}

sealed trait Facing
case object North extends Facing
case object South extends Facing
case object East extends Facing
case object West extends Facing
