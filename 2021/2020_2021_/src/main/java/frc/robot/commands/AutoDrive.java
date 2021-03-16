package frc.robot.commands;

import frc.robot.subsystems.DriveSubsystem;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.StartEndCommand;

public class AutoDrive extends CommandBase {
  // The subsystem the command runs on
  private final DriveSubsystem m_drive;
  private final DoubleSupplier m_ySpeed;
  private final DoubleSupplier m_xSpeed;
  private final DoubleSupplier m_zRotation;
  //private final HatchSubsystem m_hatchSubsystem;

  public AutoDrive(DriveSubsystem subsystem, DoubleSupplier ySpeed, 
                   DoubleSupplier xSpeed, DoubleSupplier zRotation){
    m_drive = subsystem;
    m_ySpeed = ySpeed;
    m_xSpeed = xSpeed;
    m_zRotation = zRotation;
    addRequirements(m_driveSubsystem);
  }
  /*
  @Override
  public void initialize() {

  }
  */
  
  @Override
  public void execute() {
    m_drive.drive.driveCartesian(ySpeed, xSpeed, zRotation);
  }

  /*
  @Override
  public boolean isFinished() {
    return False;
  }
  */
}