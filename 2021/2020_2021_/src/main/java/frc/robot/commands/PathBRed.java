/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.StartEndCommand;
import frc.robot.subsystems.BeaverTailSubsystem;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.FlywheelSubsystem;
import frc.robot.subsystems.NavXTest;
import frc.robot.subsystems.Pnumatics;


// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html

public class PathBRed extends SequentialCommandGroup {
  /**
   * Creates a new ComplexAuto.
   * 
   * @param DriveSubsystem
   * @param NavXTest
   * 
   */
  //double quarterTurn = 45; //45 degrees //0.5
  double startTime = 0;
  int ignore = 0; //this is to bypass deadzone

  double rotSpeed = 0.05;

  public PathBRed(DriveSubsystem m_driveCommand, NavXTest m_navX, BeaverTailSubsystem m_intake, Pnumatics m_pnumatics) {

    addCommands(
      new SolenoidOn(m_pnumatics),
        new IntakeOn(m_intake),
        new AutoDrive(m_driveCommand, m_navX,  0, 0  , -0.16, AutoDrive.Sensors.GYRO_LEFT, -2, true), //2
        new AutoDrive(m_driveCommand, m_navX,  0, 0.6, 0  , AutoDrive.Sensors.ENCODER, 7, false),    
        new AutoDrive(m_driveCommand, m_navX,  0, 0  , 0.3, AutoDrive.Sensors.GYRO_RIGHT, 16, false), //2
        new AutoDrive(m_driveCommand, m_navX,  0, 0.6, 0  , AutoDrive.Sensors.ENCODER, 8.5, false),     
        new AutoDrive(m_driveCommand, m_navX,  0, 0  , -0.3, AutoDrive.Sensors.GYRO_LEFT, -32, false), //2
        new AutoDrive(m_driveCommand, m_navX,  0, 0.6, 0  , AutoDrive.Sensors.ENCODER, 7.5, false),     
        new AutoDrive(m_driveCommand, m_navX,  0, 0  , 0.3, AutoDrive.Sensors.GYRO_RIGHT, -30, false), //2
        new AutoDrive(m_driveCommand, m_navX,  0, 0.6, 0  , AutoDrive.Sensors.ENCODER, 4, false),     
        new AutoDrive(m_driveCommand, m_navX,  0, 0.3, 0  , AutoDrive.Sensors.ENCODER, 6, false),     
        new IntakeOff(m_intake)

        );          
        
  }
}