import java.io.IOException;

public class Test {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		  String s="RC4";
	      Thread MSP = new MSP(1112,s);
	      Thread SN = new SN(1113,s);
	      Thread MD = new MD(1111,s);
	      MSP.start();
	      SN.start();
	      MD.start();
	}

}
