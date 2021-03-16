package frc.robot.commands;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveSubsystem;
import edu.wpi.first.wpilibj2.command.StartEndCommand;

public class AutoDrive extends CommandBase {
  // The subsystem the command runs on
  private final DriveSubsystem m_drive;
  private final double m_ySpeed;
  private final double m_xSpeed;
  private final double m_zRotation;
  private final Sensors m_sensorType;
  private final double m_sensorValue;
  //private final HatchSubsystem m_hatchSubsystem;

  enum Sensors{
    ENCODER, GYRO_LEFT, GYRO_RIGHT
  }

  public AutoDrive(DriveSubsystem subsystem, double ySpeed, 
                   double xSpeed, double zRotation, Sensors sensorType, double sensorValue){
    m_drive = subsystem;
    m_ySpeed = ySpeed;
    m_xSpeed = xSpeed;
    m_zRotation = zRotation;
    m_sensorType = sensorType;
    m_sensorValue = sensorValue;
    addRequirements(subsystem);
  }

  
  @Override
  public void initialize() {
    m_drive.resetEncoders();
  }
  
  
  @Override
  public void execute() {
    m_drive.driveCartesian(m_ySpeed, m_xSpeed, m_zRotation);
  }


  @Override
  public boolean isFinished() {
    if (m_sensorType == Sensors.ENCODER  && m_drive.getEncoderOneAverage() >= m_sensorValue){
      return true;
    }
    else if (m_sensorType == Sensors.GYRO_LEFT  && m_drive.getEncoderforAngle() >= m_sensorValue){
      return true;
    }
    else if (m_sensorType == Sensors.GYRO_RIGHT && m_drive.getEncoderforAngle() <= m_sensorValue){
      return true;
    }
    else{
      return false;
    }
  }
}