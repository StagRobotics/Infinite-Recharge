/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.RobotController;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class TurnAngle extends Command {
  public double turnAngle = 0.0;
  public double currentAngle = Robot.m_drivetrain.getAngle();

  private double leftDriveSpeed = .30;
  private double rightDriveSpeed = .30;

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
    if(turnAngle == 0){
      leftDriveSpeed = .15;
      rightDriveSpeed = .15;
    }
    leftDriveSpeed = leftDriveSpeed + ((13-RobotController.getBatteryVoltage())/12);
    rightDriveSpeed = rightDriveSpeed + ((13-RobotController.getBatteryVoltage())/12);
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    currentAngle = Robot.m_drivetrain.getAngle();
    
    double leftSpeed = leftDriveSpeed;
    //*leftCorrectionRatio;
    double rightSpeed = rightDriveSpeed;
    //* rightCorrectionRatio;
    /*if(leftSpeed > 0 && leftSpeed < 0.20){
      leftSpeed = 0.20;
    } else if(leftSpeed < 0 && leftSpeed > -0.35){
      leftSpeed = -0.35;
    }
    if(rightSpeed > 0 && rightSpeed < 0.35){
      rightSpeed = 0.35;
    } else if(rightSpeed < 0  && rightSpeed > -0.35){
      rightSpeed = -0.35;
    }*/
    
    SmartDashboard.putNumber("Left Speed turning", leftSpeed);
    SmartDashboard.putNumber("Right Speed turning", rightSpeed);
    if(turnAngle > 0){
      Robot.m_drivetrain.drive(-leftSpeed, rightSpeed, false);
    } else if(turnAngle < 0) {
      Robot.m_drivetrain.drive(leftSpeed, -rightSpeed, false);
    } else {
      if(currentAngle > 0) {
        Robot.m_drivetrain.drive(leftSpeed, -rightSpeed, false);
      } else if (currentAngle < 0) {
        Robot.m_drivetrain.drive(-leftSpeed, rightSpeed, false);
      }
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
    } else if (turnAngle < 0) {
      return currentAngle <= turnAngle;
    } else {
      return currentAngle <= turnAngle+0.05 && currentAngle >= turnAngle-0.05;
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
