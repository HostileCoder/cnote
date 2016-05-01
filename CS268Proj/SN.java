import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Arrays;

class SN extends Thread {

	private InetAddress IPAddress;
	private int port;
	private DatagramSocket UDPSocket;
	private byte[] ciphertext;
	private byte[] plaintext;
	private Symmetric alg;
	private String text="RANDOMRANDOMRANDOMRANDOMRANDOMRANDOMRANDOM\0\0\0\0\0\0";
	private int bitS;
	private int bitR;
	//1113
	public SN(int port,String s) throws Exception {
		IPAddress = InetAddress.getByName("localhost");
		this.port = port;
		UDPSocket = new DatagramSocket(port);
		alg = ProjUtil.alg(s);
		ciphertext=alg.encrypt(text.getBytes());
	}

	public void run() {
		try
		{
			while (true) {
			
				byte[] receiveData = new byte[1024];
				byte[] sendData = new byte[1024];
				String msg="";

				DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);
				DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);

				UDPSocket.receive(receivePacket);
				String reply = new String(receivePacket.getData());

				String[] parts = reply.split(" ");
				String num = parts[0];
				int msgID = Integer.parseInt(num);

				
				switch (msgID) {
				case 1:
					System.out.println("SN:Got Msg1 from MD. Sending Msg1 to the MSP");
					msg = "1 "+Arrays.toString(ciphertext);
					sendData = msg.getBytes();
					sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 1112);
					UDPSocket.send(sendPacket);
					
//					System.out.println("bits recieved:" + receivePacket.getLength()*8);
//					System.out.println("bits sent:" + sendPacket.getLength()*8);
					
					bitS=bitS+sendPacket.getLength()*8;
					bitR=bitR+receivePacket.getLength()*8;
					break;				
				case 2:
					
					alg.decrypt(ciphertext);
					alg.encrypt(text.getBytes());				
					System.out.println("SN:Got Msg2 from MSP. Sending Msg3 to the MD");
					msg = "3 "+Arrays.toString(ciphertext);
					sendData = msg.getBytes();
					sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 1111);
					UDPSocket.send(sendPacket);
					
//					System.out.println("bits recieved:" + receivePacket.getLength()*8);
//					System.out.println("bits sent:" + sendPacket.getLength()*8);
					
					bitS=bitS+sendPacket.getLength()*8;
					bitR=bitR+receivePacket.getLength()*8;
					
					break;
				case 4:
					
					alg.decrypt(ciphertext);
					alg.encrypt(text.getBytes());
					
					System.out.println("SN:Got Msg4 from MD. Sending Msg5 to the MD");
					IPAddress = receivePacket.getAddress();
					port = receivePacket.getPort();
					msg = "5 "+Arrays.toString(ciphertext);
					sendData = msg.getBytes();
					sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);
					UDPSocket.send(sendPacket);
									
//					System.out.println("bits recieved:" + receivePacket.getLength()*8);
//					System.out.println("bits sent:" + sendPacket.getLength()*8);
					
					bitS=bitS+sendPacket.getLength()*8;
					bitR=bitR+receivePacket.getLength()*8;
					System.out.println(bitS+"  "+bitR);
					
					
					break;				
				case 6:
					
					alg.decrypt(ciphertext);
										
					System.out.println("SN:Got Msg6 from MD. Sending Msg7 to the MD");
					IPAddress = receivePacket.getAddress();
					port = receivePacket.getPort();
					msg = "7 "+Arrays.toString(ciphertext);
					sendData = msg.getBytes();
					sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);
					UDPSocket.send(sendPacket);
								
				
				default:
					break;
				}
				
			}
		} catch (Exception e)

		{
			e.printStackTrace();
		}
	}

}