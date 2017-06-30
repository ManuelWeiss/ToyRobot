package io.weiss.toyrobot

import scala.io.StdIn

class RobotSimulation {
  private val robot = new ToyRobot
  private val placeCommandRegEx = raw"PLACE (\d),(\d),(NORTH|SOUTH|EAST|WEST)".r

  def gameLoop(): Unit = {
    var ok = true
    while (ok) {
      val input = Option(StdIn.readLine())
      if (input.isEmpty)
        ok = false
      else
        input.map(_.trim.toUpperCase).filter(_.nonEmpty).foreach(executeCommand)
    }
  }

  private def executeCommand(input: String): Unit = {
    input match {
      case "REPORT" => println(robot.report().toString().stripPrefix("(").stripSuffix(")").toUpperCase)
      case "MOVE" => robot.move()
      case "LEFT" => robot.left()
      case "RIGHT" => robot.right()
      case placeCommandRegEx(x, y, f) => robot.place(x.toInt, y.toInt, mkFacing(f))
      case _ => println("invalid command")
    }
  }

  private def mkFacing(f: String) = f match {
    case "NORTH" => North
    case "SOUTH" => South
    case "EAST" => East
    case "WEST" => West
    case _ => North // guaranteed not to happen by RegEx
  }

}
