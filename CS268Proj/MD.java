import java.io.IOException;
import java.net.*;
import java.util.Arrays;

class MD extends Thread {
	private byte[] ciphertext;
	private byte[] plaintext;
	private boolean begin = true;
	private InetAddress IPAddress;
	private int port;
	private DatagramSocket UDPSocket;
	private Symmetric alg;
	private String text = "RANDOMRANDOMRANDOMRANDOMRANDOMRANDOMRANDOM\0\0\0\0\0\0";

	// 1111
	public MD(int port, String s) throws Exception {
		IPAddress = InetAddress.getByName("localhost");
		this.port = port;
		UDPSocket = new DatagramSocket(port);	
		
		alg = ProjUtil.alg(s);
		
		ciphertext = alg.encrypt(text.getBytes());
	}

	public void run() {
		int c=0;
		long startTime = System.currentTimeMillis();
		try {
			while(true) {

				byte[] receiveData = new byte[1024];
				byte[] sendData = new byte[1024];
				DatagramPacket sendPacket;
				String msg = "1 "+Arrays.toString(ciphertext);
				
				if (begin == true) {
					alg.encrypt(text.getBytes());
					sendData = msg.getBytes();
					sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 1113);
					UDPSocket.send(sendPacket);
					begin = false;
				}
				DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);

				UDPSocket.receive(receivePacket);
				String reply = new String(receivePacket.getData());

				String[] parts = reply.split(" ");
				String num = parts[0];
				int msgID = Integer.parseInt(num);

				switch (msgID) {
				case 3:

					alg.encrypt(text.getBytes());
					alg.decrypt(ciphertext);

					System.out.println("MD:Got Msg3 from the sensor. Sending Msg4 to the sensor");
					IPAddress = receivePacket.getAddress();
					port = receivePacket.getPort();
					msg = "4 "+Arrays.toString(ciphertext);
					sendData = msg.getBytes();
					sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);
					UDPSocket.send(sendPacket);
					
					break;
				case 5:

					alg.encrypt(text.getBytes());
					alg.decrypt(ciphertext);

					System.out.println("MD:Got Msg5 from the sensor. Sending Msg6 to the sensor");
					IPAddress = receivePacket.getAddress();
					port = receivePacket.getPort();
					msg = "6 "+Arrays.toString(ciphertext);
					sendData = msg.getBytes();
					sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);
					UDPSocket.send(sendPacket);

					break;

				case 7:
					System.out.println("END");
					msg = "end";
					begin = true;
					c++;
					break;
				
				default:
					break;
				}

				if (c==1000) {
					break;
				}
		
			}

			long endTime = System.currentTimeMillis();
			System.out.println("Total execution time: " + (endTime - startTime));

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}