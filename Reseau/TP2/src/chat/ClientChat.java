package chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientChat {

	static int numPort = 50001;
	static String machine = "badoit";

	public static void main(String argv[]) throws UnknownHostException, IOException{
			Socket client = new Socket(machine, numPort);
			threadClientEm thread = new threadClientEm();
			thread.start();
			
			InputStreamReader ins = new InputStreamReader(client.getInputStream());
			BufferedReader in = new BufferedReader(ins);
		}
}


class threadClientEm extends Thread {
	public void run() {

	}
	
}
