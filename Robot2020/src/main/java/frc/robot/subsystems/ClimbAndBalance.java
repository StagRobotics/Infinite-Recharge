/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

/**
 * Add your docs here.
 */
public class ClimbAndBalance extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  //Variables
  private boolean climbOn = false;
  private boolean balanceOn = false;
  //Initialize Static Variables
  private double WINCHSPEED = 1.0;
  private double BALANCERSPEED = 1.0;
  //Initalize Motor Controllers
  private Talon winchMotor = new Talon(RobotMap.winchMotor);
  private Talon balancer = new Talon(RobotMap.balancerMotor);


  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }

  public void toggleClimb(){
    if (climbOn == false){
      winchForward();
      climbOn = true;
    }else{
      winchOff();
      climbOn = false;
    }
  }

  public void toogleBalance(){
    if (balanceOn == false){
      balancerForward();
      balanceOn = true;
    }else{
      balancerOff();
      balanceOn = false;
    }
  }

  public void toggleBalance(){

  }

  private void winchForward(){
    winchMotor.set(WINCHSPEED);
  }

  private void winchBackward(){
    winchMotor.set(-WINCHSPEED);
  }

  private void winchOff(){
    winchMotor.set(0);
  }

  private void balancerForward(){
    balancer.set(BALANCERSPEED);
  }

  private void balancerBackward(){
    balancer.set(-BALANCERSPEED);
  }

  private void balancerOff(){
    balancer.set(0);
  }


}
