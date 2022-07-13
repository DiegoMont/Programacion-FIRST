public class FieldDriven extends LinearOpMode {
    private BNO055IMU imu;

    @Override
    public void runOpMode() {
        initIMU();
        waitForStart();

        boolean drivingModeClick = false;
        boolean fieldOriented = false;
        double fieldNorth = getHeading();
        while(opModeIsActive()) {

            if(fieldOriented) {
                // Implement logic
            }

        }
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