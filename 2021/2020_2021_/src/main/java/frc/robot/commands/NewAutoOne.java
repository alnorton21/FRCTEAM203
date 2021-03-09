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

public class NewAutoOne extends SequentialCommandGroup {
  /**
   * Creates a new ComplexAuto.
   * 
   * @param DriveSubsystem
   * @param FlywheelSubsystem
   * 
   */
  double quarterTurn = 0.1; //45 degrees //0.5
  double startTime = 0;

  public NewAutoOne(DriveSubsystem m_driveCommand) {
    addCommands(

    new StartEndCommand(// START - Drive forward at the start of the command
      () -> m_driveCommand.driveCartesian(0, 0.3, 0),
      // END - Stop driving at the end of the command
      () -> m_driveCommand.resetEncoders(),
      // REQUIREMENTS - Requires the drive subsystem
      m_driveCommand)
      // Reset the drive encoders before starting
      .beforeStarting(m_driveCommand::resetEncoders)
      // End the command when the robot's driven distance exceeds the desired value
      .withInterrupt(
          () -> m_driveCommand.getEncoderOneAverage() >= 5),
          
  new StartEndCommand(// START - turn 1
  () -> m_driveCommand.driveCartesian(0, 0, -0.3),
  // END - Stop driving at the end of the command
  () -> m_driveCommand.driveCartesian(0, 0, 0),
  // REQUIREMENTS - Requires the drive subsystem
  m_driveCommand)
  // Reset the drive encoders before starting
  .beforeStarting(m_driveCommand::resetEncoders)
  // End the command when the robot's driven distance exceeds the desired value
  .withInterrupt(
      () -> Math.abs(m_driveCommand.getEncoderforAngle()) >= quarterTurn),

new StartEndCommand(// START - Drive forward 2
() -> m_driveCommand.driveCartesian(0, 0.1, 0),
// END - Stop driving at the end of the command
() -> m_driveCommand.driveCartesian(0, 0, 0),
// REQUIREMENTS - Requires the drive subsystem
m_driveCommand)
// Reset the drive encoders before starting
.beforeStarting(m_driveCommand::resetEncoders)
// End the command when the robot's driven distance exceeds the desired value
.withInterrupt(
    () -> m_driveCommand.getEncoderOneAverage() >= 2),

      new StartEndCommand(// turn to face forward / turn right 2
  () -> m_driveCommand.driveCartesian(0, 0, 0.3),
  // END - Stop driving at the end of the command
  () -> m_driveCommand.driveCartesian(0, 0, 0),
  // REQUIREMENTS - Requires the drive subsystem
  m_driveCommand)
  // Reset the drive encoders before starting
  .beforeStarting(m_driveCommand::resetEncoders)
  // End the command when the robot's driven distance exceeds the desired value
  .withInterrupt(
      () -> m_driveCommand.getEncoderTwoAverage() <= -quarterTurn),
      
      new StartEndCommand(// START - Drive forward 3
  () -> m_driveCommand.driveCartesian(0, 0.3, 0),
  // END - Stop driving at the end of the command
  () -> m_driveCommand.driveCartesian(0, 0, 0),
  // REQUIREMENTS - Requires the drive subsystem
  m_driveCommand)
  // Reset the drive encoders before starting
  .beforeStarting(m_driveCommand::resetEncoders)
  // End the command when the robot's driven distance exceeds the desired value
  .withInterrupt(
      () -> m_driveCommand.getEncoderTwoAverage() >= 12),

      new StartEndCommand(// START - Drive forward at the start of the command
  () -> m_driveCommand.driveCartesian(0, 0, 0.3),
  // END - Stop driving at the end of the command
  () -> m_driveCommand.driveCartesian(0, 0, 0),
  // REQUIREMENTS - Requires the drive subsystem
  m_driveCommand)
  // Reset the drive encoders before starting
  .beforeStarting(m_driveCommand::resetEncoders)
  // End the command when the robot's driven distance exceeds the desired value
  .withInterrupt(
      () -> m_driveCommand.getEncoderTwoAverage() <= -quarterTurn),      


      new StartEndCommand(// START - Drive forward at the start of the command
  () -> m_driveCommand.driveCartesian(0, 0.3, 0),
  // END - Stop driving at the end of the command
  () -> m_driveCommand.driveCartesian(0, 0, 0),
  // REQUIREMENTS - Requires the drive subsystem
  m_driveCommand)
  // Reset the drive encoders before starting
  .beforeStarting(m_driveCommand::resetEncoders)
  // End the command when the robot's driven distance exceeds the desired value
  .withInterrupt(
      () -> m_driveCommand.getEncoderTwoAverage() >= 2)
      
      /*
      new StartEndCommand(// START - Drive forward at the start of the command
  () -> m_driveCommand.driveCartesian(0, 0, 0.3),
  // END - Stop driving at the end of the command
  () -> m_driveCommand.driveCartesian(0, 0, 0),
  // REQUIREMENTS - Requires the drive subsystem
  m_driveCommand)
  // Reset the drive encoders before starting
  .beforeStarting(m_driveCommand::resetEncoders)
  // End the command when the robot's driven distance exceeds the desired value
  .withInterrupt(
      () -> m_driveCommand.getEncoderTwoAverage() <= -quarterTurn)/*,

        new StartEndCommand(// START - Drive forward at the start of the command
    () -> m_driveCommand.driveCartesian(0, 0.3, 0),
    // END - Stop driving at the end of the command
    () -> m_driveCommand.driveCartesian(0, 0, 0),
    // REQUIREMENTS - Requires the drive subsystem
    m_driveCommand)
    // Reset the drive encoders before starting
    .beforeStarting(m_driveCommand::resetEncoders)
    // End the command when the robot's driven distance exceeds the desired value
    .withInterrupt(
        () -> m_driveCommand.getEncoderOneAverage() >= 10),
              
        new StartEndCommand(// START - Drive forward at the start of the command
    () -> m_driveCommand.driveCartesian(0, 0.3, 0),
    // END - Stop driving at the end of the command
    () -> m_driveCommand.driveCartesian(0, 0, 0),
    // REQUIREMENTS - Requires the drive subsystem
    m_driveCommand)
    // Reset the drive encoders before starting
    .beforeStarting(m_driveCommand::resetEncoders)
    // End the command when the robot's driven distance exceeds the desired value
    .withInterrupt(
        () -> m_driveCommand.getEncoderOneAverage() >= 10)
        
    
    );    //just use comma
/*
new StartEndCommand(// START - Drive forward at the start of the command
      () -> m_driveCommand.driveCartesian(-0.1, -0.1, 0),
      // END - Stop driving at the end of the command
      () -> m_driveCommand.driveCartesian(0, 0, 0),
      // REQUIREMENTS - Requires the drive subsystem
      m_driveCommand)
      // Reset the drive encoders before starting
      .beforeStarting(m_driveCommand::resetEncoders)
      // End the command when the robot's driven distance exceeds the desired value
      .withInterrupt(
          () -> m_driveCommand.getEncoderOneAverage() <= 50)*/);
    
    // Add your commands in the super() call, e.g.
    // super(new FooCommand(), new BarCommand());
    //super();
  }
}
