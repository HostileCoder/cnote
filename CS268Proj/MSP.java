import java.net.*;

class MSP {
	public static void main(String args[]) throws Exception {

		InetAddress IPAddress = InetAddress.getByName("localhost");
		int port = 1112;

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
				System.out.println("Got Msg1 from the sensor. Sending Msg2 to the sensor");
				IPAddress=receivePacket.getAddress();
				port=receivePacket.getPort();
				msg = "2";
				sendData = msg.getBytes();
				sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);
				UDPSocket.send(sendPacket);
				break;
			case 2:
				System.out.println("Got Msg2 from the mobile device. Sending Msg3 to the mobile device");
				IPAddress=receivePacket.getAddress();
				port=receivePacket.getPort();
				msg = "3";
				sendData = msg.getBytes();
				sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);
				UDPSocket.send(sendPacket);
				break;
			case 4:
				System.out.println("Got Msg4 from the mobile device. Sending Msg4 to the mobile device");
				IPAddress=receivePacket.getAddress();
				port=receivePacket.getPort();
				msg = "5";
				sendData = msg.getBytes();
				sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);
				UDPSocket.send(sendPacket);
				break;
			case 6:
				System.out.println("end");
				break;
			default:
				break;
			}

		}

	}
}