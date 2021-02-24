/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.models;
/**
 * Keep track of 3D position based on Lime Light vision table
 */
public class CamTran {
  private double x;
  private double y;
  private double z;
  private double pitch;
  private double yaw;
  private double roll;

  /**
   * Constructor for CamTran
   * @param x x coordinate
   * @param y y coordinate
   * @param z z coordinate
   * @param pitch pitch calculation
   * @param yaw yaw calculation
   * @param roll roll calculation
   */
  public CamTran (int x, int y, int z, int pitch, int yaw, int roll) {
      this.x = x;
      this.y = y;
      this.z = z;
      this.pitch = pitch;
      this.yaw = yaw;
      this.roll = roll;
  }

  public CamTran(double[] givenValue) {
    this.x = givenValue[0];
    this.y = givenValue[1];
    this.z = givenValue[2];
    this.pitch = givenValue[3];
  }

  /**
   * get x position
   * @return x coordinate
   */
  public double getX() {
      return x;
  }

  /**
   * get y position
   * @return y coordinate
   */
  public double getY() {
      return y;
  }

  /**
   * get z position
   * @return z coordinate
   */
  public double getZ() {
      return z;
  }

  /**
   * get pitch calculation
   * @return calculated pitch
   */
  public double getPitch() {
      return pitch;
  }

  /**
   * get yaw calculation
   * @return calculated yaw
   */
  public double getYaw() {
      return yaw;
  }

  /**
   * get roll calculation
   * @return calculated roll
   */
  public double getRoll() {
      return roll;
  }

  @Override
  public String toString() {
    return "[X: " + x + ", Y: " + y + ", Z: " + z + ", PITC:" + pitch + ", YAW: " + yaw + ", ROL: " + roll + "]";
  }
}

