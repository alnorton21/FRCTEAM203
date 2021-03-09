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

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html

public class AutoSkeleton extends SequentialCommandGroup {
  /**
   * Creates a new ComplexAuto.
   * 
   * @param DriveSubsystem
   * @param FlywheelSubsystem
   * 
   */

  double startTime = 0;

  public AutoSkeleton(DriveSubsystem m_driveCommand) {
    addCommands(

    //--------------------------------------------------------------------------------------------
    //() -> m_driveCommand.driveCartesian(STRAIFE LEFT AND RIGHT, FORWARD AND BACK, ROTATE LEFT AND RIGHT),
    // ^^^ the driveCommand.driveCartesian inputs

    // vvvvvvvvvvv SAMPLE vvvvvvvvvvv

/*    new StartEndCommand(
    () -> m_driveCommand.driveCartesian(0.15, 0.1, 0), STRAIFES LEFT AT SPEED 0.15 and MOVE FORWARD 0.1 SPEED
    // END - Stop driving at the end of the command
    () -> m_driveCommand.driveCartesian(0, 0, 0), WHEN ITS INTERUPTED, DO THIS (keep it at 0s)
    // REQUIREMENTS - Requires the drive subsystem
    m_driveCommand)
    // Reset the drive encoders before starting
    .beforeStarting(m_driveCommand::resetEncoders)
    // End the command when the robot's driven distance exceeds the desired value
    .withInterrupt(
        () -> m_driveCommand.getEncoderTwoAverage() >= 4), TELLS IT TO STOP WHEN IT HITS 4 FT (should be 4 feet ask norton)
*/
//--------------------------------------------------------------------------------------------


  new StartEndCommand(// START - MOVEMENT 1
    () -> m_driveCommand.driveCartesian(0.15, 0.1, 0),
    // END - Stop driving at the end of the command
    () -> m_driveCommand.driveCartesian(0, 0, 0),
    // REQUIREMENTS - Requires the drive subsystem
    m_driveCommand)
    // Reset the drive encoders before starting
    .beforeStarting(m_driveCommand::resetEncoders)
    // End the command when the robot's driven distance exceeds the desired value
    .withInterrupt(
        () -> m_driveCommand.getEncoderTwoAverage() >= 4),

  new StartEndCommand( //MOVEMENT 2
    () -> m_driveCommand.driveCartesian(-0.1, 0.1, 0),
    // END - Stop driving at the end of the command
    () -> m_driveCommand.driveCartesian(0, 0, 0),
    // REQUIREMENTS - Requires the drive subsystem
    m_driveCommand)
    // Reset the drive encoders before starting
    .beforeStarting(m_driveCommand::resetEncoders)
    // End the command when the robot's driven distance exceeds the desired value
    .withInterrupt(
        () -> m_driveCommand.getEncoderTwoAverage() >= 10),
      
  new StartEndCommand(// MOVEMENT 3
      () -> m_driveCommand.driveCartesian(-0.15, 0.1, 0),
      // END - Stop driving at the end of the command
      () -> m_driveCommand.driveCartesian(0, 0, 0),
      // REQUIREMENTS - Requires the drive subsystem
      m_driveCommand)
      // Reset the drive encoders before starting
      .beforeStarting(m_driveCommand::resetEncoders)
      // End the command when the robot's driven distance exceeds the desired value
      .withInterrupt(
          () -> m_driveCommand.getEncoderTwoAverage() >= 4)  );



    // Add your commands in the super() call, e.g.
    // super(new FooCommand(), new BarCommand());
    //super();
  }
}
