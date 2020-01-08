package frc.robot;


import edu.wpi.first.networktables.*;

import edu.wpi.first.wpilibj.Joystick;


public class OI {

	// Initialize Joysticks

	private Joystick leftJoystick = new Joystick(0);

	private Joystick rightJoystick = new Joystick(1);

	NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");

	public OI() {

	}

	public Joystick getLeftJoystick() {

		return leftJoystick;

	}

	public Joystick getRightJoystick() {

		return rightJoystick;

	}

}