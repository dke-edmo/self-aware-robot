// File:          HingeJointController.java
// Date:
// Description:
// Author:
// Modifications:

import com.cyberbotics.webots.controller.Motor;
import com.cyberbotics.webots.controller.Robot;
import com.cyberbotics.webots.controller.Node;
import com.cyberbotics.webots.controller.Field;
import com.cyberbotics.webots.controller.Supervisor;
import com.cyberbotics.webots.controller.Brake;
import com.cyberbotics.webots.controller.PositionSensor;

public class HingeJointController {

  public static void main(String[] args) {

    // create the Robot instance.
    Supervisor robot = new Supervisor();
    Node robotNode = robot.getSelf();
    Field children = robotNode.getField("children");
    
    Node hingeJoint = null;
    int count = 0;
    while(children.getMFNode(count) != null){
      if(children.getMFNode(count).getTypeName().equals("HingeJoint")){
        hingeJoint = children.getMFNode(count);
        }
      count ++;
    }
    
    Field hingeJointChildren = hingeJoint.getField("device");
    Node rotM = hingeJointChildren.getMFNode(0);
    Motor r = robot.getMotor("rotational motor");
    Brake brake = robot.getBrake("brake");
    
    System.out.println("child = " + brake.getName());
    PositionSensor ps = robot.getPositionSensor("position sensor");
    ps.enable(10);
    
    int timeStep = 64; //(int) Math.round(robot.getBasicTimeStep());

    while (robot.step(timeStep) != -1) {
      
      /*r.setPosition(2);
      r.setVelocity(10.0);
      if(ps.getValue() >= 2)
      {
        brake.setDampingConstant(Double.POSITIVE_INFINITY);
      }*/
      setPositionBrake(r, ps, brake);

      System.out.println("Position sensor = " + ps.getValue());

    };

  }
  public static void setPositionBrake(Motor r, PositionSensor ps, Brake brake){
    r.setPosition(2);
    r.setVelocity(10.0);
    if(ps.getValue() >= 2) {
      brake.setDampingConstant(Double.POSITIVE_INFINITY);
    }
  }
}
