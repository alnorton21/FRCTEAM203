/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
public class HoodSubsystem extends SubsystemBase {
  /**
   * Creates a new HoodSubsystem.
   */

   public static VictorSPX hoodMotor = new VictorSPX(Constants.khood);

  public HoodSubsystem() {

  }

  public void hoodUp (double speed){
    hoodMotor.set(ControlMode.PercentOutput, speed);
  }
  
  public void hoodDown (double speed){
    hoodMotor.set(ControlMode.PercentOutput, -speed);
  }
    public void hoodStop (){
    hoodMotor.set(ControlMode.PercentOutput, 0);
  }

  public double getEncoderValue(){
    return hoodMotor.getSelectedSensorPosition();
  }

  public void resetEncoderValue(){
 //   hoodMotor.getSensorCollection().setQuadraturePosition(0, 10);
  }

  @Override
  public void periodic() {
    SmartDashboard.putNumber("Hood Encoder Value", getEncoderValue());
  }
}
