/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.IndexerSubsystem;
import edu.wpi.first.wpilibj2.command.StartEndCommand;
import edu.wpi.first.wpilibj.Timer;


// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class IndexerAutoOne extends SequentialCommandGroup {
  /**
   * Creates a new IndexerAutoOne.
   */
  public IndexerAutoOne(IndexerSubsystem m_indexerSubsystem) {
  addCommands(
    new StartEndCommand(
    () -> m_indexerSubsystem.indexerForward(0.5),
    () -> m_indexerSubsystem.indexerStop(),m_indexerSubsystem)
    .withInterrupt(() -> Timer.getMatchTime() < 8));
  }
}
