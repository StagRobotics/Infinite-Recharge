package frc.robot;

import edu.wpi.first.wpilibj.Solenoid;

import edu.wpi.first.wpilibj.I2C.Port;

public class RobotMap {
    // PWM
    public static int leftMotor = 0;

    public static int rightMotor = 1;

    public static int winchMotor = 2;

    public static int balancerMotor = 3;
      
    public static int rotorPort = 4;

    //Relay

    public static int dumpMotor = 1;

    public static int intakeMotor = 0;

    //Solenoid

    public static int pizzaPanPort = 0;

    public static int pizzaPanPort2 = 1;

    public static int ToneArmPort1 = 2;
     
    public static int ToneArmPort2 = 3;
    //Digital Input


    public static int LeftOne = 0;

    public static int LeftTwo = 1;

    public static int RightOne = 2;

    public static int RightTwo = 3;

  

    public static Port StylusPort = Port.kOnboard;

    public static int leftEncoderPort1 = 0;
    public static int leftEncoderPort2 = 1;

    public static int rightEncoderPort1 = 2;
    public static int rightEncoderPort2 = 3;

}