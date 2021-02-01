/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.revrobotics.ColorMatch;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.subsystems.ColorWheelPID;
import frc.robot.subsystems.FlywheelSubsystem;
import frc.robot.subsystems.HoodPID;
import frc.robot.subsystems.TurretPID;
import frc.robot.subsystems.Vision;
import frc.robot.subsystems.VisionTrackingSubsystem;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  private Command m_autonomousCommand;

  //  public UsbCamera Cam = new UsbCamera("front", 0);
  //protected	VideoSource Cam = new VideoSource(0);
  
  public RobotContainer m_robotContainer;
  public final ColorMatch colorMatcher = new ColorMatch();

  public FlywheelSubsystem m_fSubsystem = new FlywheelSubsystem();

  public Vision m_vision;

  public final VisionTrackingSubsystem visionTrackingSubsystem = new VisionTrackingSubsystem();
  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  @Override
  public void robotInit() {
    // Instantiate our RobotContainer.  This will perform all our button bindings, and put our
    // autonomous chooser on the dashboard.
  
    m_vision = new Vision();
    m_robotContainer = new RobotContainer();
    ColorWheelPID.colorWheel.getSensorCollection().setQuadraturePosition(0, 10);
    TurretPID.turret.getSensorCollection().setQuadraturePosition(0, 10);
  }

  /**
   * This function is called every robot packet, no matter the mode. Use this for items like
   * diagnostics that you want ran during disabled, autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before
   * LiveWindow and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
    // Runs the Scheduler.  This is responsible for polling buttons, adding newly-scheduled
    // commands, running already-scheduled commands, removing finished or interrupted commands,
    // and running subsystem periodic() methods.  This must be called from the robot's periodic
    // block in order for anything in the Command-based framework to work.
    
    
    CommandScheduler.getInstance().run();
  }

  /**
   * This function is called once each time the robot enters Disabled mode.
   */
  @Override
  public void disabledInit() {
    ColorWheelPID.colorWheel.getSensorCollection().setQuadraturePosition(0, 10);
  }

  @Override
  public void disabledPeriodic() {
    
  }

  /**
   * This autonomous runs the autonomous command selected by your {@link RobotContainer} class.
   */
  @Override
  public void autonomousInit() {

    m_autonomousCommand = m_robotContainer.getAutonomouCommand();

    // schedule the autonomous command (example)
    if (m_autonomousCommand != null) {
      m_autonomousCommand.schedule();
    }
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
 
  }

  @Override
  public void teleopInit() {
    // This makes sure that the autonomous stops running when
    // teleop starts running. If you want the autonomous to
    // continue until interrupted by another command, remove
    // this line or comment it out.
    
/*    CameraServer.getInstance().startAutomaticCapture();
CameraServer.getInstance().startAutomaticCapture(Cam);

NetworkTableInstance.getDefault().getTable(“limelight”).getEntry(“stream”).setNumber(2);
*/


    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }
  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {

    SmartDashboard.putNumber("Hood Encoder Value", HoodPID.hoodMotor.getSelectedSensorPosition());
  // double horizontaloff = m_robotContainer.m_visionTrackingSubsystem.getHorizontal();
    SmartDashboard.putNumber("TurretEncoder", TurretPID.turret.getSelectedSensorPosition());
    if (ColorWheelPID.colorWheelPID.atSetpoint() && m_robotContainer.m_colorWheelPID.isEnabled()  && 
    (m_robotContainer.m_colorWheelPID.getPosition() <= m_robotContainer.m_colorWheelPID.getSetpoint() + m_robotContainer.m_colorWheelPID.getTolerance() &&
    m_robotContainer.m_colorWheelPID.getPosition() >= m_robotContainer.m_colorWheelPID.getSetpoint() - m_robotContainer.m_colorWheelPID.getTolerance() ) ){
      
      //^^^ checks if its within tolerance and tries to disable the bot *DOESNT WORK*
      m_robotContainer.m_colorWheelPID.disable(); // .disableContinuousInput()
      // new InstantCommand(ColorWheelPID.colorWheelPID::disable, ColorWheelPID.colorWheelPID);
    }
    
    SmartDashboard.putBoolean("turret PId enabled", m_robotContainer.Pidturretenabled);


    SmartDashboard.putNumber("Flywheel Output", m_fSubsystem.getSpeed());
    


    double tx = TurretPID.vturret.light.getTX();
    SmartDashboard.putNumber("getTX", tx);
    
    SmartDashboard.putBoolean("Is at required speed", m_robotContainer.m_turretPID.getSpeed() <= 0.1);
    
    SmartDashboard.putBoolean("atSetpoint", (tx <= m_robotContainer.m_turretPID.getSetpoint() + m_robotContainer.m_turretPID.getTolerance() &&
    tx >= m_robotContainer.m_turretPID.getSetpoint() - m_robotContainer.m_turretPID.getTolerance()));
  
  
  
    
if ((tx <= m_robotContainer.m_turretPID.getSetpoint() + m_robotContainer.m_turretPID.getTolerance() &&
tx >= m_robotContainer.m_turretPID.getSetpoint() - m_robotContainer.m_turretPID.getTolerance() )
&& m_robotContainer.m_turretPID.isEnabled() &&  m_robotContainer.m_turretPID.getOutput() <= 0.0005 ){

//-------------------------------------NEEDS TO HIT 0 // USE THE FLAG IN CONTAINER "Pidturretenabled" TO USE CONTREOLLS
m_robotContainer.Pidturretenabled = false;


System.out.println("AAAAHH");
//^^^ screams if its within tolerance and tries to disable the bot *DOESNT WORK*

m_robotContainer.m_turretPID.turretPID.reset();


m_robotContainer.m_turretPID.disable(); // .disableContinuousInput()

// new InstantCommand(ColorWheelPID.colorWheelPID::disable, ColorWheelPID.colorWheelPID);
}




if ( visionTrackingSubsystem.validTarget()) {
  visionTrackingSubsystem.stop();
}
else
{
  visionTrackingSubsystem.moveForward(.30);
}




  }
  
  @Override
  public void testInit() {
    // Cancels all running commands at the start of test mode.
    CommandScheduler.getInstance().cancelAll();
  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
  }
}
