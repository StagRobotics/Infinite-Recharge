package frc.robot;


import edu.wpi.first.networktables.*;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;


public class OI {

	// Initialize Joysticks

	private Joystick leftJoystick = new Joystick(0);

	private Joystick rightJoystick = new Joystick(1);

	private Joystick auxJoystick = new Joystick(2);

	NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");

	public OI() {
		JoystickButton dumpButton = new JoystickButton(auxJoystick, 1);


		dumpButton.whenPressed(new DumpBalls());
	}

	public Joystick getLeftJoystick() {

		return leftJoystick;

	}

	public Joystick getRightJoystick() {

		return rightJoystick;

	}

}