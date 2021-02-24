/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.FlywheelConstants;

public class FlywheelSubsystem extends SubsystemBase {
  /**
   * Creates a new FlywheelSubsystem.
   */
  public static WPI_TalonSRX flywheelOne = new WPI_TalonSRX(FlywheelConstants.kflywheelOne);
  public static WPI_TalonSRX flywheelTwo = new WPI_TalonSRX(FlywheelConstants.kflywheelTwo);
  
  public FlywheelSubsystem() {
    flywheelOne.setInverted(true);



  }

  public void forwardFlywheel (double speed){
    flywheelOne.set(speed);
    flywheelTwo.set(speed);
  }

  public void reverseFlywheel (double speed){
    flywheelOne.set(-speed);
    flywheelTwo.set(-speed);
  }

  public void stopFlywheel (){
    flywheelOne.set(0);
    flywheelTwo.set(0);
  }

  public double getSpeed(){
    return flywheelOne.getMotorOutputPercent();
  }
  
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
