/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.StartEndCommand;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.FlywheelSubsystem;
import frc.robot.subsystems.IndexerSubsystem;
import frc.robot.subsystems.PlexiSubsystem;
import frc.robot.commands.FlywheelAutoOne;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class FINALSimpleShootAuto extends SequentialCommandGroup {
  /**
   *  
   * 
   * @param DriveSubsystem
   * @param FlywheelSubsystem
   * @param IndexerSubsystem
   * @param PlexiSubsystem
   * 
   * 
   */
   
  public FINALSimpleShootAuto(FlywheelSubsystem m_flywheelSubsystem, IndexerSubsystem m_indexerSubsystem, PlexiSubsystem m_plexiSubsystem, DriveSubsystem m_driveCommand) {
    addCommands(
        new RevUpFlywheel (m_flywheelSubsystem),
    parallel(
          new FlywheelAutoOne (m_flywheelSubsystem),
          new IndexerAutoOne (m_indexerSubsystem),
          new PlexiAutoOne (m_plexiSubsystem)),
        new SimpleShootAuto(m_driveCommand));
  }
}

