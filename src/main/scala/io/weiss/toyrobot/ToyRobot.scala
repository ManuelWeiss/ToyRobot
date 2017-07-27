package io.weiss.toyrobot

import scala.annotation.tailrec
import scala.collection.mutable
import scala.util.Random

class ToyRobot {
  private var x = 0
  private var y = 0
  private var f: Facing = North
  private val potHoles = mutable.Set[(Int, Int)]()

  def addPothole(x: Int, y: Int): Unit =
    if (isValidPosition(x, y))
      potHoles.add((x, y))

  def removePothole(x: Int, y: Int): Unit = potHoles.remove((x, y))

  private def calculateStep(x: Int, newX: Int) = (newX - x) / (newX - x).abs

  def goTo(newX: Int, newY: Int): List[(Int, Int)] = {
    go(newX, newY, List())
  }

  @tailrec
  private def go(newX: Int, newY: Int, acc: List[(Int, Int)]): List[(Int, Int)] = {
    if (isValidPosition(newX, newY)) {
      if (newX != x && !potHoles.contains(x + calculateStep(x, newX), y)) {
        x += calculateStep(x, newX)
        go(newX, newY, (x, y) :: acc)
      } else if (newY != y && !potHoles.contains((x, y + calculateStep(y, newY)))) {
        y += calculateStep(y, newY)
        go(newX, newY, (x, y) :: acc)
      } else
        acc.reverse
    }
    else
      acc.reverse
  }

  def goToWithBackTracking(newX: Int, newY: Int): List[(Int, Int)] = {
     goWithBackTracking(newX, newY, List())
  }

  @tailrec
  private def goWithBackTracking(newX: Int, newY: Int, acc: List[(Int, Int)]): List[(Int, Int)] = {
    if (x == newX && y == newY)
      acc.reverse
    else if (isValidPosition(x + 1, y) && !acc.contains((x + 1, y))){
      x += 1
      goWithBackTracking(newX, newY, (x, y) :: acc)
    } else if (isValidPosition(x - 1, y) && !acc.contains((x - 1, y))){
      x -= 1
      goWithBackTracking(newX, newY, (x, y) :: acc)
    } else if (isValidPosition(x, y + 1) && !acc.contains((x, y + 1))){
      y += 1
      goWithBackTracking(newX, newY, (x, y) :: acc)
    } else if (isValidPosition(x, y - 1) && !acc.contains((x, y - 1))) {
      y -= 1
      goWithBackTracking(newX, newY, (x, y) :: acc)
    } else
      List()
  }


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
    y >= 0 && y < 5 && !potHoles.contains((x, y))
}

sealed trait Facing
case object North extends Facing
case object South extends Facing
case object East extends Facing
case object West extends Facing
