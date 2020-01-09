package frc.robot.commands;

import frc.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class doWheel extends Command {

	public doWheel() {

		requires(Robot.m_RecordPlayer);

	}

	// Called repeatedly when this Command is scheduled to run

	@Override

	protected void execute() {
		Robot.m_RecordPlayer.doWheel();
	}

	// Make this return true when this Command no longer needs to run execute()

	@Override

	protected boolean isFinished() {

		return true;

	}

	// Called once after isFinished returns true

	@Override

	protected void end() {

	}

}