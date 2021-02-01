/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.drive.MecanumDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.DriveConstants;


public class DriveSubsystem extends SubsystemBase {
  /**
   * Creates a new DriveSubsystem.
   */
  private static CANSparkMax leftTwo = new CANSparkMax(DriveConstants.kleftBack,MotorType.kBrushless);
  private static CANSparkMax leftOne = new CANSparkMax(DriveConstants.kleftFront,MotorType.kBrushless);
  private static CANSparkMax rightOne = new CANSparkMax(DriveConstants.krightFront,MotorType.kBrushless);
  private static CANSparkMax rightTwo = new CANSparkMax(DriveConstants.krightBack,MotorType.kBrushless);

  private static MecanumDrive mecanumDrive = new MecanumDrive(leftOne, leftTwo, rightOne, rightTwo);
  
  public DriveSubsystem() {
    leftOne.setInverted(false);
    leftTwo.setInverted(false);
    rightOne.setInverted(false);
    rightTwo.setInverted(false);
  
  }

  public void driveCartesian (double ySpeed, double xSpeed, double zRotation){
    //0.07 vvv
    if (Math.abs(xSpeed) < 0.1){ // || Math.abs(move){ //> RobotMap.upperMovelimit) {
      xSpeed = 0; 
      
    }
    if (Math.abs(ySpeed) < 0.1){ // || Math.abs(move){//} > RobotMap.upperTurnlimit) {
      ySpeed = 0;
    
    }
    
    if (Math.abs(zRotation) < 0.1){ // || Math.abs(move){//} > RobotMap.upperTurnlimit) {
      zRotation = 0;
    
    }
    mecanumDrive.driveCartesian(ySpeed, xSpeed, zRotation);
    
  }
  public void setMaxOutput (double maxOutput){
    mecanumDrive.setMaxOutput(maxOutput);
  }
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}

