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

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.TurretConstants;
import frc.robot.subsystems.VisionTrackingSubsystem;

public class TurretSubsystem extends SubsystemBase {
  /**
   * Creates a new TurretSubsystem.
   */

 private VisionTrackingSubsystem vturret = new VisionTrackingSubsystem();

  public static VictorSPX turretOne = new VictorSPX(TurretConstants.kturretOne);
  //public static WPI_VictorSPX turretTwo = new WPI_VictorSPX(TurretConstants.kturretTwo);
 

 
  public TurretSubsystem() {

  }

  public void turretRotate(){

    turretOne.set(ControlMode.PercentOutput, vturret.light.getTX()*0.1);     
  //  turretOne.set(ControlMode.PercentOutput, -vturret.light.getTX()*0.1);     

  } 

/*
  public void turretRotateLeft (double turn, double turnL){
if(turnL == 0.0){
    turretOne.set(turn);
}
else{
    turretOne.set(-turnL);
}
  }
*/
  public void turretRotate(double turn) {
    turretOne.set(ControlMode.PercentOutput,turn);  

  }

/*
  public void turretRight(double turn) {
    turretOne.set(turn);  
    //turretTwo.set(-turn); 
  }

  public void turretLeft(double turn) {
    turretOne.set(-turn);  
    //turretTwo.set(-turn); 
  }
*/

  public void turretStop (){
    turretOne.set(ControlMode.PercentOutput, 0);
    //turretTwo.set(0);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
