/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.PIDSubsystem;
import frc.robot.Constants;

public class HoodPID extends PIDSubsystem {
  /**
   * Creates a new HoodPID.
   */
  public static WPI_TalonSRX hoodMotor = new WPI_TalonSRX (Constants.khood);
  public static PIDController hoodPID; 

  double tolerance = 10; //1000
  double setpoint = -800;

  public HoodPID() {
    super(
        // The PIDController used by the subsystem
        new PIDController(0.00075, 0.0001, 0));
        getController().setTolerance(tolerance); 
        setSetpoint(setpoint); 
  }

  public void hoodUp (double speed){
    hoodMotor.set(speed);
  }
  
  public void hoodDown (double speed){
    hoodMotor.set(-speed);
  }

    public void hoodStop (){
    hoodMotor.set(0);
  }

  public double getEncoderValue(){
    return hoodMotor.getSelectedSensorPosition();
  }

  public void resetEncoderValue(){
    hoodMotor.getSensorCollection().setQuadraturePosition(0, 10);
  }

  @Override
  public void useOutput(double output, double setpoint) {
    // Use the output here
    hoodMotor.set(output);
  }

  @Override
  public double getMeasurement() {
    // Return the process variable measurement here
    return hoodMotor.getSelectedSensorPosition();
  }
}
