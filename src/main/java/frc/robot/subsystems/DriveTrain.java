package frc.robot.subsystems;

import frc.robot.Robot;

// Import packages needed to run

import frc.robot.RobotMap;

import frc.robot.commands.*;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PWMVictorSPX;

import edu.wpi.first.wpilibj.command.Subsystem;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;



public class DriveTrain extends Subsystem {

	// Initialize Static Variables

	private final double DEADBAND = 0.05;

	private final double WHEEL_DIAMETER = 6.0;

	// Initialize Motor Controllers

	private PWMVictorSPX leftMotor = new PWMVictorSPX(RobotMap.leftMotor);

	private PWMVictorSPX rightMotor = new PWMVictorSPX(RobotMap.rightMotor);

	// Initialize the type of drive -- DiffernetialDrive is used for Tank Drive

	public DifferentialDrive robotDrive = new DifferentialDrive(leftMotor, rightMotor);

	public Encoder leftEncoder = new Encoder(RobotMap.leftEncoderPort1, RobotMap.leftEncoderPort2, false, Encoder.EncodingType.k4X);
	public Encoder rightEncoder = new Encoder(RobotMap.rightEncoderPort1, RobotMap.rightEncoderPort2, false, Encoder.EncodingType.k4X);

	// Creates the DriveTrain Subsystem

	public DriveTrain() {

		super();

		System.out.println("Drive");

		leftEncoder.setMaxPeriod(1);
		leftEncoder.setMinRate(10);
		leftEncoder.setDistancePerPulse(0.00275);
		leftEncoder.setReverseDirection(true);
		leftEncoder.setSamplesToAverage(7);
		leftEncoder.reset();

		rightEncoder.setMaxPeriod(1);
		rightEncoder.setMinRate(10);
		rightEncoder.setDistancePerPulse(0.00275);
		rightEncoder.setReverseDirection(true);
		rightEncoder.setSamplesToAverage(7);
		rightEncoder.reset();

		addChild("Left Encoder", leftEncoder);
		addChild("Right Encoder", rightEncoder);
	}

	@Override

	public void initDefaultCommand() {

		setDefaultCommand(new tankDriveWithJoysticks());

	}

	public void log() {
		SmartDashboard.putNumber("Left Motor", leftMotor.get());

		SmartDashboard.putNumber("Right Motor", rightMotor.get());

		SmartDashboard.putString("Color Reading", Robot.m_RecordPlayer.getColorString());

		SmartDashboard.putNumber("Encoder Left", getLeftInches());
		SmartDashboard.putNumber("Encoder Right", getRightInches());
	}

	public double getLeftInches() {
		return leftEncoder.getDistance() * Math.PI * WHEEL_DIAMETER;
	}

	public double getRightInches() {
		return rightEncoder.getDistance() * Math.PI * WHEEL_DIAMETER;
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