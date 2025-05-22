// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.commands.Drive;
import frc.robot.commands.PositionElevate;
import frc.robot.commands.TankDrive;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Elevator;
import frc.robot.subsystems.Grabber;
import frc.thunder.LightningContainer;

import com.ctre.phoenix6.controls.PositionVoltage;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
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
  private Elevator elevator;
  private Compressor compressor;
  private Grabber grabber;
  
  private double multiplier = 0.7;
  private double targetElevatorPosition = 60;

  @Override
  protected void initializeSubsystems() {
    driver = new XboxController(0);
    drivetrain = new Drivetrain();
    elevator = new Elevator();
    grabber = new Grabber();

    compressor = new Compressor(0, PneumaticsModuleType.CTREPCM);
    compressor.enableDigital();
  }

  @Override
  protected void initializeNamedCommands() {
    // TODO Auto-generated method stub
    
  }

  @Override
  protected void configureButtonBindings() {
    new Trigger(driver::getRightBumperButton).whileTrue(new Drive(drivetrain, (() -> driver.getLeftX() * multiplier), (() -> driver.getLeftY() * multiplier)));
    
    new Trigger(driver::getAButton).whileTrue(new InstantCommand(() -> setTargetPosition(0)));
    new Trigger(driver::getBButton).whileTrue(new InstantCommand(() -> setTargetPosition(25)));
    new Trigger(driver::getXButton).whileTrue(new InstantCommand(() -> setTargetPosition(50)));
    new Trigger(driver::getYButton).whileTrue(new InstantCommand(() -> setTargetPosition(100)));

    new Trigger(driver::getRightBumperButton).onTrue(new InstantCommand(() -> grabber.openArms()));
    new Trigger(driver::getRightBumperButton).onFalse(new InstantCommand(() -> grabber.closeArms()));
  }

  @Override
  protected void configureDefaultCommands() {
    // TODO Auto-generated method stub
    drivetrain.setDefaultCommand(
      new TankDrive(drivetrain, (() -> driver.getLeftY() * multiplier), (() -> driver.getRightY() * multiplier)));
    elevator.setDefaultCommand(new PositionElevate(elevator, () -> targetElevatorPosition));
 }

  @Override
  protected Command getAutonomousCommand() {
    return null;
  }

  private void setTargetPosition(double value) {
    targetElevatorPosition = value;
    System.out.println(targetElevatorPosition);
  }
}
