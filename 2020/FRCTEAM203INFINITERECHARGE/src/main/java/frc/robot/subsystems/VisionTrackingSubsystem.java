/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.LimeLightLibrary.limelight;
import frc.robot.models.CamTran;

public class VisionTrackingSubsystem extends SubsystemBase {
  private static final double[] DEF_DOUB_ARR = {0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
  private boolean validTarget;
  private double horizOffset;
  private double vertOffset;
  private double targetArea;
  private double rotation;
  private double latency; //in milliseconds
  private double shortSideLength; //shortes side of fitted bouding box(pixels)
  private double longSideLength; //longest side of fitted bounding box(pixels)
  private double horizSideLength; //Horizontal sidelength of the rough bounding box (0 - 320 pixels)
  private double vertSideLength; //Vertical sidelength of the rough bounding box (0 - 320 pixels)
  private double distance;
  private int activePipeline; //0...9
  private CamTran camTran; //3D position calculations

  public limelight light;
  private int flagCameraSwitch;

  //Distance Variables
  private double a1;
  private double a2;
  private double h1;
  private double h2;
  private double atot;


  
  private static final WPI_TalonSRX motor = new WPI_TalonSRX(6);
  /**
   * Creates a new VisionTrackingSubsystem.
   */
  public VisionTrackingSubsystem () {
    light = new limelight();
    validTarget = false;
    horizOffset = 0;
    vertOffset = 0;
    targetArea = 0;
    rotation = 0;
    latency = 0;
    shortSideLength = 0;
    longSideLength = 0;
    horizSideLength = 0;
    vertSideLength = 0;
    distance = 0;
    activePipeline = 0;
    camTran = new CamTran(0, 0, 0, 0, 0, 0);
    flagCameraSwitch = 1;
  }

  /**
   * Set the variables with the current information from LimeLight
   */
  public void setInstanceVariables() {
    //TODO: Verify these are the correct data types. 
    //LimeLight documentation does not specify the retuned data types, so there might need to be
    //a couple debug sessions with the LimeLight server in order to see the specific data type returned
    //from the getEntry().get.. command.
    try {
      // validTarget = instance.getEntry("tv").getBoolean(false);
      // horizOffset = instance.getEntry("tx").getDouble(0);
      // vertOffset = instance.getEntry("ty").getDouble(0);
      // targetArea = instance.getEntry("ta").getDouble(0);
      // rotation = instance.getEntry("ts").getDouble(0);
      // latency = instance.getEntry("tl").getDouble(0);
      // shortSideLength = instance.getEntry("tshort").getDouble(0);
      // longSideLength = instance.getEntry("tlong").getDouble(0);
      // horizSideLength = instance.getEntry("thor").getDouble(0);
      // vertSideLength = instance.getEntry("tvert").getDouble(0);
      // activePipeline = (int) instance.getEntry("getpipe").getDouble(0);
      // camTran = new CamTran(instance.getEntry("camtran").getDoubleArray(DEF_DOUB_ARR));
      horizOffset = light.getTX();
      validTarget = light.getTV() == 1;
      vertOffset = light.getTY();
      targetArea = light.getTA();
      distance = light.getDist(vertOffset, 40, 0);
      

    } catch (Exception e) {
      SmartDashboard.putString("VisionTrackingSystem", "EXCEPTION OCCURRED: " + e.getMessage());
    }
  }

  
  public boolean validTarget () {
    return validTarget;
  }

  public void moveForward (double speed){
    motor.set(speed);
  }

  public void moveBackward (double speed){
    motor.set(-speed);
  }



  public void stop (){
    motor.set(0);
  }

  public double getHorizontal(){
    return horizOffset;
  }
  public void changeVisionState(){
    if (flagCameraSwitch == 1){
      light.setCAMMode(0);
      light.setLEDMode(0);
      SmartDashboard.putString("Limelight Vision", "Vision");
      flagCameraSwitch = -1;
    }
    else{
      light.setCAMMode(1);
      light.setLEDMode(1);
      SmartDashboard.putString("Limelight Vision", "Driver");
      flagCameraSwitch = 1;
    }
  }

  @Override
  public void periodic() {
    setInstanceVariables();
    SmartDashboard.putNumber("limelight/HORIZ_OFFSET", horizOffset);
    SmartDashboard.putNumber("limelight/VERT_OFFSET", vertOffset);
    SmartDashboard.putNumber("limelight/DISTANCE", distance);
    SmartDashboard.putBoolean("limelight/VALID_TARGET", validTarget);
  }

}
