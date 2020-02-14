package frc.robot;


import edu.wpi.first.networktables.*;
import frc.robot.commands.*;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;


public class OI {

	// Initialize Joysticks

	private Joystick leftJoystick = new Joystick(0);

	private Joystick rightJoystick = new Joystick(1);

	NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");

	public OI() {

		JoystickButton getBallButton = new JoystickButton(rightJoystick, 3);
		getBallButton.whileActive(new getBall());
		getBallButton.whenReleased(new StopDrive());

		JoystickButton dumpButton = new JoystickButton(auxJoystick, 6);

		dumpButton.whenPressed(new DumpBalls());
		dumpButton.whenReleased(new DumpBalls());
		JoystickButton intakeButton = new JoystickButton(auxJoystick, 7);

		intakeButton.whenPressed(new IntakeBalls());
		intakeButton.whenReleased(new IntakeBalls());

		JoystickButton climbForwardButton = new JoystickButton(auxJoystick, 2);

		climbForwardButton.whenPressed(new Climb());

		climbForwardButton.whenReleased(new ClimbOff());

		JoystickButton climbBackwardsButton = new JoystickButton(auxJoystick, 3);

		climbBackwardsButton.whenPressed(new ClimbBackwards());

		climbBackwardsButton.whenReleased(new ClimbOff());

		JoystickButton balanceForwardButton = new JoystickButton(auxJoystick, 4);

		balanceForwardButton.whenPressed(new Balance());

		balanceForwardButton.whenReleased(new BalanceOff());

		JoystickButton balanceBackwardsButton = new JoystickButton(auxJoystick, 5);

		balanceBackwardsButton.whenPressed(new BalanceBack());

		balanceBackwardsButton.whenReleased(new BalanceOff());

		JoystickButton driveTest = new JoystickButton(rightJoystick, 6);

		//driveTest.whenPressed(new Auto6Ball());
		driveTest.whenPressed(new Auto5Ball());
		JoystickButton reset = new JoystickButton(rightJoystick, 4);
		reset.whenPressed(new Reset());
		doWheel.whenPressed(new doWheel());
	}

	public Joystick getLeftJoystick() {

		return leftJoystick;

	}

	public Joystick getRightJoystick() {

		return rightJoystick;

	}

}