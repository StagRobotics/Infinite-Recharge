/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Auto5Ball extends CommandGroup {
  /**
   * Add your docs here.
   */
  public Auto5Ball() {
    addSequential(new PutSmartDashboard("Intake"));
    addSequential(new IntakeBalls());
    addSequential(new PutSmartDashboard("Driving to Trench"));
    addSequential(new DriveStraight(65, true));
    addSequential(new PutSmartDashboard("Getting Balls"));
    addSequential(new Reset());
    addSequential(new getBallAuto(67));
    addSequential(new PutSmartDashboard("Going to Zero"));
    addSequential(new Wait(1));
    addSequential(new TurnAngle(0));
    addSequential(new PutSmartDashboard("Resetting"));
    addSequential(new Reset()); 
    addSequential(new Reset());   
    addSequential(new PutSmartDashboard("Driving Straight"));
    addSequential(new DriveStraight(-190, false));
    addSequential(new IntakeBalls());
    addSequential(new Reset());
    addSequential(new PutSmartDashboard("Turning"));
    addSequential(new TurnAngle(-46));
    addSequential(new Reset());
    addSequential(new PutSmartDashboard("Driving Straight"));
    addSequential(new DriveStraight(-45, false));
    addSequential(new Reset());
    addSequential(new PutSmartDashboard("Turning"));
    addSequential(new TurnAngle(57));
    addSequential(new PutSmartDashboard( "Driving Straight"));
    addSequential(new DriveStraight(-19, false));
    addSequential(new manualDumpDown());
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
