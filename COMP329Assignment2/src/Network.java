import java.net.*;
import java.io.*;

public class Network extends Thread{
	private SimpleRobot robot;
	private int delay;
	private int port =1234;
	
	public Network(SimpleRobot robot, int delay) {
		this.robot = robot;
		this.delay = delay;
	}
	
	public void run() {
		while(true) {
			try {
	            ServerSocket server = new ServerSocket(port);
	            Socket client = server.accept();
	            OutputStream out = client.getOutputStream();
	            DataOutputStream dOut = new DataOutputStream(out);
	            out.close();
		        dOut.flush();
		        dOut.close();
		        server.close();
		        try {
		        	sleep(delay);
		        }catch(Exception e) {
		        		
		        }
			}catch(IOException e) {
				
	        } 
		}
	}
}
