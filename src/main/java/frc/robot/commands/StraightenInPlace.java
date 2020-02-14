/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class StraightenInPlace extends Command {
  private double leftDriveSpeed = .45;
  private double rightDriveSpeed = -.465;

  private double driveDistance = 0.0;
  private double encoderDifference = 0.0;
  private double leftCorrectionRatio = 1.0;
  private double rightCorrectionRatio = 1.0;
  private double lastAngle = 0.0;
  public StraightenInPlace() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.m_drivetrain);
    
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    lastAngle = Robot.m_drivetrain.getAngle();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    Robot.m_drivetrain.drive(leftDriveSpeed * leftCorrectionRatio, rightDriveSpeed * rightCorrectionRatio);
    if(Robot.m_drivetrain.getAngle() > 0 && lastAngle < 0){
      rightDriveSpeed = -rightDriveSpeed;
      leftDriveSpeed = Math.abs(leftDriveSpeed); 
      lastAngle = Robot.m_drivetrain.getAngle();
    } else  if (Robot.m_drivetrain.getAngle() < 0 && lastAngle > 0){
      
      leftDriveSpeed = -leftDriveSpeed;
      rightDriveSpeed = Math.abs(rightDriveSpeed); 
      lastAngle = Robot.m_drivetrain.getAngle();   
    }
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return Robot.m_drivetrain.getAngle() < 1 && Robot.m_drivetrain.getAngle() > -1;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.m_drivetrain.drive(0, 0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
