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

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.Color;
import frc.robot.Robot;
import frc.robot.RobotMap;

// Steps of Color Matching
// 1. Set Values of Colors from VEX website
// 2. Add Colors to the ColorMatch
// 3. Get the Reading from the Sensor and find the Closest Color from the ColorMatch
// 4. Return the String
// 5. Do stuff with the String

public class RecordPlayer extends Subsystem {
  
  private final double ARMSPEED = 0.35;
  private final boolean ISINVERSEROTOR = false;
  private final int NUMBEROFTIMESAFTER = 4 ;

  // Step 1
  private final ColorMatch m_colorMatcher = new ColorMatch();
  private final Color kBlueTarget = ColorMatch.makeColor(0.143, 0.427, 0.429);
  private final Color kGreenTarget = ColorMatch.makeColor(0.197, 0.561, 0.240);
  private final Color kRedTarget = ColorMatch.makeColor(0.561, 0.232, 0.114);
  private final Color kYellowTarget = ColorMatch.makeColor(0.361, 0.524, 0.113);

  private Timer timer = new Timer();

  private boolean isArmOut = false;

  private DoubleSolenoid ToneArm = new DoubleSolenoid(RobotMap.ToneArmPort1, RobotMap.ToneArmPort2);
  private ColorSensorV3 Stylus = new ColorSensorV3(RobotMap.StylusPort);
  private Talon Rotor = new Talon(RobotMap.rotorPort); 
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
    moveArmIn();

  }

  public void moveArmIn() {
    ToneArm.set(DoubleSolenoid.Value.kForward);
    isArmOut = true;
  }

  public void moveArmOut(){
    ToneArm.set(DoubleSolenoid.Value.kReverse);
    isArmOut = false;
  }

  public void turnWheel(double speed){
    Rotor.set(speed);
  }

  public void stopWheel(){
    Rotor.set(0.0);
  }

  public String getColorString(){

    String colorString;

    Color detectedColor = Stylus.getColor();

    // Step 3
    ColorMatchResult match = m_colorMatcher.matchClosestColor(detectedColor);
    if (match.color == kBlueTarget) {
      colorString = "Blue";
    } else if (match.color == kRedTarget) {
      colorString = "Red";
    } else if (match.color == kGreenTarget) {
      colorString = "Green";
    } else if (match.color == kYellowTarget) {
      colorString = "Yellow";
    } else {
      colorString = "Unknown";
    }

    // Step 4
    return colorString;
  }

  public void completeStage2(){
    int count = 0;

    // Moves toneArmOut
    moveArmOut();

    // Get whatever color is under the sensor when the needle is first dropped
    String startingColor = getColorString();
    String lastColor = getColorString();

    // Turns the wheel in a given direction
    turnWheel(ARMSPEED);

    // Each color is on the wheel twice and therefore we need to rotate at least three times
    // Each "count" is equal to half of a rotation so 3 * 2 = 6
    // Therefore continue using the motor until we see the color 6 times
    while(count < 6){
      // Get whatever the motor currently sees
      String currentColor = getColorString();
      // If the currentColor is equal to staring color, then we know we have completed a 1/2 rotation so increase the count by 1
      // Last color prevents the sensor from reading the same panel twice
      if(currentColor.equals(startingColor) && !currentColor.equals(lastColor)){
        count++;
      }

      // Sets the lastColor read to the currentColor
      lastColor = currentColor;
    }
    
    // We want to spin the color wheel a little over three times to make sure the sensor on the field sees three complete rotations
    // Used to count the Colors past the final starting color
    int nextColorCounter = 0;

    // NUMBEROFTIMESAFTER is the number of colors past the final third rotation
    while(nextColorCounter < NUMBEROFTIMESAFTER){
      String currentColor = getColorString();

      // If the current color is different than the previous color
      if(!currentColor.equals(lastColor)){
        nextColorCounter++;
        
        // Set the last Color read to the starting color
        lastColor = currentColor;
      }
    }

    // Stops Wheel
    stopWheel();

    pause(1);

    // Raises arm back into chassis
    moveArmIn();

  }

  public void pause(double time){
    timer.reset();
    timer.start();
    while(timer.get() < time){
      SmartDashboard.putString("Timer Status", "Waiting");
    }
    SmartDashboard.putString("Timer Status", "Done");
  }
  // The sensor of the field reads the color that is located two positions after the color the robot sees
  // Takes the color from the field and transforms it into the color that we need to get too
  public char getCorrectColor(char color){
    char result;
    switch (color)
    {
      case 'B' :
        result = 'R'; 
        break;
      case 'G':
        result = 'Y';
        break;
      case 'R' :
        result = 'B';
        break;
      case 'Y' :
        result = 'G';
        break;
      default :
        result = 'E';
        break;
    }
    return result;
  }



  public void completeStage3(char color) {
    // Moves toneArmOut
    moveArmOut();

    // Get whatever color is under the sensor when the needle is first dropped
    String startingColor = getColorString();
    String previousColor = getColorString();
    // Turns the wheel in a given direction
    turnWheel(-ARMSPEED);
    
    while(startingColor.charAt(0) != color){
      startingColor = getColorString();
      if((previousColor.charAt(0) == 'R' || previousColor.charAt(0) == 'G') && startingColor.charAt(0) == 'Y'){
        startingColor = "Green";
        pause(0.2);
      }
      Robot.m_drivetrain.log();
      previousColor = startingColor;
    }

    turnWheel(ARMSPEED);
    pause(0.6);

    // Stops the wheel
    stopWheel();

    pause(1);

    // Pulls the toneArm back in
    moveArmIn();
  }

  public void doWheel(){
    String gameData = DriverStation.getInstance().getGameSpecificMessage();
    if(gameData.length() > 0){
      char correctColor = getCorrectColor(gameData.charAt(0));
      completeStage3(correctColor);
    } else {
      completeStage2();
    }
  }
}