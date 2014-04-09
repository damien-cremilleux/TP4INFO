package chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ServeurChat {
	static int port = 50001; // port par défaut pour telnet
	public ArrayList<Socket> listeS;
	public ServerSocket serv;

	public ServeurChat() {
		try {
			serv = new ServerSocket(port);
			listeS = new ArrayList<Socket>();

		} catch (IOException e) {
			System.out.println("Could not listen on port" + port);
			System.exit(-1);
		}

	}

	public static void main(String argv[]) throws IOException,
	InterruptedException {
		ServeurChat sc = new ServeurChat();
		while (true) {
			Socket soc = sc.serv.accept();
			threadServeurChat thread = new threadServeurChat(sc.listeS, soc);
			sc.listeS.add(soc);
			thread.start();
		}
	}

}

class threadServeurChat extends Thread {
	ArrayList<Socket> listeS;
	Socket soc;
	PrintWriter out;

	public threadServeurChat(ArrayList<Socket> listeSocket, Socket soc)
			throws IOException {
		this.listeS = listeSocket;
		this.soc = soc;
		this.out = new PrintWriter(soc.getOutputStream(), true);
	}

	public void propagerMessage(String mess) throws IOException {
		for (Socket s : listeS) {
			if(!s.equals(soc)){
				PrintWriter out = new PrintWriter(s.getOutputStream(), true);
				out.println(mess);
			}
		}

	}

	@Override
	public void run() {
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(soc.getInputStream()));
			String inputLine;
			while((inputLine = in.readLine()) != null)
			{
				propagerMessage(inputLine);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
