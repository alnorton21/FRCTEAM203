/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import LimeLightLibrary.limelight;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.controller.SimpleMotorFeedforward;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.PIDSubsystem;
import frc.robot.Constants.TurretConstants;

import edu.wpi.first.wpilibj2.command.PIDCommand;


public class TurretPID extends PIDSubsystem {
  /**
   * Creates a new TurretPID.
   */
  public static VisionTrackingSubsystem vturret = new VisionTrackingSubsystem();
  public static WPI_TalonSRX turret = new WPI_TalonSRX (TurretConstants.kturretOne);

  public static PIDController turretPID; 
  public static int nPIDCount = 0;
  public static int nPIDOutputCount = 0;

  public final SimpleMotorFeedforward m_turretFeedForward = new SimpleMotorFeedforward(0.0004, (12/13180));
  
  // Variables
  double steering_adjust = 0;
  double tolerance = 0.06; //0.05
  double setpoint = 0;


  
  double willBeTurretSet = 0; //gets output from turret set for if statement

  public TurretPID() {
    super(
      // The PIDController used by the subsystem
      //.0535, 0.001, 0.0001  -Jimmy 0.166, 0.001, 0.005
      //0.055, 0.0013, 0.0001   -DeAisha
      turretPID = new PIDController(0.43, 0, 0)); //p = 0.03
      getController().setTolerance(tolerance); 
      setSetpoint(setpoint);

      turretPID.disableContinuousInput();



      SmartDashboard.putNumber("kP", turretPID.getP());
      SmartDashboard.putNumber("kI", turretPID.getI());
      SmartDashboard.putNumber("kD", turretPID.getD());
      SmartDashboard.putNumber("Position",getPosition());
     }


  public void turretForward (double speed){
    turret.set(speed);
    }    


  public void turretReverse (double speed){
    turret.set(-speed);
    }
    
  public void turretStop (){
    turret.set(0);
    turret.getSelectedSensorPosition();
    }
    
  public boolean atSetpoint(){
    //return turretPID.atSetpoint();
    return m_controller.atSetpoint();
  }

  public double getPosition(){
    return turret.getSelectedSensorPosition();
  }

  public double getSetpoint(){
    return setpoint;
  }

  public double getTolerance(){
    return tolerance;
  }

  public void setkP(double kP){
    turretPID.setP(kP);
  }

  public void setkI(double kI){
    turretPID.setI(kI);
  }

  public void setkD(double kD) {
    turretPID.setD(kD);
  }

  public double getSpeed(){
    return turret.getMotorOutputPercent();
  }

  public void resetPIDValues(){
    turretPID.setP(SmartDashboard.getNumber("kP", turretPID.getP())); 
    turretPID.setI(SmartDashboard.getNumber("kI", turretPID.getI()));
    turretPID.setD(SmartDashboard.getNumber("kD", turretPID.getD()));
    turretPID.setTolerance(SmartDashboard.getNumber("Tolerance", tolerance));
    //turretPID.setSetpoint(setpoint); //SmartDashboard.getNumber("Setpoint", setpoint)
  }    

  public void resetEncoder(){
    turret.getSensorCollection().setQuadraturePosition(0, 10);
  }

@Override
public void useOutput(double output, double setpoint) {
  SmartDashboard.putNumber("PID Output Count",++nPIDOutputCount);
  SmartDashboard.putNumber("PID Output",output);
  SmartDashboard.putNumber("PID setpoint",setpoint);
  double turret_set = output + m_turretFeedForward.calculate(setpoint) ;

  willBeTurretSet = turret_set;
//  turretPID.enableContinuousInput(-320/2,320/2); // enableContinuousInput(minimumInput, maximumInput);

SmartDashboard.putNumber("PID turret_set",turret_set);
turret.set(turret_set); //when multiplied by < 1 works more often
SmartDashboard.putNumber("Turret Voltage",turret.getMotorOutputVoltage());

}

public double getOutput(){
return willBeTurretSet;
}


@Override
public double getMeasurement() {
  //SmartDashboard.putNumber("Tolerance", tolerance);
  //SmartDashboard.putNumber("Setpoint", setpoint);
 // SmartDashboard.putNumber("turretoff",vturret.getHorizontal());
//  setSetpoint(vturret.getHorizontal());

//System.out.println(SmartDashboard.getNumber("limelight/HORIZ_OFFSET", 0));
  SmartDashboard.putNumber("HOR OFF",vturret.light.getTX());
  return vturret.light.getTX();

  //turret.getSelectedSensorPosition();
}
}
