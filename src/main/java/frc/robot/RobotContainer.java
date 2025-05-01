// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.commands.Drive;
import frc.robot.commands.TankDrive;
import frc.robot.subsystems.Drivetrain;
import frc.thunder.LightningContainer;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.Trigger;
/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer extends LightningContainer {
  private XboxController driver;
  private Drivetrain drivetrain;

  private double multiplier = 0.7;

  @Override
  protected void initializeSubsystems() {
    driver = new XboxController(0);
    drivetrain = new Drivetrain();
  }

  @Override
  protected void initializeNamedCommands() {
    // TODO Auto-generated method stub
    
  }

  @Override
  protected void configureButtonBindings() {
    new Trigger(driver::getRightBumperButton).whileTrue(new TankDrive(drivetrain, (() -> driver.getLeftY() * multiplier), (() -> driver.getRightY() * multiplier)));
  }

  @Override
  protected void configureDefaultCommands() {
    // TODO Auto-generated method stub
    drivetrain.setDefaultCommand(
      new Drive(drivetrain, (() -> driver.getLeftX() * multiplier), (() -> driver.getLeftY() * multiplier))
    );
  }

  @Override
  protected Command getAutonomousCommand() {
    return null;
  }
  
}
