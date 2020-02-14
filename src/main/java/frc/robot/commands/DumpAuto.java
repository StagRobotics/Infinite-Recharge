/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class DumpAuto extends Command {

  private Timer timer = new Timer();
  public DumpAuto() {
    requires(Robot.m_PizzaOven);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    timer.start();
    Robot.m_PizzaOven.toggleDump();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return timer.get() >= 1;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.m_PizzaOven.toggleDump();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
