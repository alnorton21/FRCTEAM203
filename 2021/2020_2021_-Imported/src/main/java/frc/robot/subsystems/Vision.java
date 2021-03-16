/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
/**
 * Add your docs here.
 */

public class Vision {

    public Vision(){
      // DRIVER CAMERA FOR SEEING THE FIELD NORMALLY!!!---------------------------
     
     //usb camera
     
      UsbCamera cameraOne = CameraServer.getInstance().startAutomaticCapture();
      cameraOne.setResolution(64,64);
      cameraOne.setBrightness(60);
      

      // UsbCamera cameraTwo = CameraServer.getInstance().startAutomaticCapture();
      // cameraTwo.setResolution(320,240);
      


      //SMARTDASHBOARD TAB
      Shuffleboard.getTab("Vision Tracking");
        SmartDashboard.getEntry("vision/orders");
        
        SmartDashboard.getNumber("AreaMin", 0);
        SmartDashboard.getNumber("WidthMin", 0);
        SmartDashboard.getNumber("WidthMax", 0);
        SmartDashboard.getNumber("HeightMin", 0);
        SmartDashboard.getNumber("HeightMax", 0);
        SmartDashboard.getNumber("HueMin", 0);
        SmartDashboard.getNumber("HueMax", 0);
        SmartDashboard.getNumber("SatMin", 0);
        SmartDashboard.getNumber("SatMax", 0);

      }
      }






