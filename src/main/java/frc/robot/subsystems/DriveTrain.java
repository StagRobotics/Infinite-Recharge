package frc.robot.subsystems;

import frc.robot.Robot;

// Import packages needed to run

import frc.robot.RobotMap;

import frc.robot.commands.*;
import edu.wpi.first.networktables.NetworkTableInstance;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PWMVictorSPX;
import edu.wpi.first.wpilibj.Encoder;

import edu.wpi.first.wpilibj.command.Subsystem;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.interfaces.Gyro;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.networktables.*;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.SPI;


public class DriveTrain extends Subsystem {

	// Initialize Static Variables

	private final double DEADBAND = 0.10;
	private final double SPEED = 0.40;
	private final double WHEEL_DIAMETER = 6.0;

	private final double WHEEL_DIAMETER = 6.0;

	// Initialize Motor Controllers

	private PWMVictorSPX leftMotor = new PWMVictorSPX(RobotMap.leftMotor);

	private PWMVictorSPX rightMotor = new PWMVictorSPX(RobotMap.rightMotor);

	private Encoder leftEncoder = new Encoder(RobotMap.LeftOne, RobotMap.LeftTwo, false, Encoder.EncodingType.k4X);

	private Encoder rightEncoder = new Encoder(RobotMap.RightOne, RobotMap.RightTwo, false, Encoder.EncodingType.k4X);

	// Initialize the type of drive -- DiffernetialDrive is used for Tank Drive

	public DifferentialDrive robotDrive = new DifferentialDrive(leftMotor, rightMotor);

	public NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");

	public NetworkTableEntry tx = table.getEntry("tx");
	public NetworkTableEntry ty = table.getEntry("ty");
	public NetworkTableEntry ta = table.getEntry("ta");

	public AnalogGyro gyro = new AnalogGyro(0);

	public Encoder leftEncoder = new Encoder(RobotMap.leftEncoderPort1, RobotMap.leftEncoderPort2, false, Encoder.EncodingType.k4X);
	public Encoder rightEncoder = new Encoder(RobotMap.rightEncoderPort1, RobotMap.rightEncoderPort2, false, Encoder.EncodingType.k4X);

	// Creates the DriveTrain Subsystem

	public DriveTrain() {

		super();

		System.out.println("Drive");
		leftEncoder.setMaxPeriod(1);
		leftEncoder.setMinRate(10);
		//leftEncoder.setDistancePerPulse(0.00385);
		leftEncoder.setDistancePerPulse(0.00275);
		leftEncoder.setReverseDirection(true);
		leftEncoder.setSamplesToAverage(10);
		leftEncoder.reset();

		rightEncoder.setMaxPeriod(1);
		rightEncoder.setMinRate(10);
		rightEncoder.setDistancePerPulse(0.00385);
		//rightEncoder.setDistancePerPulse(0.00275);
		rightEncoder.setReverseDirection(false);
		rightEncoder.setSamplesToAverage(10);
		rightEncoder.reset();

		gyro.calibrate();
		gyro.reset();

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

		SmartDashboard.putNumber("LimeLightX", tx.getDouble(0.0));
		SmartDashboard.putNumber("LimeLightY", ty.getDouble(0.0));
		SmartDashboard.putNumber("LimeLightArea", ta.getDouble(0.0));

		SmartDashboard.putNumber("Left Encoder", getLeftEncoder());
		SmartDashboard.putNumber("Right Encoder", getRightEncoder());

		SmartDashboard.putNumber("SPI Gyro Get Angle", gyro.getAngle());
		SmartDashboard.putNumber("Distance", getDistance());

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
        
        robotDrive.tankDrive(-leftY, -rightY);
	}

	public void getBall(){
		if(tx.getDouble(0.0) < -1){
			robotDrive.tankDrive(SPEED, SPEED + 0.3);
		} else if(tx.getDouble(0.0) > 1){
			robotDrive.tankDrive(SPEED + 0.3, SPEED);
		} else {
			robotDrive.tankDrive(SPEED, SPEED);
		}
	}
	
	public double getLeftEncoder(){
		return leftEncoder.getDistance()* Math.PI * WHEEL_DIAMETER;
	}

	public double getRightEncoder(){
		return rightEncoder.getDistance()* Math.PI * WHEEL_DIAMETER;
	}

	public double getAngle() {
		return gyro.getAngle();
	}

	public double getDistance(){
		return Math.abs(getLeftEncoder() + getRightEncoder())/2;
	}
	
	public double getBallY(){
		return ty.getDouble(0.0);
	}

	public double getBallX(){
		return tx.getDouble(0.0);
	}
	public void reset(){
		gyro.reset();
		leftEncoder.reset();
		rightEncoder.reset();
	}
}