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


public class getBallAuto extends Command {

  private double leftDriveSpeed = .400;
  private double rightDriveSpeed = .415;

  private double driveDistance = 0;
  private double encoderDifference = 0.0;
  private double leftCorrectionRatio = 1.0;
  private double rightCorrectionRatio = 1.0;
  

  public getBallAuto(int distance) {
    requires(Robot.m_drivetrain);
    driveDistance = distance;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    double leftSpeed = -leftDriveSpeed *leftCorrectionRatio;
    double rightSpeed = -rightDriveSpeed * rightCorrectionRatio;
    if(leftSpeed > 0 && leftSpeed < 0.35){
      leftSpeed = 0.35;
    } else if(leftSpeed < 0 && leftSpeed > -0.35){
      leftSpeed = -0.35;
    }
    if(rightSpeed > 0 && rightSpeed < 0.35){
      rightSpeed = 0.35;
    } else if(rightSpeed < 0  && rightSpeed > -0.35){
      rightSpeed = -0.35;
    }
    Robot.m_drivetrain.drive(leftSpeed, rightSpeed);
    if(Robot.m_drivetrain.getBallX() < 0){
      leftCorrectionRatio = 1.0;
      rightCorrectionRatio = 1.0 +  0.3 * Math.abs(Robot.m_drivetrain.getBallX() / 10);
    } else if (Robot.m_drivetrain.getBallX() > 0){
      leftCorrectionRatio = 1.0 + 0.3 * Math.abs(Robot.m_drivetrain.getBallX() / 10);
      rightCorrectionRatio = 1.0;
    } else {
      leftCorrectionRatio = 1.0;
      rightCorrectionRatio = 1.0;
    }
    SmartDashboard.putNumber("Left Correct", leftCorrectionRatio);
    SmartDashboard.putNumber("Right Correct", rightCorrectionRatio);
    Robot.m_drivetrain.log();
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return Robot.m_drivetrain.getDistance() >= driveDistance;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    leftDriveSpeed = 0;
    rightDriveSpeed = 0;
    Robot.m_drivetrain.drive(0.0,0.0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
