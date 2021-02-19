/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;


import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Spark;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

/**
 * Add your docs here.
 */
public class ClimbAndBalance extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  //Initialize Static Variables
  private double WINCHSPEED = 1.0;
  private double BALANCERSPEED = 1.0;
  //Initalize Motor Controllers
  private Spark winchMotor = new Spark(RobotMap.winchMotor);
  private Spark balancer = new Spark(RobotMap.balancerMotor);
  private DigitalInput climbLimit = new DigitalInput(RobotMap.climbLimit);


  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }

 
  


  public void winchForward(){
    winchMotor.set(WINCHSPEED);
  }

  public void winchBackward(){
    if (climbLimit.get() == true){
      winchMotor.set(-WINCHSPEED);
    }
    
  }

  public void winchOff(){
    winchMotor.set(0);
  }

  public void balancerForward(){
    balancer.set(BALANCERSPEED);
  }

  public void balancerBackward(){
    balancer.set(-BALANCERSPEED);
  }

  public void balancerOff(){
    balancer.set(0);
  }


}
