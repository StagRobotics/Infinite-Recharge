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

  // Initialize Motor Controllers

  private Talon intakeMotor = new Talon(RobotMap.intakeMotor);

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }

  public void intakeForward() {
    intakeMotor.set(INTAKESPEED);
  }

  public void intakeBackwards() {
    intakeMotor.set(-INTAKESPEED);
  }
}
