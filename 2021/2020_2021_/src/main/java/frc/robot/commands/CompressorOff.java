// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.FlywheelSubsystem;
import frc.robot.subsystems.Pnumatics;

public class CompressorOff extends CommandBase {
  /** Creates a new FlywheelOn. */
  private final Pnumatics m_pnumatics;

  public CompressorOff(Pnumatics pnumatics) {
    m_pnumatics = pnumatics; 
  }

  @Override
  public void execute() {
    m_pnumatics.disableCompressor();
  }

  @Override
  public boolean isFinished() {
    return true;
  }
}
