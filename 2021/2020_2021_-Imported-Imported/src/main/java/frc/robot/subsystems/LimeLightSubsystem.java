/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
//
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;

//
public class LimeLightSubsystem extends SubsystemBase {
//
NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
NetworkTableEntry tx = table.getEntry("tx");
NetworkTableEntry ty = table.getEntry("ty");
NetworkTableEntry ta = table.getEntry("ta");

NetworkTableEntry status = table.getEntry("status");



/*   //VERY TESTY, JUST A BASIC THING I WANT TO TRY OUT

if(tx > 5){ //checking if robot is within 5 somethings of target
robot.turn(tx*0.01); //multiplies the error by 0.01 to set it to an attainable speed, means it'll slowly come to a stop
}
else if(tx < -5){ //check if in range
robot.turn(tx*0.01); //turns robot left i think until stop, if doesn't work just make em negative
}
else{
  robot.go(0);
}

*/


//read values periodically
double x = tx.getDouble(0.0);
double y = ty.getDouble(0.0);
double area = ta.getDouble(0.0);
String lloutput = status.getString("");


  public LimeLightSubsystem() {

  }

  @Override
  public void periodic() {

if(x > 0){
  lloutput = "go right";
}
else if(x < 0){
  lloutput = "go left";
}
else{
  lloutput = "literally perfectly centered";
}


  //SmartDashboard.putNumber("", value)

    SmartDashboard.putNumber("LimelightX", x);
    SmartDashboard.putNumber("LimelightY", y);
    SmartDashboard.putNumber("LimelightArea", area);

//

SmartDashboard.putNumber("/limelight/tv", area);
SmartDashboard.putNumber("/limelight/ta", area);
SmartDashboard.putNumber("/limelight/tx", area);
SmartDashboard.putNumber("/limelight/ty", area);

SmartDashboard.putString("/limelight/status", lloutput);



  }
}
