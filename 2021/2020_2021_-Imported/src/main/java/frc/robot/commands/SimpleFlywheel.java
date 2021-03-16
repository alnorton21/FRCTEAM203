/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.FlywheelSubsystem;
import edu.wpi.first.wpilibj2.command.StartEndCommand;
import edu.wpi.first.wpilibj.Timer;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class SimpleFlywheel extends SequentialCommandGroup {
  /**
   * Creates a new SimpleFlywheel.
   */
  public SimpleFlywheel(FlywheelSubsystem m_flywheelSubsystem) {

    new StartEndCommand(
      () -> m_flywheelSubsystem.forwardFlywheel(0.85),
      () -> m_flywheelSubsystem.stopFlywheel(), m_flywheelSubsystem)
       .withInterrupt(() -> Timer.getMatchTime() < 13);
  }
}
