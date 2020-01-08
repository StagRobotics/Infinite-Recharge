/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.revrobotics.ColorMatch;
import com.revrobotics.ColorMatchResult;
import com.revrobotics.ColorSensorV3;

import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.util.Color;
import frc.robot.Robot;
import frc.robot.RobotMap;

// Steps of Color Matching
// 1. Set Values of Colors from VEX website
// 2. Add Colors to the ColorMatch
// 3. Get the Reading from the Sensor and find the Closest Color from the ColorMatch
// 4. Return the String

public class RecordPlayer extends Subsystem {
  
  private final double ARMSPEED = 0.5;

  // Step 1
  private final ColorMatch m_colorMatcher = new ColorMatch();
  private final Color kBlueTarget = ColorMatch.makeColor(0.143, 0.427, 0.429);
  private final Color kGreenTarget = ColorMatch.makeColor(0.197, 0.561, 0.240);
  private final Color kRedTarget = ColorMatch.makeColor(0.561, 0.232, 0.114);
  private final Color kYellowTarget = ColorMatch.makeColor(0.361, 0.524, 0.113);

  private boolean isArmOut = false;

  private Spark ToneArm = new Spark(RobotMap.toneArmPort);
  private ColorSensorV3 Stylus = new ColorSensorV3(RobotMap.StylusPort);
  private Relay Rotor = new Relay(RobotMap.rotorPort);
  
  public RecordPlayer() {
    // Step 2
    m_colorMatcher.addColorMatch(kBlueTarget);
    m_colorMatcher.addColorMatch(kGreenTarget);
    m_colorMatcher.addColorMatch(kRedTarget);
    m_colorMatcher.addColorMatch(kYellowTarget);
  }

  @Override
  protected void initDefaultCommand() {
    // TODO Auto-generated method stub

  }

  public void moveArmOut() {
    ToneArm.set(ARMSPEED);
    isArmOut = true;
  }

  public void moveArmIn(){
    ToneArm.set(-ARMSPEED);
    isArmOut = false;
  }

  public void stopArm(){
    ToneArm.set(0.0);
  }

  public String getColorString(){
    String result = "";
    Color detectedColor = Stylus.getColor();
    // Step 3
    ColorMatchResult match = m_colorMatcher.matchClosestColor(detectedColor);

    return result;
  }
  public void completeStage2(){
    moveArmOut();
  }

}
