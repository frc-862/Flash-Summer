// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.Constants.ElevatorConstants;
import frc.robot.subsystems.Elevator;
import frc.thunder.shuffleboard.LightningShuffleboard;

/* You should consider using the more terse Command factories API instead https://docs.wpilib.org/en/stable/docs/software/commandbased/organizing-command-based.html#defining-commands */
public class PositionElevate extends Command {

  private Elevator elevator;

  private DoubleSupplier targetPosition;
  private double currentPosition;

  private double duration;
  private Timer timer;

  private double power;

  /** Creates a new PositionElevate. */
  public PositionElevate(Elevator elevator, DoubleSupplier targetPosition) {
    this.elevator = elevator;

    this.targetPosition = targetPosition;
    this.currentPosition = 0;

    this.timer = new Timer();

    this.power = 0;

    addRequirements(elevator);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    timer.start();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    // Shuffleboard values
    LightningShuffleboard.setDouble("Elevator", "Target Position", targetPosition.getAsDouble());
    LightningShuffleboard.setDouble("Elevator", "Current Position", targetPosition.getAsDouble());
    LightningShuffleboard.setDouble("Elevator", "Duration", duration);
    LightningShuffleboard.setDouble("Elevator", "Runtime", timer.get());

    if (targetPosition.getAsDouble() != currentPosition) {
      calculateDuration();
      calculatePower();

      System.out.println("Target position updated");
      System.out.println("CurrentPosition: " + currentPosition);

      currentPosition = targetPosition.getAsDouble();

      System.out.println("Target position: " + targetPosition.getAsDouble());
    }

    if (duration > timer.get()) {
      elevator.setPower(power);
      System.out.println("Moving with power: " + power);
    } else {  
      elevator.setPower(0.09); 
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    timer.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }

  private void calculateDuration() {
    duration = ((Math.abs(targetPosition.getAsDouble() - currentPosition) / 100) * ElevatorConstants.FULL_RANGE_TIME) + timer.get();
    System.out.println("Duration just set to: " + duration);
  }

  private void calculatePower() {
    power = Math.signum(targetPosition.getAsDouble() - currentPosition);

    if (power < 0) {
      power = -0.87;
    }

    System.out.println("Power just set to: " + power);
  }
}
