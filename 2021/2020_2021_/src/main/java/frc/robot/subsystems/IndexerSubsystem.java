/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class IndexerSubsystem extends SubsystemBase {
  /**
   * Creates a new IndexerSubsystem.
   */
  private static WPI_TalonSRX indexerOne = new WPI_TalonSRX(Constants.kIndexer);
//  private static WPI_VictorSPX indexerTwo = new WPI_VictorSPX(10);

  public IndexerSubsystem() {

  }

  public void indexerForward (double speed){
    indexerOne.set(speed);
 //   indexerTwo.set(-speed);
  }
  
  public void indexerBackward (double speed){
    indexerOne.set(-speed);
 //   indexerTwo.set(speed);
  }

  public void indexerStop (){
    indexerOne.set(0);
 //   indexerTwo.set(0);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
