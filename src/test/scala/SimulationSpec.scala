import java.io.{ByteArrayOutputStream, StringReader}

import io.weiss.toyrobot.RobotSimulation
import org.scalatest._

class SimulationSpec extends FlatSpec with Matchers {

  "The Simulation" should "accept valid commands" in {

    val commands = Seq("PLACE 2,2,WEST", "MOVE", "RIGHT", "MOVE", "REPORT")

    val input = new StringReader(commands.mkString("\n"))
    val outCapture = new ByteArrayOutputStream

    Console.withIn(input) {
      Console.withOut(outCapture) {
        val newSimulation = new RobotSimulation
        newSimulation.gameLoop()
      }
    }

    outCapture.toString shouldBe "1,3,NORTH\n"
  }
}
