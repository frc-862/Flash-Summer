 // Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Drivetrain;

/* You should consider using the more terse Command factories API instead https://docs.wpilib.org/en/stable/docs/software/commandbased/organizing-command-based.html#defining-commands */
public class TankDrive extends Command {
  private Drivetrain drivetrain;

  private DoubleSupplier leftPower;
  private DoubleSupplier rightPower;


  /** Creates a new TankDrive. */
  public TankDrive(Drivetrain drivetrain, DoubleSupplier leftPower, DoubleSupplier rightPower) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.drivetrain = drivetrain;

    this.leftPower = leftPower;
    this.rightPower = rightPower;

    addRequirements(drivetrain);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    drivetrain.setVerticalPower(leftPower.getAsDouble(), rightPower.getAsDouble());
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    drivetrain.setVerticalPower(0, 0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
