/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import frc.robot.commands.Auto5Ball;
import frc.robot.commands.Auto6Ball;
import frc.robot.commands.AutonomousNoAuto;
import frc.robot.commands.Wait;
import frc.robot.commands.manualDumpDown;
import frc.robot.subsystems.ClimbAndBalance;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.PizzaOven;
import frc.robot.subsystems.RecordPlayer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends TimedRobot {
  SendableChooser<String> autoChooser = new SendableChooser<>();

  public static DriveTrain m_drivetrain;
  public static PizzaOven m_PizzaOven;
  public static ClimbAndBalance m_ClimbAndBalance;

  public static RecordPlayer m_RecordPlayer;
  public static OI m_oi;
  public static String AutoCommand = "";
  public static Command command;
  

  public void robotInit() {

    // Assigns each variable to a subsystem

    m_RecordPlayer = new RecordPlayer();

    m_drivetrain = new DriveTrain();
    m_ClimbAndBalance = new ClimbAndBalance();
    m_PizzaOven = new PizzaOven();
    m_oi =new OI();

    autoChooser.setDefaultOption("Do Nothing", "N");
    autoChooser.addOption("5 Ball Auto", "5");
    autoChooser.addOption("6 Ball Auto", "6");
		autoChooser.addOption("Dump", "D");


    SmartDashboard.putData("Chooser", autoChooser);
  }
  @Override

  public void robotPeriodic() {

  }

  @Override

  public void disabledInit() {

  }

  @Override

  public void disabledPeriodic() {

    Scheduler.getInstance().run();

  }

  @Override

  public void autonomousInit() {
    AutoCommand = autoChooser.getSelected();
    switch(AutoCommand){ 
      case "6":
        command = new Auto6Ball();
        break;
      case "5":
        command = new Auto5Ball();
        break;
      case "D":
        command = new manualDumpDown();
        break;
      default:
        command = new AutonomousNoAuto();
        break;
    }

    command.start();

  }

  @Override

  public void autonomousPeriodic() {
    SmartDashboard.putString("Current Command", command.getName());

    Scheduler.getInstance().run();
    SmartDashboard.putBoolean("Running", command.isRunning());
    SmartDashboard.putData(Scheduler.getInstance());
  }

  @Override

  public void teleopInit() {

  }

  @Override

  public void teleopPeriodic() {
    Scheduler.getInstance().run();
    SmartDashboard.putData(Scheduler.getInstance());
  }

  @Override

  public void testPeriodic() {

  }

}
