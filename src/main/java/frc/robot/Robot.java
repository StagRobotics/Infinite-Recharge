/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import frc.robot.subsystems.ClimbAndBalance;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.PizzaOven;
import frc.robot.subsystems.RecordPlayer;
import edu.wpi.first.wpilibj.command.Scheduler;

public class Robot extends TimedRobot {
  
  public static DriveTrain m_drivetrain;
  public static PizzaOven m_PizzaOven;
  public static ClimbAndBalance m_ClimbAndBalance;

  public static RecordPlayer m_RecordPlayer;
  public static OI m_oi;

  

  public void robotInit() {

    // Assigns each variable to a subsystem

    m_RecordPlayer = new RecordPlayer();

    m_drivetrain = new DriveTrain();
    m_ClimbAndBalance = new ClimbAndBalance();
    m_PizzaOven = new PizzaOven();
    m_oi =new OI();

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

  }

  @Override

  public void autonomousPeriodic() {

    Scheduler.getInstance().run();

  }

  @Override

  public void teleopInit() {

  }

  @Override

  public void teleopPeriodic() {

    Scheduler.getInstance().run();

  }

  @Override

  public void testPeriodic() {

  }

}
