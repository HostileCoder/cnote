
//derived from http://www.java2s.com/Code/Java/Security/Basicsymmetricencryptionexample.htm


import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
public class RC4 implements Symmetric {
	private byte[] plaintext;
	private byte[] keyBytes;
	private SecretKey key;
	private Cipher cipher;
	private byte[] ciphertext;

	
	public RC4() throws Exception{
//		KeyGenerator keyGen = KeyGenerator.getInstance("RC4");
//		keyGen.init(64);
//		key = keyGen.generateKey();
		//40	1176
		//48	1152
		//56	1128
		//64	1176
		//72	1128	
		//80	1120
		//88	1128
		//96	1200
		//104	1080
		//112	1104
		//120	1104
		//128	1104
		keyBytes = new byte[] { 0x00, 0x01, 0x02, 0x03, 
								0x04, 0x05, 0x06, 0x07, 
								0x08, 0x09, 0x0a, 0x0b, 
								0x0c, 0x0d, 0x0e, 0x0f,
								
								};
	    key = new SecretKeySpec(keyBytes, "RC4");
	    cipher = Cipher.getInstance("RC4");
	   
	}
	
	public byte[] encrypt(byte[] plaintext) throws Exception{
		cipher.init(Cipher.ENCRYPT_MODE, key);
		byte[] ciphertext = cipher.doFinal(plaintext);
	    //System.out.println(ciphertext.length);
	    return ciphertext;
	}
	
	public byte[] decrypt(byte[] ciphertext) throws Exception{
		cipher.init(Cipher.DECRYPT_MODE, key);
		byte[] plaintext = cipher.doFinal(ciphertext);
		return plaintext;
	}
}