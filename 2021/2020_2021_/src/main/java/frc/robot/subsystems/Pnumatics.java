/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;


//TODO: Change the naming of one and two to front and back with motors

public class Pnumatics extends SubsystemBase {
  
/**
   * Creates a new Piston.
   */

   
  Compressor compressor;
  Solenoid solenoidR;// = new Solenoid(2);
  Solenoid solenoidL;// = new Solenoid(1);
  


  public Pnumatics() {
    
  compressor = new Compressor();
  
  compressor.setClosedLoopControl(true);
//  solenoidR = new Solenoid(2);
//  solenoidL = new Solenoid(1);

}

  public void enableSolenoids(){
  //  solenoidR.set(true);
  //  solenoidL.set(true);
  }

  public void disableSolenoids(){
  //  solenoidR.set(false);
  //  solenoidL.set(false);
  }


  public void enableCompressor(){
//    compressor.setClosedLoopControl(true);
    compressor.start();
  }
  
  public void disableCompressor(){
//    compressor.setClosedLoopControl(false);
    compressor.stop();
  
  }

  public Boolean compressorStatus(){
    return compressorStatus();
  }
/*
  public void compressorStatusPrint(){
    System.out.println(compressorStatus());
  }*/

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
   // System.out.println(compressor.getCompressorNotConnectedFault());
  }
  
}

