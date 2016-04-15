import java.net.*;

class MD {
	public static void main(String args[]) throws Exception {

		InetAddress IPAddress = InetAddress.getByName("localhost");
		int port = 1111;

		while (true) {
			DatagramSocket UDPSocket = new DatagramSocket(port);
			byte[] receiveData = new byte[1024];
			byte[] sendData = new byte[1024];

			String msg = "1";
			sendData = msg.getBytes();
			DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 1113);
			UDPSocket.send(sendPacket);

			DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
			System.out.println("Waiting for Replay from MSP" + UDPSocket.getLocalPort() + "...");
			UDPSocket.receive(receivePacket);
			String reply = new String(receivePacket.getData());

			String[] parts = reply.split(" ");
			String num = parts[0];
			int msgID = Integer.parseInt(num);

			switch (msgID) {
			case 3:
				System.out.println("Got Msg3 from the sensor. Sending Msg4 to the sensor");
				IPAddress=receivePacket.getAddress();
				port=receivePacket.getPort();
				msg = "4";
				sendData = msg.getBytes();
				sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);
				UDPSocket.send(sendPacket);
				break;
			case 5:
				System.out.println("Got Msg5 from the sensor. Sending Msg6 to the sensor");
				IPAddress=receivePacket.getAddress();
				port=receivePacket.getPort();
				msg = "6";
				sendData = msg.getBytes();
				sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);
				UDPSocket.send(sendPacket);
				break;
			default:
				break;
			}

		}

	}
}