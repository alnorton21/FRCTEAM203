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
import frc.robot.subsystems.FlywheelSubsystem;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html

public class ComplexAuto extends SequentialCommandGroup {
  /**
   * Creates a new ComplexAuto.
   * 
   * @param DriveSubsystem
   * @param FlywheelSubsystem
   * 
   */
  
  double startTime = 0;

  public ComplexAuto(DriveSubsystem m_driveCommand, FlywheelSubsystem m_flywheelSubsystem) {
    addCommands(
      new StartEndCommand(// START - Drive forward at the start of the command
      () -> m_driveCommand.driveCartesian(0, 0.2, 0),
      // END - Stop driving at the end of the command
      () -> m_driveCommand.driveCartesian(0, 0, 0),
      // REQUIREMENTS - Requires the drive subsystem
      m_driveCommand)
      // Reset the drive encoders before starting
      .beforeStarting(m_driveCommand::resetEncoders)
      // End the command when the robot's driven distance exceeds the desired value
      .withInterrupt(
          () -> m_driveCommand.getEncoderOneAverage() >= 50),

      new StartEndCommand(// START - Drive forward at the start of the command
      () -> m_flywheelSubsystem.forwardFlywheel(0.85),
      // END - Stop driving at the end of the command
      () -> m_flywheelSubsystem.stopFlywheel(),
      // REQUIREMENTS - Requires the drive subsystem
      m_flywheelSubsystem)
      // Reset the drive encoders before starting
      //.beforeStarting(startTime = Timer.getFPGATimestamp())
      // End the command when the robot's driven distance exceeds the desired value
      .withInterrupt(
          //() -> (Timer.getFPGATimestamp() - startTime) < 4),
            () -> Timer.getFPGATimestamp() < 8),

      new StartEndCommand(// START - Drive forward at the start of the command
      () -> m_driveCommand.driveCartesian(0, 0.2, 0),
      // END - Stop driving at the end of the command
      () -> m_driveCommand.driveCartesian(0, 0, 0),
      // REQUIREMENTS - Requires the drive subsystem
      m_driveCommand)
      // Reset the drive encoders before starting
      .beforeStarting(m_driveCommand::resetEncoders)
      // End the command when the robot's driven distance exceeds the desired value
      .withInterrupt(
          () -> m_driveCommand.getEncoderOneAverage() >= 50));

          
    // Add your commands in the super() call, e.g.
    // super(new FooCommand(), new BarCommand());
    //super();
  }
}
