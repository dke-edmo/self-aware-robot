// File:          AccelerometerController.java
// Date:
// Description:
// Author:
// Modifications:

//  import com.cyberbotics.webots.controller.DistanceSensor;
import com.cyberbotics.webots.controller.Accelerometer;
import com.cyberbotics.webots.controller.Robot;

public class AccelerometerController {

  public static void main(String[] args) {

    // create the Robot instance.
    Robot robot = new Robot();

    // get the time step of the current world.
    int timeStep = (int) Math.round(robot.getBasicTimeStep());
    Accelerometer accelerometer = robot.getAccelerometer("accelerometer");
    accelerometer.enable(10);
    // Main loop:
    // - perform simulation steps until Webots is stopping the controller
    while (robot.step(timeStep) != -1) {
      // Read the sensors:
      // Enter here functions to read sensor data, like:
      //  double val = ds.getValue();
      double[] values = accelerometer.getValues();
      System.out.println("x = " + values[0] +", y = " + values[1] + "z = " + values[2]);
      // Process sensor data here.

      // Enter here functions to send actuator commands, like:
      //  motor.setPosition(10.0);
    };

    // Enter here exit cleanup code.
  }
}
