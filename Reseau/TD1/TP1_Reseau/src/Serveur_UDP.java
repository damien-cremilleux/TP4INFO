import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Calendar;



public	class Serveur_UDP extends Thread
{
	int Port;

	public Serveur_UDP(int Un_Port)
	{

		this.Port  = Un_Port;
	}

	public void run()
	{
		while(true)
		{
			System.out.println("Démarrage du serveur");

			byte[] receiveBuf = new byte[256];
			DatagramSocket Socket_UDP;

			// Ouvrir un socket UDP      

			try {
				Socket_UDP = new DatagramSocket(Port);

				// Recevoir un message sur ce port
				DatagramPacket receivePacket = new DatagramPacket(receiveBuf, receiveBuf.length);

				System.out.println("Attente d'un message");

				Socket_UDP.receive(receivePacket);

				String sentence = new String( receivePacket.getData());
				System.out.println("RECEIVED: " + sentence);

				InetAddress IPAddress = receivePacket.getAddress();

				System.out.println(IPAddress);

			//	IPAddress = InetAddress.getByName("localhost");
				Port = receivePacket.getPort();


				receiveBuf[0] = '2';
				receiveBuf[1] = '4';
				receiveBuf[2] = '0';
				receiveBuf[3] = '2';
				receiveBuf[4] = '2';
				receiveBuf[5] = '0';
				receiveBuf[6] = '1';
				receiveBuf[7] = '4';


				Calendar cal = Calendar.getInstance();
				int day = cal.get(Calendar.DATE);
				System.out.println(day);
				//int annee = cal.get(Calendar.YEAR);
				//int mois = cal.get(Calendar.MONTH); //Donne un résultat entre 0 et 11

				DatagramPacket sendPacket = new DatagramPacket(receiveBuf, receiveBuf.length, IPAddress, Port);

				String sentence2 = new String( sendPacket.getData());
				System.out.println("SEND: " + sentence2);
				System.out.println(receiveBuf.length);

				Socket_UDP.send(sendPacket);



				System.out.println("Renvoi d'un message");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] argv)
	{
		Serveur_UDP s1 = new Serveur_UDP(33000);
		s1.start();

	}
}