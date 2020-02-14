/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class Auto5Ball extends CommandGroup {
  /**
   * Add your docs here.
   */
  public Auto5Ball() {
    addSequential(new IntakeBalls());
    addSequential(new DriveStraight(68));
    addSequential(new Reset());
    addSequential(new getBallAuto(75));
    addSequential(new StraightenInPlace());
    addSequential(new Reset());
    addSequential(new DriveStraight(-200));
    addSequential(new Reset());
    addSequential(new TurnAngle(-30));
    addSequential(new Reset());
    addSequential(new DriveStraight(-67));
    addSequential(new Reset());
    addSequential(new TurnAngle(32));
    addSequential(new DriveStraight(-11));
    addSequential(new DumpBalls());
    // Add Commands here:
    // e.g. addSequential(new Command1());
    // addSequential(new Command2());
    // these will run in order.

    // To run multiple commands at the same time,
    // use addParallel()
    // e.g. addParallel(new Command1());
    // addSequential(new Command2());
    // Command1 and Command2 will run in parallel.

    // A command group will require all of the subsystems that each member
    // would require.
    // e.g. if Command1 requires chassis, and Command2 requires arm,
    // a CommandGroup containing them would require both the chassis and the
    // arm.
  }
}
