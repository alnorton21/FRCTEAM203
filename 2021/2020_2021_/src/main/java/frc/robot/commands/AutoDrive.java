package frc.robot.commands;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.NavXTest;

import java.util.function.DoubleSupplier;
import edu.wpi.first.wpilibj2.command.StartEndCommand;

public class AutoDrive extends CommandBase {
  // The subsystem the command runs on
  private final DriveSubsystem m_drive;
  private final NavXTest m_navX;
  private final double m_ySpeed;
  private final double m_xSpeed;
  private final double m_zRotation;
  private final Sensors m_sensorType;
  private final double m_sensorValue;
  private final boolean m_resetGyro;
  //private final HatchSubsystem m_hatchSubsystem;

  enum Sensors{
    ENCODER, GYRO_LEFT, GYRO_RIGHT
  }

  public AutoDrive(DriveSubsystem subsystem, NavXTest navX, double ySpeed, 
                   double xSpeed, double zRotation, Sensors sensorType, double sensorValue, boolean resetGyro){
    m_drive = subsystem;
    m_navX = navX;
    m_ySpeed = ySpeed;
    m_xSpeed = xSpeed;
    m_zRotation = zRotation;
    m_sensorType = sensorType;
    m_sensorValue = sensorValue;
    m_resetGyro = resetGyro;
    addRequirements(subsystem);
  }

  
  @Override
  public void initialize() {
    m_drive.resetEncoders();
    if (m_resetGyro){
      m_navX.reset();
    }
  }
  
  
  @Override
  public void execute() {
    m_drive.driveCartesian(m_ySpeed, m_xSpeed, m_zRotation,0);
  }


  @Override
  public boolean isFinished() {
    if (m_sensorType == Sensors.ENCODER  && m_drive.getEncoderOneAverage() >= m_sensorValue){
      return true;
    }
    else if (m_sensorType == Sensors.GYRO_LEFT  && m_navX.getYaw() <= m_sensorValue){
      System.out.println(m_navX.getYaw());
      return true;
    }
    else if (m_sensorType == Sensors.GYRO_RIGHT && m_navX.getYaw() >= m_sensorValue){
      System.out.println(m_navX.getYaw());
      return true;
    }
    else{
      return false;
    }
  }
}