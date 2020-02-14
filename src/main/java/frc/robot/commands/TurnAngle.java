/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class TurnAngle extends Command {
  public double turnAngle = 0.0;
  public double currentAngle = Robot.m_drivetrain.getAngle();

  private double leftDriveSpeed = .70;
  private double rightDriveSpeed = .715;

  private double driveDistance = 0.0;
  private double encoderDifference = 0.0;
  private double leftCorrectionRatio = 1.0;
  private double rightCorrectionRatio = 1.0;

  public TurnAngle(double angle) {
    requires(Robot.m_drivetrain);
    turnAngle = angle;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    currentAngle = Robot.m_drivetrain.getAngle();
    if(turnAngle > 0){
      Robot.m_drivetrain.drive(-leftDriveSpeed *leftCorrectionRatio, rightDriveSpeed * rightCorrectionRatio);
    } else {
      Robot.m_drivetrain.drive(leftDriveSpeed *leftCorrectionRatio, -rightDriveSpeed * rightCorrectionRatio);
    }
    leftCorrectionRatio = 1 - Math.abs(currentAngle/turnAngle);
    rightCorrectionRatio = 1 - Math.abs(currentAngle/turnAngle);
    if(leftCorrectionRatio < .5){
      leftCorrectionRatio = 0.5;
    }
    if(rightCorrectionRatio < 0.5){
      rightCorrectionRatio = 0.5;
    }
    SmartDashboard.putNumber("Left Correct", leftCorrectionRatio);
    SmartDashboard.putNumber("Right Correct", rightCorrectionRatio);
    Robot.m_drivetrain.log();
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    if(turnAngle > 0){
      return currentAngle >= turnAngle;
    } else {
      return currentAngle <= turnAngle;
    }
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
