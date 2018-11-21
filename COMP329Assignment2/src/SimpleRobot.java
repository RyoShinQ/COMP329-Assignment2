import lejos.hardware.Brick;
import lejos.hardware.BrickFinder;
import lejos.hardware.motor.EV3MediumRegulatedMotor;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.sensor.EV3GyroSensor;
//import lejos.hardware.sensor.EV3IRSensor;			//can be initialise by thread
//import lejos.hardware.sensor.EV3ColorSensor;		//can be initialise by thread
import lejos.robotics.SampleProvider;
import lejos.robotics.chassis.Chassis;
import lejos.robotics.chassis.Wheel;
import lejos.robotics.chassis.WheeledChassis;
import lejos.robotics.navigation.MovePilot;

public class SimpleRobot {
	//private EV3ColorSensor leftColSensor, rightColSensor;		//can be initialise by thread
	//private EV3IRSensor irSensor;		//can be initialise by thread
	private EV3GyroSensor gyroSensor;
	private EV3LargeRegulatedMotor motorL, motorR;
	private EV3MediumRegulatedMotor motorM;
	
	private SampleProvider gyroSP;	//leftColSP, rightColSP, irSP;
	private float[] gyroSample;		//leftColSample, rightColSample, irSample
	
	private int xPos;
	private int yPos;
	private int direction; // 0 means north, 1 means east, 2 means south, 3 means west
	
	private Cell[][] map;
	private int width = 6;
	private int height = 6;
	
	private MovePilot pilot;
	
	public SimpleRobot() {
		Brick myEV3 = BrickFinder.getDefault();
		
		// Setting up Sensor/Bumper Ports
		//leftColSensor = new EV3TouchSensor(myEV3.getPort("S1"));
		//rightColSensor = new EV3TouchSensor(myEV3.getPort("S4"));
		//irSensor = new EV3UltrasonicSensor(myEV3.getPort("S3"));
		gyroSensor = new EV3GyroSensor(myEV3.getPort("S2"));
		
		// Setting up Motor Ports
		motorL = new EV3LargeRegulatedMotor(myEV3.getPort("B"));
		motorR = new EV3LargeRegulatedMotor(myEV3.getPort("D"));
		motorM = new EV3MediumRegulatedMotor(myEV3.getPort("C"));
		
		// Setting up Sensors 
		//leftColSP = leftColSensor.getRGBMode();
		//rightColSP = rightColSensor.getRGBMode();
		//irSP = irSensor.getSeekMode();
		gyroSP = gyroSensor.getAngleMode();

		// Setting up Sample
		//leftColSample = new float[leftSP.sampleSize()];		
		//rightColSample = new float[rightSP.sampleSize()];		
		//irSample = new float[irSP.sampleSize()];
		gyroSample = new float[gyroSP.sampleSize()]; 
		
		// Setting up Chassis and Pilot
		Wheel leftWheel = WheeledChassis.modelWheel(motorL, 4.05).offset(-4.9);
		Wheel rightWheel = WheeledChassis.modelWheel(motorR, 4.05).offset(4.9);
		Chassis chassis = new WheeledChassis( new Wheel[]{leftWheel, rightWheel}, WheeledChassis.TYPE_DIFFERENTIAL);
		pilot = new MovePilot(chassis);
		
		//initialise position of the robot
		xPos = 0;
		yPos = 0;
		direction = 0;
		
		//initialise the map
		for(int i=0; i<height; i++) {
			for(int j=0; j<width; j++) {
				map[j][i] = new Cell();
			}
		}
	}
}
