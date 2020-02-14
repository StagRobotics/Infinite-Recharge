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

public class DriveStraight extends Command {

  private double leftDriveSpeed = .65;
  private double rightDriveSpeed = .665;

  private double driveDistance = 0.0;
  private double encoderDifference = 0.0;
  private double leftCorrectionRatio = 1.0;
  private double rightCorrectionRatio = 1.0;

  public DriveStraight(double distance) {
    requires(Robot.m_drivetrain);
    driveDistance = distance;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    if(driveDistance > 0){
      leftDriveSpeed = -leftDriveSpeed;
      rightDriveSpeed = -rightDriveSpeed;
    }
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    SmartDashboard.putNumber("Left Motor", leftDriveSpeed * leftCorrectionRatio);
    SmartDashboard.putNumber("Right Motor", rightDriveSpeed * rightCorrectionRatio);
    SmartDashboard.putNumber("Encoder Difference", encoderDifference);
    SmartDashboard.putNumber("Left Correct", leftCorrectionRatio);
    SmartDashboard.putNumber("Right Correct", rightCorrectionRatio);
    Robot.m_drivetrain.drive(leftDriveSpeed * leftCorrectionRatio, rightDriveSpeed * rightCorrectionRatio);
    encoderDifference = Robot.m_drivetrain.getLeftEncoder() - Robot.m_drivetrain.getRightEncoder();
    if(Math.abs(Robot.m_drivetrain.getDistance()) > 2){
      if(encoderDifference < -1.0){
        leftCorrectionRatio = Robot.m_drivetrain.getRightEncoder()/Robot.m_drivetrain.getLeftEncoder();
        //leftCorrectionRatio = leftCorrectionRatio + 0.1;
        rightCorrectionRatio = 1.0;
      } else if (encoderDifference > 1.0){
        rightCorrectionRatio = Robot.m_drivetrain.getLeftEncoder()/Robot.m_drivetrain.getRightEncoder();
        //rightCorrectionRatio = rightCorrectionRatio + 0.1;
        leftCorrectionRatio = 1.0;
      } else {
        leftCorrectionRatio = 1.0;
        rightCorrectionRatio = 1.0;
      }
      SmartDashboard.putNumber("Distance", Robot.m_drivetrain.getDistance());
    }
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    //if(driveDistance > 0){
      return Math.abs(Robot.m_drivetrain.getDistance()) >= Math.abs(driveDistance);
    //} else {
      //return Robot.m_drivetrain.getDistance() <= driveDistance;
    //}
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.m_drivetrain.drive(0, 0);
    Robot.m_drivetrain.reset();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
