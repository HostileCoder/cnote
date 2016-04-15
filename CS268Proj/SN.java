import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

class SN {
	public static void main(String args[]) throws Exception {

		InetAddress IPAddress = InetAddress.getByName("localhost");
		int port = 1113;

		while (true) {
			DatagramSocket UDPSocket = new DatagramSocket(1234);
			byte[] receiveData = new byte[1024];
			byte[] sendData = new byte[1024];
			String msg ;

			DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);


			DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
			System.out.println("Waiting for Replay from MSP" + UDPSocket.getLocalPort() + "...");
			UDPSocket.receive(receivePacket);
			String reply = new String(receivePacket.getData());

			String[] parts = reply.split(" ");
			String num = parts[0];
			int msgID = Integer.parseInt(num);

			switch (msgID) {
			case 1:
				msg = "1";
				sendData = msg.getBytes();
				sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 1112);
				UDPSocket.send(sendPacket);
				break;
			case 2:
				msg = "2";
				sendData = msg.getBytes();
				sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 1111);
				UDPSocket.send(sendPacket);
				break;
			default:
				break;
			}

		}

	}
}