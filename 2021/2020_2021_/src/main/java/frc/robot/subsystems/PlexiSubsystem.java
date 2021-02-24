/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class PlexiSubsystem extends SubsystemBase {

  private static WPI_VictorSPX plexiMotor = new WPI_VictorSPX(90); 

  public PlexiSubsystem() {

  }

public void up(double speed){
  plexiMotor.set(speed);
}

public void down(double speed){
  plexiMotor.set(-speed);
}

public void stop(){
  plexiMotor.set(0);
}

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
