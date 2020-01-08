package frc.robot.subsystems;

// Import packages needed to run

import frc.robot.RobotMap;

import frc.robot.commands.*;

import edu.wpi.first.wpilibj.PWMVictorSPX;

import edu.wpi.first.wpilibj.command.Subsystem;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;



public class DriveTrain extends Subsystem {

	// Initialize Static Variables

	private final double DEADBAND = 0.05;

	// Initialize Motor Controllers

	private PWMVictorSPX leftMotor = new PWMVictorSPX(RobotMap.leftMotor);

	private PWMVictorSPX rightMotor = new PWMVictorSPX(RobotMap.rightMotor);

	// Initialize the type of drive -- DiffernetialDrive is used for Tank Drive

	public DifferentialDrive robotDrive = new DifferentialDrive(leftMotor, rightMotor);

	// Creates the DriveTrain Subsystem

	public DriveTrain() {

		super();

		System.out.println("Drive");

	}

	@Override

	public void initDefaultCommand() {

		setDefaultCommand(new tankDriveWithJoysticks());

	}

	public void log() {
		SmartDashboard.putNumber("Left Motor", leftMotor.get());

		SmartDashboard.putNumber("Right Motor", rightMotor.get());
	}

	public void drive(double leftY, double rightY) {

        // Prevents motor movement if the input is between the DEADBAND and the negative of the DEADBAND

        if (rightY < DEADBAND && rightY > -DEADBAND) {

			rightY = 0.0;

		}

		if (leftY < DEADBAND && leftY > -DEADBAND) {

			leftY = 0.0;

        }
        
        robotDrive.tankDrive(leftY, rightY);
	}
}