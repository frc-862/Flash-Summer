// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Grabber extends SubsystemBase {

  private DoubleSolenoid solenoid;

  /** Creates a new Grabber. */
  public Grabber() {
    solenoid = new DoubleSolenoid(0, PneumaticsModuleType.CTREPCM, 0, 1);
  }

  @Override
  public void periodic() {}

  public void openArms() {
    solenoid.set(Value.kForward);
  }
  
  public void closeArms() {
    solenoid.set(Value.kReverse);
  }
}
