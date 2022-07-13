public class FieldDriven extends LinearOpMode {
    private BNO055IMU imu;
    private boolean fieldOriented = false;
    private double FIELD_NORTH;

    @Override
    public void runOpMode() {
        initIMU();
        waitForStart();
        FIELD_NORTH = getHeading();
        boolean drivingModeClick = false;
        while(opModeIsActive()) {
            if(!gamepad1.dpad_down && drivingModeClick) {
                fieldOriented = !fieldOriented;
                fieldNorth = getHeading();
            }            
            drivingModeClick = gamepad1.dpad_down;
            controlChasis();
        }
    }

    private void controlChasis() {
        double drive = -gamepad1.left_stick_y;
        double lateral = gamepad1.left_stick_x;
        double turn = gamepad1.right_stick_x;
        if(fieldOriented) {
            double heading = getHeading();
            double degreesToRotate = FIELD_NORTH - heading;
            lateral = Math.cos(lateral * Math.toRadians(degreesToRotate)) - Math.sin(drive * Math.toRadians(degreesToRotate));
            drive = Math.sin(lateral * Math.toRadians(degreesToRotate)) + Math.cos(drive * Math.toRadians(degreesToRotate));
        }
        // TODO: Assign motor power
    }

    public void initIMU() {
        telemetry.addData("Calibrando ", "IMU");
        telemetry.update();
        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
        parameters.angleUnit           = BNO055IMU.AngleUnit.DEGREES;
        parameters.accelUnit           = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
        parameters.calibrationDataFile = "BNO055IMUCalibration.json";
        parameters.loggingEnabled      = true;
        parameters.loggingTag          = "IMU";
        parameters.accelerationIntegrationAlgorithm = new JustLoggingAccelerationIntegrator();

        imu = hardwareMap.get(BNO055IMU.class, "imu");
        imu.initialize(parameters);
        imu.startAccelerationIntegration(new Position(), new Velocity(), 1000);
    }

    public double getHeading() {
        Orientation angles = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
        return angles.firstAngle;
    }
}