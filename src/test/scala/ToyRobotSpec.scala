package io.weiss.toyrobot

import org.scalatest._

class ToyRobotSpec extends FlatSpec with Matchers {

  val robot = new ToyRobot

  "The robot" should "ignore an invalid place command" in {
    robot.place(0, 0, North)
    robot.place(5, 5, South)
    robot.report() shouldBe (0, 0, North)
  }

  it should "move 1 step" in {
    robot.place(2, 2, North)
    robot.move()
    robot.report() shouldBe (2, 3, North)
  }

  it should "not fall off the table" in {
    robot.place(0, 0, South)
    robot.move()
    robot.report() shouldBe (0, 0, South)

    robot.place(0, 0, West)
    robot.move()
    robot.report() shouldBe (0, 0, West)

    robot.place(0, 4, North)
    robot.move()
    robot.report() shouldBe (0, 4, North)

    robot.place(4, 0, East)
    robot.move()
    robot.report() shouldBe (4, 0, East)
  }

  it should "do a left turn" in {
    robot.place(2, 2, North)
    robot.left()
    robot.report() shouldBe (2, 2, West)
  }

  it should "do a right turn" in {
    robot.place(2, 2, North)
    robot.right()
    robot.report() shouldBe (2, 2, East)
  }

  it should "do several turns" in {
    robot.place(2, 2, North)
    robot.left()
    robot.left()
    robot.report() shouldBe (2, 2, South)
  }

  it should "follow a set of instructions" in {
    robot.place(1, 2, East)
    robot.move()
    robot.move()
    robot.left()
    robot.move()
    robot.report() shouldBe (3, 3, North)
  }

  it should "follow a second set of instructions" in {
    robot.place(0, 0, East)
    robot.move()
    robot.left()
    robot.move()
    robot.right()
    robot.move()
    robot.left()
    robot.move()
    robot.right()
    robot.report() shouldBe (2, 2, East)
  }

  it should "allow to add a pothole" in {
    robot.addPothole(2, 2)

    robot.place(2, 1, North)

    robot.move()
    robot.report() shouldBe (2, 1, North)
  }


  it should "allow to remove a pothole" in {
    robot.addPothole(2, 2)

    robot.place(2, 1, North)

    robot.move()
    robot.report() shouldBe (2, 1, North)

    robot.removePothole(2, 2)

    robot.move()
    robot.report() shouldBe (2, 2, North)
  }

  it should "robot should find a way" in {
    robot.place(0, 0, North)
    robot.goTo(2, 2) shouldBe List((1, 0), (2, 0), (2, 1), (2, 2))
  }

  it should "robot should avoid potholes on the way" in {
    robot.place(0, 0, North)
    robot.addPothole(1, 0)
    robot.goTo(2, 2) shouldBe List((0, 1), (1, 1), (2, 1), (2, 2))
  }

  it should "robot should avoid potholes on the way - strategy 2" in {
    robot.place(0, 0, North)
    robot.addPothole(1, 0)
    robot.goToWithBackTracking(2, 2) shouldBe List((0, 1), (1, 1), (2, 1), (3, 1), (4, 1), (4, 2), (3, 2), (2, 2))
  }


}
