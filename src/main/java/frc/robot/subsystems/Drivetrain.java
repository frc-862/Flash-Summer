// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.TalonSRXControlMode;
import com.ctre.phoenix.motorcontrol.VictorSPXControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.RobotMap;

public class Drivetrain extends SubsystemBase {

  public TalonSRX leftVerticalMotor;
  public VictorSPX rightVerticalMotor;
  public TalonSRX frontHorizontalMotor;
  public TalonSRX backHorizontalMotor;

  /** Creates a new Drivetrain. */
  public Drivetrain() {
    leftVerticalMotor = new TalonSRX(RobotMap.LV_MOTOR_ID);
    rightVerticalMotor = new VictorSPX(RobotMap.RV_MOTOR_ID);
    frontHorizontalMotor = new TalonSRX(RobotMap.FH_MOTOR_ID);
    backHorizontalMotor = new TalonSRX(RobotMap.BH_MOTOR_ID);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void setVerticalPower(double leftPower, double rightPower) {
    leftVerticalMotor.set(TalonSRXControlMode.PercentOutput, -leftPower);
    rightVerticalMotor.set(VictorSPXControlMode.PercentOutput, rightPower);
  }

  public void setHorizontalPower(double frontPower, double backPower) {
    frontHorizontalMotor.set(TalonSRXControlMode.PercentOutput, -frontPower);
    backHorizontalMotor.set(TalonSRXControlMode.PercentOutput, backPower);
  }

  public void stop() {
    leftVerticalMotor.set(TalonSRXControlMode.PercentOutput, 0);
    rightVerticalMotor.set(VictorSPXControlMode.PercentOutput, 0);

    leftVerticalMotor.set(TalonSRXControlMode.PercentOutput, 0);
    rightVerticalMotor.set(VictorSPXControlMode.PercentOutput, 0);
  }
}
