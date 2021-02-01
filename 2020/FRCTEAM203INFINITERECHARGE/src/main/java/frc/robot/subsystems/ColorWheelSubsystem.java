
/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.revrobotics.ColorMatch;
import com.revrobotics.ColorMatchResult;
import com.revrobotics.ColorSensorV3;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ColorWheelConstants;

public class ColorWheelSubsystem extends SubsystemBase {
  

   // Creates a new ColorWheelSubsystem.
  
  public static WPI_TalonSRX colorWheel = new WPI_TalonSRX (ColorWheelConstants.kcolorWheel);

  public final I2C.Port i2cPort = I2C.Port.kOnboard;
  public final ColorSensorV3 colorSensor = new ColorSensorV3(i2cPort);
  public final ColorMatch colorMatcher = new ColorMatch();


  public ColorWheelSubsystem() {
 
  }

  public void Forward(double speed){
    colorWheel.set(speed);
  }

  public void GoYellow(){

    Color detectedColor = colorSensor.getColor();
                                                                        
    ColorMatchResult match = colorMatcher.matchClosestColor(detectedColor);

    colorMatcher.addColorMatch(ColorWheelConstants.kblueTarget);
    colorMatcher.addColorMatch(ColorWheelConstants.kgreenTarget);
    colorMatcher.addColorMatch(ColorWheelConstants.kredTarget);
    colorMatcher.addColorMatch(ColorWheelConstants.kyellowTarget);

    while(match.color != ColorWheelConstants.kyellowTarget){
    
      detectedColor = colorSensor.getColor();                                                                  
      match = colorMatcher.matchClosestColor(detectedColor);
  
      if ( match.color == ColorWheelConstants.kredTarget){
        colorWheel.set(0.5);
      }
      else if (match.color == ColorWheelConstants.kblueTarget ||match.color == ColorWheelConstants.kgreenTarget){
        colorWheel.set(-0.5);
      }
    }

  colorWheel.set(0);
  
}
public void GoGreen(){

  Color detectedColor = colorSensor.getColor();
                                                                      
  ColorMatchResult match = colorMatcher.matchClosestColor(detectedColor);

  colorMatcher.addColorMatch(ColorWheelConstants.kblueTarget);
  colorMatcher.addColorMatch(ColorWheelConstants.kgreenTarget);
  colorMatcher.addColorMatch(ColorWheelConstants.kredTarget);
  colorMatcher.addColorMatch(ColorWheelConstants.kyellowTarget);

  while(match.color != ColorWheelConstants.kgreenTarget){
  
    detectedColor = colorSensor.getColor();                                                                  
    match = colorMatcher.matchClosestColor(detectedColor);

    if ( match.color == ColorWheelConstants.kredTarget){
      colorWheel.set(0.5);
    }
    else if (match.color == ColorWheelConstants.kblueTarget || match.color == ColorWheelConstants.kyellowTarget){
      colorWheel.set(-0.5);
    }
  }

colorWheel.set(0);

}
public void GoRed(){

  Color detectedColor = colorSensor.getColor();
                                                                      
  ColorMatchResult match = colorMatcher.matchClosestColor(detectedColor);

  colorMatcher.addColorMatch(ColorWheelConstants.kblueTarget);
  colorMatcher.addColorMatch(ColorWheelConstants.kgreenTarget);
  colorMatcher.addColorMatch(ColorWheelConstants.kredTarget);
  colorMatcher.addColorMatch(ColorWheelConstants.kyellowTarget);

  while(match.color != ColorWheelConstants.kredTarget){
  
    detectedColor = colorSensor.getColor();                                                                  
    match = colorMatcher.matchClosestColor(detectedColor);

    if ( match.color == ColorWheelConstants.kgreenTarget){
      colorWheel.set(0.5);
    }
    else if (match.color == ColorWheelConstants.kblueTarget ||match.color == ColorWheelConstants.kyellowTarget){
      colorWheel.set(-0.5);
    }
  }

colorWheel.set(0);

}
public void GoBlue(){

Color detectedColor = colorSensor.getColor();
                                                                    
ColorMatchResult match = colorMatcher.matchClosestColor(detectedColor);

colorMatcher.addColorMatch(ColorWheelConstants.kblueTarget);
colorMatcher.addColorMatch(ColorWheelConstants.kgreenTarget);
colorMatcher.addColorMatch(ColorWheelConstants.kredTarget);
colorMatcher.addColorMatch(ColorWheelConstants.kyellowTarget);

while(match.color != ColorWheelConstants.kblueTarget){

  detectedColor = colorSensor.getColor();                                                                  
  match = colorMatcher.matchClosestColor(detectedColor);

  if ( match.color == ColorWheelConstants.kyellowTarget){
    colorWheel.set(0.5);
  }
  else if (match.color == ColorWheelConstants.kredTarget||match.color == ColorWheelConstants.kgreenTarget ){
    colorWheel.set(-0.5);
  }
}

colorWheel.set(0);

}



  @Override
  public void periodic() {
    Color detectedColor = colorSensor.getColor();

    String colorString;                                                                           
    ColorMatchResult match = colorMatcher.matchClosestColor(detectedColor);

    colorMatcher.addColorMatch(ColorWheelConstants.kblueTarget);
    colorMatcher.addColorMatch(ColorWheelConstants.kgreenTarget);
    colorMatcher.addColorMatch(ColorWheelConstants.kredTarget);
    colorMatcher.addColorMatch(ColorWheelConstants.kyellowTarget);


    
    if (match.color == ColorWheelConstants.kblueTarget){
      colorString = "Blue";

    } else if (match.color == ColorWheelConstants.kredTarget){
      colorString = "Red";

    } else if (match.color == ColorWheelConstants.kgreenTarget){
      colorString = "Green";

    } else if (match.color == ColorWheelConstants.kyellowTarget){
      colorString = "Yellow";
      
    } else {
      colorString = "Unknown";
    }

    SmartDashboard.putNumber("Red", detectedColor.red);
    SmartDashboard.putNumber("Green", detectedColor.green);
    SmartDashboard.putNumber("Blue", detectedColor.blue);

    SmartDashboard.putNumber("Confidence", match.confidence);
    SmartDashboard.putString("Detected Color", colorString);  }
}

