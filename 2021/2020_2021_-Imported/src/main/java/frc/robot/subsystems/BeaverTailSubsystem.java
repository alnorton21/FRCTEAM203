/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class BeaverTailSubsystem extends SubsystemBase {
  /**
   * Creates a new BeaverTailSubsystem.
   */

  private static WPI_VictorSPX beaverTail = new WPI_VictorSPX(Constants.kaccumulator);

  public BeaverTailSubsystem() {

  }
  
  public void beaverBoward (double speed){
    beaverTail.set(speed);
  }
  
  public void beaverBackward (double speed){
    beaverTail.set(-speed);
  }

  public void beaverBop (){
    beaverTail.set(0);
  }
  
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
