// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.math.geometry.Translation2d;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
  public static class LEDConstants {
    public enum LEDStates {}
  }

  public static class PoseConstants {
    public static Translation2d FIELD_LIMIT = new Translation2d();
  }

  public static class RobotMap {
    public static int LV_MOTOR_ID = 4;
    public static int RV_MOTOR_ID = 2;
    public static int FH_MOTOR_ID = 1;
    public static int BH_MOTOR_ID = 5;

    public static int ELEVATOR_ID = 3;
  }

  public static class ElevatorConstants {
    public static double FULL_RANGE_TIME = 1.5; // seconds at 100% power
  }
}
