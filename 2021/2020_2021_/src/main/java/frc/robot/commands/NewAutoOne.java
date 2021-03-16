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
import frc.robot.subsystems.NavXTest;


// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html

public class NewAutoOne extends SequentialCommandGroup {
  /**
   * Creates a new ComplexAuto.
   * 
   * @param DriveSubsystem
   * @param NavXTest
   * 
   */
  double quarterTurn = 45; //45 degrees //0.5
  double startTime = 0;
  int ignore = 0; //this is to bypass deadzone

  double rotSpeed = 0.05;

  public NewAutoOne(DriveSubsystem m_driveCommand, NavXTest m_navX) {

}

  public void Adrive(DriveSubsystem m_driveSubsystem, double ySpeed, double xSpeed,
                     double zRotation, Sensors sensorType, double sensorValue){
    /*
    Autonomous drive command for mechanum drive
    
    Parameters:
        m_driveSubsytem - Drive subsystem
        ySpeed -        North/South movement of robot
        xSpeed -        East/West movement of robot
        zRotation -     Turning motion of robot
        sensorValue -   Sensor value used to interrupt the robot's curent movement.
        sensorType -    Type of sensor used to interrupt the robot's current movement.
                        Use Sensors enumerators for this parameter
    Returns:
        None
    */
    if (sensorType == Sensors.ENCODER){
        
        new StartEndCommand(// START - Drive forward at the start of the command
        () -> m_driveSubsystem.driveCartesian(ySpeed, xSpeed, encoderAvg),
        // END - Stop driving at the end of the command
        () -> m_driveSubsystem.resetEncoders(),
        // REQUIREMENTS - Requires the drive subsystem
        m_driveCommand)
        // Reset the drive encoders before starting
        .beforeStarting(m_driveCommand::resetEncoders)
        // End the command when the robot's driven distance exceeds the desired value
        .withInterrupt(
        () -> m_driveSubsystem.getEncoderOneAverage() >= encoderAvg);
    }

    else if (sensorType == Sensors.GYRO_LEFT){
        new StartEndCommand(// START - Drive forward at the start of the command
        () -> m_driveSubsystem.driveCartesian(ySpeed, xSpeed, encoderAvg),
        // END - Stop driving at the end of the command
        () -> m_driveSubsystem.resetEncoders(),
        // REQUIREMENTS - Requires the drive subsystem
        m_driveCommand)
        // Reset the drive encoders before starting
        .beforeStarting(m_driveCommand::resetEncoders)
        // End the command when the robot's driven distance exceeds the desired value
        .withInterrupt(
        () -> m_driveSubsystem.getEncoderforAngle() >= encoderAvg);
    }

    else if (sensorType == Sensors.GYRO_RIGHT){
        new StartEndCommand(// START - Drive forward at the start of the command
        () -> m_driveSubsystem.driveCartesian(ySpeed, xSpeed, encoderAvg),
        // END - Stop driving at the end of the command
        () -> m_driveSubsystem.resetEncoders(),
        // REQUIREMENTS - Requires the drive subsystem
        m_driveCommand)
        // Reset the drive encoders before starting
        .beforeStarting(m_driveCommand::resetEncoders)
        // End the command when the robot's driven distance exceeds the desired value
        .withInterrupt(
        () -> m_driveSubsystem.getEncoderforAngle() <= encoderAvg);
    }
  }

  public NewAutoOne(DriveSubsystem m_driveCommand) {
    addCommands(
        Adrive(m_driveCommand, 0, 0.3, 0, Sensor.ENCODER, 5),
        Adrive(m_driveCommand, 0, 0, -0.3, Sensor.GYRO_LEFT, quarterTurn),
    /*
    new StartEndCommand(// START - Drive forward at the start of the command
      () -> m_driveCommand.driveCartesian(0, 0.3, 0,ignore),
      // END - Stop driving at the end of the command
      () -> m_driveCommand.resetEncoders(),
      // REQUIREMENTS - Requires the drive subsystem
      m_driveCommand)
      // Reset the drive encoders before starting
      .beforeStarting(m_driveCommand::resetEncoders)
      .beforeStarting(m_navX::reset)
      // End the command when the robot's driven distance exceeds the desired value
      .withInterrupt(
          () -> m_driveCommand.getEncoderOneAverage() >= 4.5),
          
  new StartEndCommand(// START - turn 1
  () -> m_driveCommand.driveCartesian(0, 0, -rotSpeed,ignore),
  // END - Stop driving at the end of the command
  () -> m_driveCommand.driveCartesian(0, 0, 0),
  // REQUIREMENTS - Requires the drive subsystem
  m_driveCommand)
  // Reset the drive encoders before starting
  .beforeStarting(m_driveCommand::resetEncoders)
  // End the command when the robot's driven distance exceeds the desired value
  .withInterrupt(
      () -> m_navX.getYaw() <= -45),
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
    */


new StartEndCommand(// START - Drive forward 2
() -> m_driveCommand.driveCartesian(0, 0.1, 0,ignore),
// END - Stop driving at the end of the command
() -> m_driveCommand.driveCartesian(0, 0, 0),
// REQUIREMENTS - Requires the drive subsystem
m_driveCommand)
// Reset the drive encoders before starting
.beforeStarting(m_driveCommand::resetEncoders)
// End the command when the robot's driven distance exceeds the desired value
.withInterrupt(
    () -> m_driveCommand.getEncoderOneAverage() >= 7),

      new StartEndCommand(// turn to face forward / turn right 2
  () -> m_driveCommand.driveCartesian(0, 0, rotSpeed,ignore),
  // END - Stop driving at the end of the command
  () -> m_driveCommand.driveCartesian(0, 0, 0),
  // REQUIREMENTS - Requires the drive subsystem
  m_driveCommand)
  // Reset the drive encoders before starting
  .beforeStarting(m_driveCommand::resetEncoders)
  // End the command when the robot's driven distance exceeds the desired value
  .withInterrupt(
      () -> m_navX.getYaw() >= -2),
      
      new StartEndCommand(// START - Drive forward 3
  () -> m_driveCommand.driveCartesian(0, 0.3, 0,ignore),
  // END - Stop driving at the end of the command
  () -> m_driveCommand.driveCartesian(0, 0, 0),
  // REQUIREMENTS - Requires the drive subsystem
  m_driveCommand)
  // Reset the drive encoders before starting
  .beforeStarting(m_driveCommand::resetEncoders)
  // End the command when the robot's driven distance exceeds the desired value
  .withInterrupt(
      () -> m_driveCommand.getEncoderTwoAverage() >= 9),

      new StartEndCommand(// turn to face forward / turn right 2
  () -> m_driveCommand.driveCartesian(0, 0, rotSpeed,ignore),
  // END - Stop driving at the end of the command
  () -> m_driveCommand.driveCartesian(0, 0, 0),
  // REQUIREMENTS - Requires the drive subsystem
  m_driveCommand)
  // Reset the drive encoders before starting
  .beforeStarting(m_driveCommand::resetEncoders)
  // End the command when the robot's driven distance exceeds the desired value
  .withInterrupt(
      () -> m_navX.getYaw() >= 45),    


      new StartEndCommand(// START - Drive forward at the start of the command
  () -> m_driveCommand.driveCartesian(0, 0.3, 0,ignore),
  // END - Stop driving at the end of the command
  () -> m_driveCommand.driveCartesian(0, 0, 0),
  // REQUIREMENTS - Requires the drive subsystem
  m_driveCommand)
  // Reset the drive encoders before starting
  .beforeStarting(m_driveCommand::resetEncoders)
  // End the command when the robot's driven distance exceeds the desired value
  .withInterrupt(
      () -> m_driveCommand.getEncoderOneAverage() >= 6),

      new StartEndCommand(// turn to face forward / turn right 2
  () -> m_driveCommand.driveCartesian(0, 0, -rotSpeed,ignore),
  // END - Stop driving at the end of the command
  () -> m_driveCommand.driveCartesian(0, 0, 0),
  // REQUIREMENTS - Requires the drive subsystem
  m_driveCommand)
  // Reset the drive encoders before starting
  .beforeStarting(m_driveCommand::resetEncoders)
  // End the command when the robot's driven distance exceeds the desired value
  .withInterrupt(
      () -> m_navX.getYaw() <= 0),    

  new StartEndCommand(// START - Drive forward at the start of the command
  () -> m_driveCommand.driveCartesian(0, 0.3, 0,ignore),
  // END - Stop driving at the end of the command
  () -> m_driveCommand.driveCartesian(0, 0, 0),
  // REQUIREMENTS - Requires the drive subsystem
  m_driveCommand)
  // Reset the drive encoders before starting
  .beforeStarting(m_driveCommand::resetEncoders)
  // End the command when the robot's driven distance exceeds the desired value
  .withInterrupt(
      () -> m_driveCommand.getEncoderOneAverage() >= 3),

  new StartEndCommand(// turn to face forward / turn right 2
  () -> m_driveCommand.driveCartesian(0, 0, -rotSpeed,ignore),
  // END - Stop driving at the end of the command
  () -> m_driveCommand.driveCartesian(0, 0, 0),
  // REQUIREMENTS - Requires the drive subsystem
  m_driveCommand)
  // Reset the drive encoders before starting
  .beforeStarting(m_driveCommand::resetEncoders)
  // End the command when the robot's driven distance exceeds the desired value
  .withInterrupt(
      () -> m_navX.getYaw() <= -90),   

  new StartEndCommand(// START - Drive forward at the start of the command
  () -> m_driveCommand.driveCartesian(0, 0.3, 0,ignore),
  // END - Stop driving at the end of the command
  () -> m_driveCommand.driveCartesian(0, 0, 0),
  // REQUIREMENTS - Requires the drive subsystem
  m_driveCommand)
  // Reset the drive encoders before starting
  .beforeStarting(m_driveCommand::resetEncoders)
  // End the command when the robot's driven distance exceeds the desired value
  .withInterrupt(
      () -> m_driveCommand.getEncoderOneAverage() >= 4.2),

  new StartEndCommand(// turn to face forward / turn right 2
  () -> m_driveCommand.driveCartesian(0, 0, -rotSpeed,ignore),
  // END - Stop driving at the end of the command
  () -> m_driveCommand.driveCartesian(0, 0, 0),
  // REQUIREMENTS - Requires the drive subsystem
  m_driveCommand)
  // Reset the drive encoders before starting
  .beforeStarting(m_driveCommand::resetEncoders)
  // End the command when the robot's driven distance exceeds the desired value
  .withInterrupt(
      () -> m_navX.getYaw() <= -179), 
 
      new StartEndCommand(// START - Drive forward at the start of the command
  () -> m_driveCommand.driveCartesian(0, 0.3, 0,ignore),
  // END - Stop driving at the end of the command
  () -> m_driveCommand.driveCartesian(0, 0, 0),
  // REQUIREMENTS - Requires the drive subsystem
  m_driveCommand)
  // Reset the drive encoders before starting
  .beforeStarting(m_driveCommand::resetEncoders)
  // End the command when the robot's driven distance exceeds the desired value
  .withInterrupt(
      () -> m_driveCommand.getEncoderOneAverage() >= 3),    

 new StartEndCommand(// turn to face forward / turn right 2
  () -> m_driveCommand.driveCartesian(0, 0, -rotSpeed,ignore),
  // END - Stop driving at the end of the command
  () -> m_driveCommand.driveCartesian(0, 0, 0),
  // REQUIREMENTS - Requires the drive subsystem
  m_driveCommand)
  // Reset the drive encoders before starting
  .beforeStarting(m_driveCommand::resetEncoders)
  // End the command when the robot's driven distance exceeds the desired value
  .withInterrupt(
      () -> m_navX.getYaw() <= -225),         
      
      new StartEndCommand(// START - Drive forward at the start of the command
  () -> m_driveCommand.driveCartesian(0, 0.3, 0,ignore),
  // END - Stop driving at the end of the command
  () -> m_driveCommand.driveCartesian(0, 0, 0),
  // REQUIREMENTS - Requires the drive subsystem
  m_driveCommand)
  // Reset the drive encoders before starting
  .beforeStarting(m_driveCommand::resetEncoders)
  // End the command when the robot's driven distance exceeds the desired value
  .withInterrupt(
      () -> m_driveCommand.getEncoderOneAverage() >= 6),    
      
 new StartEndCommand(// turn to face forward / turn right 2
  () -> m_driveCommand.driveCartesian(0, 0, rotSpeed,ignore),
  // END - Stop driving at the end of the command
  () -> m_driveCommand.driveCartesian(0, 0, 0),
  // REQUIREMENTS - Requires the drive subsystem
  m_driveCommand)
  // Reset the drive encoders before starting
  .beforeStarting(m_driveCommand::resetEncoders)
  // End the command when the robot's driven distance exceeds the desired value
  .withInterrupt(
      () -> m_navX.getYaw() >= -180),          
      
      new StartEndCommand(// START - Drive forward at the start of the command
  () -> m_driveCommand.driveCartesian(0, 0.3, 0,ignore),
  // END - Stop driving at the end of the command
  () -> m_driveCommand.driveCartesian(0, 0, 0),
  // REQUIREMENTS - Requires the drive subsystem
  m_driveCommand)
  // Reset the drive encoders before starting
  .beforeStarting(m_driveCommand::resetEncoders)
  // End the command when the robot's driven distance exceeds the desired value
  .withInterrupt(
      () -> m_driveCommand.getEncoderOneAverage() >= 9),    

 new StartEndCommand(// turn to face forward / turn right 2
  () -> m_driveCommand.driveCartesian(0, 0, rotSpeed,ignore),
  // END - Stop driving at the end of the command
  () -> m_driveCommand.driveCartesian(0, 0, 0),
  // REQUIREMENTS - Requires the drive subsystem
  m_driveCommand)
  // Reset the drive encoders before starting
  .beforeStarting(m_driveCommand::resetEncoders)
  // End the command when the robot's driven distance exceeds the desired value
  .withInterrupt(
      () -> m_navX.getYaw() >= -135),          

      new StartEndCommand(// START - Drive forward at the start of the command
  () -> m_driveCommand.driveCartesian(0, 0.3, 0,ignore),
  // END - Stop driving at the end of the command
  () -> m_driveCommand.driveCartesian(0, 0, 0),
  // REQUIREMENTS - Requires the drive subsystem
  m_driveCommand)
  // Reset the drive encoders before starting
  .beforeStarting(m_driveCommand::resetEncoders)
  // End the command when the robot's driven distance exceeds the desired value
  .withInterrupt(
      () -> m_driveCommand.getEncoderOneAverage() >= 6),    
      
 new StartEndCommand(// turn to face forward / turn right 2
  () -> m_driveCommand.driveCartesian(0, 0, -rotSpeed,ignore),
  // END - Stop driving at the end of the command
  () -> m_driveCommand.driveCartesian(0, 0, 0),
  // REQUIREMENTS - Requires the drive subsystem
  m_driveCommand)
  // Reset the drive encoders before starting
  .beforeStarting(m_driveCommand::resetEncoders)
  // End the command when the robot's driven distance exceeds the desired value
  .withInterrupt(
      () -> m_navX.getYaw() <= -180),          

      new StartEndCommand(// START - Drive forward at the start of the command
  () -> m_driveCommand.driveCartesian(0, 0.3, 0,ignore),
  // END - Stop driving at the end of the command
  () -> m_driveCommand.driveCartesian(0, 0, 0),
  // REQUIREMENTS - Requires the drive subsystem
  m_driveCommand)
  // Reset the drive encoders before starting
  .beforeStarting(m_driveCommand::resetEncoders)
  // End the command when the robot's driven distance exceeds the desired value
  .withInterrupt(
      () -> m_driveCommand.getEncoderOneAverage() >= 5)    


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
