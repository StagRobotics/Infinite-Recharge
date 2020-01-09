package frc.robot;


import edu.wpi.first.networktables.*;
import frc.robot.commands.*;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;


public class OI {

	// Initialize Joysticks

	private Joystick leftJoystick = new Joystick(0);

	private Joystick rightJoystick = new Joystick(1);

	private JoystickButton doWheel = new JoystickButton(rightJoystick, 2);

	NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");

	public OI() {
		doWheel.whenPressed(new doWheel());
	}

	public Joystick getLeftJoystick() {

		return leftJoystick;

	}

	public Joystick getRightJoystick() {

		return rightJoystick;

	}

}