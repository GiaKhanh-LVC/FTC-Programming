package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.Range;

@TeleOp(name="Sample_TeleOp", group="TeleOp")
public class Sample_TeleOp extends OpMode {

    private DcMotor leftFront, rightFront;
    private DcMotor leftRear, rightRear;
    private double theta;

    @Override
    public void init() {
        leftFront = hardwareMap.get(DcMotor.class, "leftFront");
        rightFront = hardwareMap.get(DcMotor.class, "rightFront");
        leftRear = hardwareMap.get(DcMotor.class, "leftRear");
        rightRear = hardwareMap.get(DcMotor.class, "rightRear");

        leftFront.setDirection(DcMotor.Direction.FORWARD);
        rightFront.setDirection(DcMotor.Direction.REVERSE);
        leftRear.setDirection(DcMotor.Direction.FORWARD);
        rightRear.setDirection(DcMotor.Direction.REVERSE);

        leftFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftRear.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightRear.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    @Override
    public void loop() {
        double driveX = gamepad1.left_stick_x;  // Chuyển động theo trục X
        double driveY = -gamepad1.left_stick_y; // Chuyển động theo trục Y (âm vì Y đảo ngược)
        double turn = gamepad1.right_stick_x;   // Thành phần quay


        double magnitude = Math.hypot(driveX, driveY);
        theta = Math.atan2(driveY, driveX);


        double sin = Math.sin(theta - Math.PI / 4);
        double cos = Math.cos(theta - Math.PI / 4);
        double max = Math.max(Math.abs(sin), Math.abs(cos));


        double leftFrontPower = (magnitude * cos / max) + turn;
        double rightFrontPower = (magnitude * sin / max) - turn;
        double leftRearPower = (magnitude * sin / max) + turn;
        double rightRearPower = (magnitude * cos / max) - turn;


        leftFront.setPower(Range.clip(leftFrontPower, -1.0, 1.0));
        rightFront.setPower(Range.clip(rightFrontPower, -1.0, 1.0));
        leftRear.setPower(Range.clip(leftRearPower, -1.0, 1.0));
        rightRear.setPower(Range.clip(rightRearPower, -1.0, 1.0));
    }
}
