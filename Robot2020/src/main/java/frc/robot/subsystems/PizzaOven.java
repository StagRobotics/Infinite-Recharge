/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Talon;
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
  private boolean chuteOn = false;

  // Initialize Static Variables
  private final double INTAKESPEED = 1.0;
  private final double CHUTESPEED = 1.0;

  // Initialize Motor Controllers

  private Talon intakeMotor = new Talon(RobotMap.intakeMotor);
  private Talon chuteMotor = new Talon(RobotMap.chuteMotor);
  
  private Relay dumpMotor = new Relay(RobotMap.dumpMotor);
  private Relay camMotor = new Relay(RobotMap.camMotor);

  private DigitalInput dumpLimit = new DigitalInput(RobotMap.dumpLimit);

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

  public void toggleChute(){
    if (chuteOn == false){
      chuteForward();
      chuteOn = true;
    }else{
      chuteOff();
      chuteOn = false;
    }
  }

  public boolean getDumpLimit(){
    return dumpLimit.get();
  }

  public void intakeForward() {
    intakeMotor.set(INTAKESPEED);
  }

  public void intakeBackward() {
    intakeMotor.set(-INTAKESPEED);
  }

  public void intakeOff(){
    intakeMotor.set(0);
  }

  public void chuteForward(){
    chuteMotor.set(CHUTESPEED);
  }

  public void chuteBackward(){
    chuteMotor.set(-CHUTESPEED);
  }

  public void chuteOff(){
    chuteMotor.set(0);
  }
  
  public void forwardDump(){
    dumpMotor.set(Relay.Value.kForward);
  }

  public void reverseDump(){
    dumpMotor.set(Relay.Value.kReverse);
  }

  public void offDump(){
    dumpMotor.set(Relay.Value.kOff);
  }

  public void forwardCam(){
    camMotor.set(Relay.Value.kForward);
  }

  public void offCam(){
    camMotor.set(Relay.Value.kOff);
  }




}
