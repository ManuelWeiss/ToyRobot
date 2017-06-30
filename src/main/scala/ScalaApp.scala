import io.weiss.toyrobot._

object ScalaApp {

  def main(args: Array[String]): Unit = {
    println(welcomeMessage)

    val newSimulation = new RobotSimulation
    newSimulation.gameLoop()
  }

  val welcomeMessage =
    """
      |Welcome to the toy robot simulation!
      |
      |    .---.     Type one of the following commands (case insensitive):
      |  .'_:___".     PLACE x,y,f (where x and y are 0-4, f is one of NORTH, SOUTH, EAST, WEST)
      |  |__ --==|     MOVE
      |  [  ]  :[|     LEFT
      |  |__| I=[|     RIGHT
      |  / / ____|     REPORT
      | |-/.____.'
      |/___\ /___\   Hit CTRL-D to exit.
    """.stripMargin
}
