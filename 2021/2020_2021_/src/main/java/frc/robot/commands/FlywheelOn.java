// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.FlywheelSubsystem;

public class FlywheelOn extends CommandBase {
  /** Creates a new FlywheelOn. */
  private final FlywheelSubsystem m_flywheel;

  public FlywheelOn(FlywheelSubsystem flywheel) {
    m_flywheel = flywheel; 
  }

  @Override
  public void execute() {
  m_flywheel.forwardFlywheel(1);
  }

  @Override
  public boolean isFinished() {
    return true;
  }
}
