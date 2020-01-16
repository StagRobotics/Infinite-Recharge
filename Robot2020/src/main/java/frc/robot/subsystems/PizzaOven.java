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
public class PizzaOven extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  // Initialize Static Variables

  private final double INTAKESPEED = 1.0;
  private final double CHUTESPEED = 1.0;
  private final double SHOOTSPEED = 1.0;
  // Initialize Motor Controllers

  private Talon intakeMotor = new Talon(RobotMap.intakeMotor);
  private Talon chuteMotor = new Talon(RobotMap.chuteMotor);
  private Talon shootMotor = new Talon(RobotMap.shootMotor);

  public PizzaOven() {
    super();
    System.out.println("Shoot");
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }

  public void intakeForward() {
    intakeMotor.set(INTAKESPEED);
  }

  public void intakeBackward() {
    intakeMotor.set(-INTAKESPEED);
  }

  public void chuteForward(){
    chuteMotor.set(CHUTESPEED)
  }

  public void chuteBackward(){
    chuteMotor.set(-CHUTESPEED);
  }

  public void shootForward(){
    shootMotor.set(SHOOTSPEED);
  }

  public void shootBackward(){
    shootMotor.set(-SHOOTSPEED);
  }
  
}
