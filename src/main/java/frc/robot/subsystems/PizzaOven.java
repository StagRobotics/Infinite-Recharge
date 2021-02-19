/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

/**
 * Add your docs here.
 */
public class PizzaOven extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  // Variables
  private boolean onIntake = false;
  private boolean onDump = true;

  // Initialize Static Variables

  // Initialize Motor Controllers
  private DoubleSolenoid pizzaPan = new DoubleSolenoid(RobotMap.pizzaPanPort, RobotMap.pizzaPanPort2);

  private Relay intakeMotor = new Relay(RobotMap.intakeMotor);
  

  public PizzaOven() {
    super();
    System.out.println("Shoot");
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }

  public void toggleIntake(){
    if (onIntake == false){
      intakeForward();
      onIntake = true;
    }else{
      intakeOff();
      onIntake = false;
    }
  }

  public void toggleDump(){
    if (onDump == false){
      forwardDump();
      onDump = true;
    }else{
      reverseDump();
      onDump = false;
    }
  }

  public void intakeForward() {
    intakeMotor.set(Relay.Value.kForward);
  }

  public void intakeBackward() {
    intakeMotor.set(Relay.Value.kReverse);
  }

  public void intakeOff(){
    intakeMotor.set(Relay.Value.kOff);
  }
  
  public void forwardDump(){
    pizzaPan.set(DoubleSolenoid.Value.kForward);
  }

  public void reverseDump(){
    pizzaPan.set(DoubleSolenoid.Value.kReverse);
  }

  public void offDump(){
    pizzaPan.set(DoubleSolenoid.Value.kOff);
  }



}
