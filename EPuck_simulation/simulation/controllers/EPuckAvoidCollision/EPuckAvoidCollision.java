import com.cyberbotics.webots.controller.DistanceSensor;
import com.cyberbotics.webots.controller.Motor;
import com.cyberbotics.webots.controller.Robot;

// Here is the main class of your controller.
// This class defines how to initialize and how to run your controller.
public class EPuckAvoidCollision {

  public static void main(String[] args) {

    // create the Robot instance.
    Robot robot = new Robot();

    // get the time step of the current world.
    int TIME_STEP = 64;
    double MAX_SPEED = 6.28;


    // initialize devices

    DistanceSensor[] ps = new DistanceSensor[8];
    String[] psNames = {
      "ps0", "ps1", "ps2", "ps3", 
      "ps4", "ps5", "ps6", "ps7"};
     
    for(int i = 0; i<8; i++){
      ps[i] = robot.getDistanceSensor(psNames[i]);
      ps[i].enable(TIME_STEP);
    }
    
    // initialize motors
    Motor leftMotor = robot.getMotor("left wheel motor");
    Motor rightMotor = robot.getMotor("right wheel motor");
    leftMotor.setPosition(Double.POSITIVE_INFINITY);
    rightMotor.setPosition(Double.POSITIVE_INFINITY);
    leftMotor.setVelocity(0.0);
    rightMotor.setVelocity(0.0);
    

  



    // Main loop:
    // - perform simulation steps until Webots is stopping the controller
    while (robot.step(TIME_STEP) != -1) {
        //read sensors input
    double[] psValues = {0, 0, 0, 0, 0, 0, 0, 0};
    for(int i = 0; i<8; i++){
      psValues[i] = ps[i].getValue();
    }
      // process behavior
      // detect obstacles
     boolean right_obstacle =
     psValues[0] > 80.0 ||
     psValues[1] > 80.0 ||
     psValues[2] > 80.0;
     boolean left_obstacle =
     psValues[5] > 80.0 ||
     psValues[6] > 80.0 ||
     psValues[7] > 80.0;
     

    // initialize motor speeds at 50% of MAX_SPEED.
    double leftSpeed  = 0.5 * MAX_SPEED;
    double rightSpeed = 0.5 * MAX_SPEED;
    // modify speeds according to obstacles
    if (left_obstacle) {
      // turn right
     leftSpeed  = 0.5 * MAX_SPEED;
      rightSpeed = -0.5 * MAX_SPEED;
     }
     else if (right_obstacle) {
     // turn left
     leftSpeed  = -0.5 * MAX_SPEED;
     rightSpeed = 0.5 * MAX_SPEED;
      }
    // write actuators inputs
    leftMotor.setVelocity(leftSpeed);
    rightMotor.setVelocity(rightSpeed);
    };

    // Enter here exit cleanup code.
  }
}
