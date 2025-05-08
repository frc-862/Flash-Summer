// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.TalonSRXControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.RobotMap;

public class Elevator extends SubsystemBase {

  public TalonSRX elevatorMotor;

  public Elevator() {
    elevatorMotor = new TalonSRX(RobotMap.ELEVATOR_ID);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void setPower(double power){
    elevatorMotor.set(TalonSRXControlMode.PercentOutput, power);
  }
}
