/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

//TEST COMMIT
package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.XboxController.Button;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.StartEndCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.AutoSkeleton;
import frc.robot.commands.Barrel;
import frc.robot.commands.ComplexAuto;
import frc.robot.commands.FINALSimpleShootAuto;
import frc.robot.commands.NewAutoOne;
import frc.robot.commands.NewAutoTwo;
import frc.robot.commands.PathABlue;
import frc.robot.commands.SimpleShootAuto;
import frc.robot.subsystems.BeaverTailSubsystem;
import frc.robot.subsystems.ColorWheelPID;
import frc.robot.subsystems.ColorWheelSubsystem;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.FlywheelSubsystem;
import frc.robot.subsystems.HoodPID;
import frc.robot.subsystems.HoodSubsystem;
import frc.robot.subsystems.IndexerSubsystem;
import frc.robot.subsystems.NavXTest;
import frc.robot.subsystems.PlexiSubsystem;
import frc.robot.subsystems.Pnumatics;
import frc.robot.subsystems.TurretPID;
import frc.robot.subsystems.TurretSubsystem;
import frc.robot.commands.RevUpFlywheel;
import frc.robot.subsystems.VisionTrackingSubsystem;
/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  
public boolean Pidturretenabled = false;

  private final DriveSubsystem  m_driveCommand = new DriveSubsystem();
  private final XboxController m_driverController = new XboxController(0);
  private final XboxController m_operatorController = new XboxController(1);
  private final FlywheelSubsystem m_flywheelSubsystem = new FlywheelSubsystem();
  private final TurretSubsystem m_turretSubsystem = new TurretSubsystem();
  public final VisionTrackingSubsystem m_visionTrackingSubsystem = new VisionTrackingSubsystem();
  private final ColorWheelSubsystem m_colorWheelSubsysytem = new ColorWheelSubsystem();
  public final ColorWheelPID m_colorWheelPID = new ColorWheelPID();
  public final TurretPID m_turretPID = new TurretPID();
  public final PlexiSubsystem m_plexiSubsystem = new PlexiSubsystem();
  //public final HoodSubsystem m_hoodSubsystem = new HoodSubsystem();
  public final BeaverTailSubsystem m_beaverTailSubsystem = new BeaverTailSubsystem();
  public final IndexerSubsystem m_indexerSubsystem = new IndexerSubsystem();
  public final HoodPID m_hoodPID = new HoodPID();
  public final Pnumatics m_pnumatics = new Pnumatics();
  public final HoodSubsystem m_hoodSubsystem = new HoodSubsystem();
  public final NavXTest m_navX = new NavXTest();
  /*
  //A simple auto routine that drives forward a specified distance, and then stops
  private final Command m_simpleAuto = new StartEndCommand(
    // START - Drive forward at the start of the command
    () -> m_driveCommand.driveCartesian(0.2, 0, 0),
    // END - Stop driving at the end of the command
    () -> m_driveCommand.driveCartesian(0, 0, 0),
    // REQUIREMENTS - Requires the drive subsystem
    m_driveCommand)
    // Reset the drive encoders before starting
    .beforeStarting(m_driveCommand::resetEncoders)
    // End the command when the robot's driven distance exceeds the desired value
    .withInterrupt(
        () -> m_driveCommand.getEncoderOneAverage() >= 10);
  */
  //Complex Autonomous command
  private final Command m_complexAuto = new ComplexAuto(m_driveCommand, m_flywheelSubsystem);
  private final Command m_simpleShootAuto = new SimpleShootAuto (m_driveCommand);
  private final Command m_revUpFlywheel = new RevUpFlywheel(m_flywheelSubsystem);
  private final Command m_finalSimpleShootAuto = new FINALSimpleShootAuto (m_flywheelSubsystem, m_indexerSubsystem, m_plexiSubsystem, m_driveCommand);
  private final Command m_newAutoOne = new NewAutoOne(m_driveCommand, m_navX);
  private final Command m_newAutoTwo = new NewAutoTwo(m_driveCommand,m_navX);
  private final Command m_autoSkeleton = new AutoSkeleton(m_driveCommand);
  private final Command m_pathABlue = new PathABlue(m_driveCommand, m_navX, m_flywheelSubsystem);
  private final Command m_barrel = new Barrel(m_driveCommand, m_navX);
 
 
  //A chooser for autonomous commands
  SendableChooser<Command> m_chooser = new SendableChooser<>();

  /**
   * The container for the robot.  Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();

    // Manual Drive of the Robot
    m_driveCommand.setDefaultCommand( 
    new RunCommand(() -> m_driveCommand.driveCartesian(m_driverController.getRawAxis(2)*-1.00 + 
                         m_driverController.getRawAxis(3)*1.00,
                         m_driverController.getY(GenericHID.Hand.kLeft)*-1.00,
                         m_driverController.getX(GenericHID.Hand.kRight)),
                         m_driveCommand)
    );

    //m_chooser.addOption("Simple Auto", m_simpleAuto);
    m_chooser.addOption("Complex Auto", m_complexAuto);
    m_chooser.addOption("Simple Shoot Auto", m_simpleShootAuto);
    m_chooser.addOption("FINAL Simple Shoot Auto", m_finalSimpleShootAuto);
    m_chooser.addOption("Rev Up Flywheel", m_revUpFlywheel);
    m_chooser.addOption("2021 At Home challenge auto 1", m_newAutoOne);
    m_chooser.addOption("2021 At Home challenge auto 2", m_newAutoTwo);
    m_chooser.addOption("2021 At Home challenge Path A Blue", m_pathABlue);
    m_chooser.addOption("barrel", m_barrel);
    m_chooser.addOption("AUTO SKELETON", m_autoSkeleton);


    Shuffleboard.getTab("Autonomous").add(m_chooser);
  
    
    m_turretSubsystem.setDefaultCommand( 
    new RunCommand(() -> m_turretSubsystem.turretRotate( 
                         (m_operatorController.getRawAxis(3)-
                         m_operatorController.getRawAxis(2))*0.50),
                         m_turretSubsystem)
    );


  
/*
    // Shooter Turret Control
    if(Pidturretenabled == false){
      m_turretSubsystem.setDefaultCommand(
    
      new RunCommand(() -> m_turretSubsystem.turretRotateLeft(m_operatorController.getRawAxis(2)*0.5,
                           m_operatorController.getRawAxis(3)*0.5),
                           m_turretSubsystem)
    );
    }
    else if(Pidturretenabled == true){

    }*/
  }

  /**
   * Use this method to define your button->command mappings.  Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a
   * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    
  // OPERATOR BUTTONS- OPERATOR BUTTONS- OPERATOR BUTTONS- OPERATOR BUTTONS- OEPRATOR BUTTONS- OPERATOR BUTTONS- OPERATOR BUTTONS

    //ACCUMULATOR BUTTONS--------------------------------------------------------------------------------------------------------
    
    new JoystickButton(m_driverController, Button.kA.value)
    .whenPressed(() -> m_pnumatics.enableSolenoids(), m_pnumatics);
    
    new JoystickButton(m_driverController, Button.kB.value)
    .whenReleased(() -> m_pnumatics.disableSolenoids(), m_pnumatics);
    
    //HOOD BUTTONS -----------------------------------------------------------------------------------------------------------
    new JoystickButton(m_operatorController, Button.kY.value)
    .whenPressed(() -> m_hoodSubsystem.hoodUp(0.5), m_hoodSubsystem)
    .whenReleased(() -> m_hoodSubsystem.hoodStop(), m_hoodSubsystem);
    
    new JoystickButton(m_operatorController, Button.kX.value)
    .whenPressed(() -> m_hoodSubsystem.hoodDown(0.5), m_hoodSubsystem)
    .whenReleased(() -> m_hoodSubsystem.hoodStop(), m_hoodSubsystem);
 
    /* 
    new POVButton(m_operatorController,0,0)
    .whenPressed(() -> m_hoodSubsystem.hoodUp(0.10), m_hoodSubsystem)
    .whenReleased(() -> m_hoodSubsystem.hoodStop(), m_hoodSubsystem);
    
    new POVButton(m_operatorController,180,180)
    .whenPressed(() -> m_hoodSubsystem.hoodDown(0.10), m_hoodSubsystem)
    .whenReleased(() -> m_hoodSubsystem.hoodStop(), m_hoodSubsystem);
    */

    //COLOR WHEEL BUTTONS ----------------------------------------------------------------------------------------------------------------
    /*
    new JoystickButton(m_operatorController, Button.kStart.value)
    .whenPressed(() -> m_hoodPID.resetEncoderValue(), m_hoodPID);
    
    new JoystickButton(m_operatorController, Button.kBack.value)
    .whenPressed(new InstantCommand(m_hoodPID::enable, m_hoodPID));
    */
    //SHOOTER (MANUAL) BUTTONS--------------------------------------------------------------------------------------------------------------
    new JoystickButton(m_operatorController, Button.kB.value)
    .whenPressed(() -> m_flywheelSubsystem.reverseFlywheel(1), m_flywheelSubsystem)
    .whenReleased(() -> m_flywheelSubsystem.reverseFlywheel(0), m_flywheelSubsystem);

    new JoystickButton(m_operatorController, Button.kA.value)
    .whenPressed(() -> m_flywheelSubsystem.forwardFlywheel(1), m_flywheelSubsystem)
    .whenReleased(() -> m_flywheelSubsystem.forwardFlywheel(0), m_flywheelSubsystem);
    /*
    new JoystickButton(m_operatorController, Button.kY.value)
    .whenPressed(() -> m_flywheelSubsystem.reverseFlywheel(0.75), m_flywheelSubsystem)
    .whenReleased(() -> m_flywheelSubsystem.reverseFlywheel(0), m_flywheelSubsystem);

    //------------------------------------------------------------------------------------------------------------------------------
    //SHOOTER (PID) BUTTONS--------------------------------------------------------------------------------------------------------------
    /*
    new JoystickButton(m_operatorController, Button.kA.value)
    .whenPressed(() -> m_turretSubsystem.turretRotate(), m_turretSubsystem);
    /*
    .whenPressed(new InstantCommand(m_turretPID::enable, m_turretPID))
    .whenPressed(() -> Pidturretenabled = true);
    

    new JoystickButton(m_driverController, Button.kStart.value)
    .whenPressed(new InstantCommand(m_hoodPID::disable, m_hoodPID))
    .whenPressed(new InstantCommand(m_turretPID::disable, m_turretPID))
    .whenPressed(() -> Pidturretenabled = false);
    */
    //------------------------------------------------------------------------------------------------------------------------------
    
    
    // DRIVER BUTTONS- DRIVER BUTTONS- DRIVER BUTTONS- DRIVER BUTTONS- DRIVER BUTTONS- DRIVER BUTTONS- DRIVER BUTTONS- DRIVER BUTTONS





    //BEAVER BUTTONS---------------------------------------------------------------------------------------------------------------
    new JoystickButton(m_driverController, Button.kX.value)
    .whenPressed(() -> m_beaverTailSubsystem.beaverBoward(1), m_beaverTailSubsystem)
    .whenReleased(() -> m_beaverTailSubsystem.beaverBop(), m_beaverTailSubsystem);
    
    new JoystickButton(m_driverController, Button.kY.value)
    .whenPressed(() -> m_beaverTailSubsystem.beaverBackward(1), m_beaverTailSubsystem)
    .whenReleased(() -> m_beaverTailSubsystem.beaverBop(), m_beaverTailSubsystem);
    
    new JoystickButton(m_driverController, Button.kBumperLeft.value)
    .whenPressed(() -> m_beaverTailSubsystem.beaverBackward(1), m_beaverTailSubsystem)
    .whenReleased(() -> m_beaverTailSubsystem.beaverBop(), m_beaverTailSubsystem);
    
    new JoystickButton(m_driverController, Button.kBumperRight.value)
    .whenPressed(() -> m_beaverTailSubsystem.beaverBoward(1), m_beaverTailSubsystem)
    .whenReleased(() -> m_beaverTailSubsystem.beaverBop(), m_beaverTailSubsystem);
    
    new JoystickButton(m_operatorController, Button.kStart.value)
    .whenPressed(() -> m_driveCommand.resetEncoders(), m_driveCommand);
    
    new JoystickButton(m_operatorController, Button.kBack.value)
    .whenPressed(() -> m_navX.reset(), m_navX);
    

    //INDEXER BUTTONS-------------------------------------------------------------------------------------------------------------------

    
    new JoystickButton(m_operatorController, Button.kBumperRight.value)
    .whenPressed(() -> m_indexerSubsystem.indexerForward(1), m_indexerSubsystem)  //0.8
    .whenReleased(() -> m_indexerSubsystem.indexerStop(), m_indexerSubsystem);    

    new JoystickButton(m_operatorController, Button.kBumperLeft.value)
    .whenPressed(() -> m_indexerSubsystem.indexerBackward(1), m_indexerSubsystem)
    .whenReleased(() -> m_indexerSubsystem.indexerStop(), m_indexerSubsystem);
    /*
    new JoystickButton(m_driverController, Button.kBack.value)
    .whenPressed(() -> m_visionTrackingSubsystem.changeVisionState(), m_visionTrackingSubsystem);
*/

    //PNUMATICS BUTTONS---------------------------------------------------------------------------------------------------------------------

    new JoystickButton(m_driverController, Button.kStart.value)
    .whenPressed(() -> m_pnumatics.enableCompressor(), m_pnumatics);

    new JoystickButton(m_driverController, Button.kBack.value)
    .whenPressed(() -> m_pnumatics.disableCompressor(), m_pnumatics);
/*
    new JoystickButton(m_driverController, Button.kA.value)
    .whenPressed(() -> m_pnumatics.enableSolenoids(), m_pnumatics);

    new JoystickButton(m_driverController, Button.kB.value)
    .whenPressed(() -> m_pnumatics.disableSolenoids(), m_pnumatics);

/*
new JoystickButton(m_driverController, Button.kStart.value)
.whenReleased(() -> m_pnumatics.enableCompressor(), m_pnumatics);
*/

/*  //COLOR BUTTONS-------------------------------------------------------------------------------------------------------------------------
  new JoystickButton(m_operatorController, Button.kY.value)
  .whenPressed(() -> m_colorWheelSubsysytem.GoYellow());
  
  new JoystickButton(m_operatorController, Button.kB.value)
  .whenPressed(() -> m_colorWheelSubsysytem.GoRed());
  
  new JoystickButton(m_operatorController, Button.kA.value)
  .whenPressed(() -> m_colorWheelSubsysytem.GoGreen());
  
  new JoystickButton(m_operatorController, Button.kX.value)
  .whenPressed(() -> m_colorWheelSubsysytem.GoBlue());

*/

    /*
    new JoystickButton(m_driverController, Button.kY.value)
    .whenPressed(new InstantCommand(m_colorWheelPID::disable, m_colorWheelPID))
    .whenPressed(() -> m_colorWheelPID.resetEncoder(), m_colorWheelPID)
    //.whenPressed(() -> m_colorWheelPID.resetPIDValues(), m_colorWheelPID)
    .whenReleased(() -> m_colorWheelPID.colorWheelStop());


    new JoystickButton (m_driverController, Button.kB.value)
    .whenPressed(new InstantCommand(m_colorWheelPID::disable, m_colorWheelPID))
    .whenPressed(() -> m_colorWheelPID.colorWheelForward(1), m_colorWheelPID)
    .whenReleased(() -> m_colorWheelPID.colorWheelStop());
    */


    //-------------------------------------------------------------------------------------------------------------------
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
    // An ExampleCommand will run in autonomous
    public Command getAutonomouCommand(){
      return m_chooser.getSelected();
    }

public GenericHID getDriverJoystick() {
	return m_driverController;
}
  }

