/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.EncoderType;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.drive.MecanumDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.DriveConstants;

//TODO: Change the naming of one and two to front and back with motors

public class DriveSubsystem extends SubsystemBase {
  /**
   * Creates a new DriveSubsystem.
   */
 public static CANSparkMax leftOne = new CANSparkMax(DriveConstants.kleftFront,MotorType.kBrushless);
  public static CANSparkMax leftTwo = new CANSparkMax(DriveConstants.kleftBack,MotorType.kBrushless);
  public static CANSparkMax rightOne = new CANSparkMax(DriveConstants.krightFront,MotorType.kBrushless);
  public static CANSparkMax rightTwo = new CANSparkMax(DriveConstants.krightBack,MotorType.kBrushless);

  public static MecanumDrive mecanumDrive = new MecanumDrive(leftOne, leftTwo, rightOne, rightTwo);
  
  public CANEncoder leftOneEncoder = leftOne.getEncoder();
  public CANEncoder leftTwoEncoder = leftTwo.getEncoder();
  public CANEncoder rightOneEncoder = rightOne.getEncoder();
  public CANEncoder rightTwoEncoder = rightTwo.getEncoder();

  public DriveSubsystem() {
    leftOne.setInverted(false);
    leftTwo.setInverted(false);
    rightOne.setInverted(false);
    rightTwo.setInverted(false);

    leftOne.setIdleMode(IdleMode.kBrake);
    leftTwo.setIdleMode(IdleMode.kBrake);
    rightOne.setIdleMode(IdleMode.kBrake);
    rightTwo.setIdleMode(IdleMode.kBrake);

    mecanumDrive.setSafetyEnabled(false);
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

  public void resetEncoders(){
    leftOneEncoder.setPosition(0);
    leftTwoEncoder.setPosition(0);
    rightOneEncoder.setPosition(0);
    rightTwoEncoder.setPosition(0);
    
  }

  public void setMaxOutput (double maxOutput){
    mecanumDrive.setMaxOutput(maxOutput);
  }


  public double getEncoderOneAverage(){
    double right = -1*rightOneEncoder.getPosition();
    right = right / 5.16;

    double left = leftOneEncoder.getPosition();
    left = left / 5.16;

    return (right + left) / 2;
  }

  public double getEncoderTwoAverage(){
    double right = -1*rightTwoEncoder.getPosition();
    right = right / 5.16;

    double left = leftTwoEncoder.getPosition();
    left = left / 5.16;

    return (right + left) / 2;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run

    //DOESN'T WORK FOR SOME REASON
     SmartDashboard.putNumber("Left One Position", leftOneEncoder.getPosition());
     SmartDashboard.putNumber("Left Two Position", leftTwoEncoder.getPosition());
     SmartDashboard.putNumber("Right One Position", rightOneEncoder.getPosition());
     SmartDashboard.putNumber("Right Two Position", rightTwoEncoder.getPosition());
     SmartDashboard.putNumber("Encoder One Average", getEncoderOneAverage());
     SmartDashboard.putNumber("Encoder Two Average", getEncoderTwoAverage());
  }
}

