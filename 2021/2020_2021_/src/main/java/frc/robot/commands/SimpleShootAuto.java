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
import frc.robot.subsystems.DriveSubsystem;


// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class SimpleShootAuto extends SequentialCommandGroup {
  /**
   * Creates a new SimpleShootAuto.
   * 
   * @param DriveSubsystem
   * 
   */
  int encoder = 50;
  

  public SimpleShootAuto(DriveSubsystem m_driveCommand) {
    addCommands(
      new StartEndCommand(
      () -> m_driveCommand.driveCartesian(0, -0.40, 0),
      () -> m_driveCommand.driveCartesian(0, 0 ,0), m_driveCommand)
      .beforeStarting(m_driveCommand::resetEncoders)
      .withInterrupt(
     // () -> Timer.getMatchTime() > 8 || Timer.getMatchTime() < 15 ));
         () -> m_driveCommand.getEncoderOneAverage() >= -25 ));
  }
}
