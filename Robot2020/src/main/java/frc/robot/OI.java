package frc.robot;


import edu.wpi.first.networktables.*;
import frc.robot.commands.*;
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

		JoystickButton intakeButton = new JoystickButton(auxJoystick, 3);

		intakeButton.whenPressed(new IntakeBalls());

		JoystickButton climbButton = new JoystickButton(auxJoystick, 4);

		climbButton.whenPressed(new Climb());

		JoystickButton balanceButton = new JoystickButton(auxJoystick, 5);

		balanceButton.whenPressed(new Balance());
	}

	public Joystick getLeftJoystick() {

		return leftJoystick;

	}

	public Joystick getRightJoystick() {

		return rightJoystick;

	}

}