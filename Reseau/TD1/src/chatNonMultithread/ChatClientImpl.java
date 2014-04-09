package chatNonMultithread;

import java.net.MalformedURLException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.io.*;

/**
 * This class describes the client object that handles the communications with
 * the server
 */

public class ChatClientImpl extends UnicastRemoteObject implements ClientDistant  {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * The name of the user who sends messages using this client
	 */

	protected String user;

	/**
	 * Remote reference to the server
	 */

	protected ChatServer theServer;

	/**
	 * We create and initialise an object for user <code>user</code> in order to
	 * speak to the server which can be found at the URL passed as a second
	 * parameter
	 */

	public ChatClientImpl(String user, ChatServer server)
			throws RemoteException {
		this.user = user;
		this.theServer = server;

		/*
		 * this.start(); this.sendMessage();
		 */
	}

	public static void main(String[] args) {
		String url;
		String server_name;
		String user;
		String adresseClient;
		String ad_server;

		// Checks the arguments passed on the command line and prints out
		// a help message if there are not enough arguments (2)
		if (args.length < 2) {
			System.out.println("Usage: client <server URL> <username>");
			return;
		} else {
			server_name = args[0];
			user = args[1];
		}
		try {
			ad_server = InetAddress.getByName(server_name).getHostAddress();
		} catch (UnknownHostException e) {
			return;
		}

		url = "rmi://" + ad_server + ":50001/simpleChatServer";
		// Tries to connect to the server
		try {
			// Lookups up the server object using the rmiregistry
			ChatServer server = (ChatServer) Naming.lookup(url);
			if (server != null) {
				System.out.println("Server found at URL " + url);

				// Server is found, let's create the client object
				ChatClientImpl theClient = new ChatClientImpl(user, server);
				server.connect((ClientDistant) theClient);
				while (true) {
					try {
						System.out.println("Tape une ligne ");
						String line;
						BufferedReader clavier = new BufferedReader(
								new InputStreamReader(System.in));
						line = clavier.readLine();

						line = "[" + theClient.user + "] " + line;
						server.sendmsg(line);
						// Remote call
						// this.theServer.addNewMessage(line);
					} catch (RemoteException e) {
						System.out
								.println("Problem while sending a message to the server: "
										+ e);
					} catch (IOException ex) {
						System.err.println("IO Problem " + ex);
					}
				}
			} else {
				System.out.println("No server found at URL " + url);
			}
		} catch (MalformedURLException e) {
			System.out.println("URL is not a valid one: " + url);
		} catch (NotBoundException e) {
			System.out.println("No server bound with this URL: " + url);
		} catch (RemoteException e) {
			System.out.println("Error, client cannot find server: " + e);
		}

		return;
	}

	@Override
	public void msg(String m) throws RemoteException {
		System.out.println(m);

	}
}
