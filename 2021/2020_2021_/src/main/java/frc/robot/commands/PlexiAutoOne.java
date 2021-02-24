/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.PlexiSubsystem;
import edu.wpi.first.wpilibj2.command.StartEndCommand;
import edu.wpi.first.wpilibj.Timer;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class PlexiAutoOne extends SequentialCommandGroup {
  /**
   * Creates a new PlexiAutoOne.
   */
  public PlexiAutoOne(PlexiSubsystem m_plexiSubsystem) {
  addCommands(
    new StartEndCommand(
      () -> m_plexiSubsystem.up(1), 
      () -> m_plexiSubsystem.stop(), m_plexiSubsystem)
       .withInterrupt(() -> Timer.getMatchTime() < 8));
  }
}
