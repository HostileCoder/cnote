import java.io.IOException;
import java.net.*;
import java.util.Arrays;

class MSP extends Thread {

	private InetAddress IPAddress;
	private int port;
	private DatagramSocket UDPSocket;
	private byte[] ciphertext;
	private byte[] plaintext;
	private Symmetric alg;
	private String text="RANDOMRANDOMRANDOMRANDOMRANDOMRANDOMRANDOM\0\0\0\0\0\0"
						+ "RANDOMRANDOMRANDOMRANDOMRANDOMRANDOMRANDOM\0\0\0\0\0\0"
						+ "RANDOMRANDOMRANDOMRANDOMRANDOMRANDOMRANDOM\0\0\0\0\0\0"
						+ "RANDOMRANDOMRANDOMRANDOMRANDOMRANDOMRANDOM\0\0\0\0\0\0"
						+ "RANDOMRANDOMRANDOMRANDOMRANDOMRANDOMRANDOM\0\0\0\0\0\0";
	//private String text="abcdefghijklmnopqrstuvwxyz123456";
	//1112
	public MSP(int port,String s) throws Exception {
		IPAddress = InetAddress.getByName("localhost");
		this.port = port;
		UDPSocket = new DatagramSocket(port);
		alg = ProjUtil.alg(s);
		ciphertext=alg.encrypt(text.getBytes());
	}

	public void run() {

		try {
			while (true) {
		
				byte[] receiveData = new byte[1024];
				byte[] sendData = new byte[1024];
				String msg;

				DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);

				DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
				//System.out.println("Waiting for Replay from MSP" + UDPSocket.getLocalPort() + "...");
				UDPSocket.receive(receivePacket);
				String reply = new String(receivePacket.getData());

				String[] parts = reply.split(" ");
				String num = parts[0];
				int msgID = Integer.parseInt(num);

				switch (msgID) {
				case 1:
					
					alg.decrypt(ciphertext);
					alg.encrypt(text.getBytes());
					
					System.out.println("MSP:Got Msg1 from the sensor. Sending Msg2 to the sensor");
					IPAddress = receivePacket.getAddress();
					port = receivePacket.getPort();
					msg = "2 "+text;
					sendData = msg.getBytes();
					sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);
					UDPSocket.send(sendPacket);
					break;
				default:
					break;
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}