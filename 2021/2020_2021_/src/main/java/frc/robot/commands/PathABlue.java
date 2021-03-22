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


// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html

public class PathABlue extends SequentialCommandGroup {
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

  public PathABlue(DriveSubsystem m_driveCommand, NavXTest m_navX, FlywheelSubsystem m_flywheel) {

    addCommands(
        new AutoDrive(m_driveCommand, m_navX,  0, 0.3, 0  , AutoDrive.Sensors.ENCODER, 4.5, true),
        new AutoDrive(m_driveCommand, m_navX,  0, 0  , -0.1, AutoDrive.Sensors.GYRO_LEFT, -80, false),
        new AutoDrive(m_driveCommand, m_navX,  0, 0.3, 0  , AutoDrive.Sensors.ENCODER, 4, false),
        new FlywheelOn(m_flywheel),
        new AutoDrive(m_driveCommand, m_navX,  0, 0.3, 0  , AutoDrive.Sensors.ENCODER, 4, false),
        new FlywheelOff(m_flywheel)


        );          
        
  }
}