
//derived from http://www.java2s.com/Code/Java/Security/Basicsymmetricencryptionexample.htm


import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
public class DESede implements Symmetric {
	private byte[] plaintext;
	private byte[] keyBytes;
	private SecretKeySpec key;
	private Cipher cipher;
	private byte[] ciphertext;

	
	public DESede() throws Exception{
	
		keyBytes = new byte[] { 0x00, 0x01, 0x02, 0x03, 
								0x04, 0x05, 0x06, 0x07, 
								0x00, 0x01, 0x02, 0x03, 
								0x04, 0x05, 0x06, 0x07,
								0x04, 0x05, 0x06, 0x07,
								0x04, 0x05, 0x06, 0x07
		};
	    key = new SecretKeySpec(keyBytes, "DESede");
	    cipher = Cipher.getInstance("DESede");
	   
	}
	
	public byte[] encrypt(byte[] plaintext) throws Exception{
		cipher.init(Cipher.ENCRYPT_MODE, key);
		byte[] ciphertext = cipher.doFinal(plaintext);
	   // System.out.println("ciphertext: " + new String(ciphertext));
	    return ciphertext;
	}
	
	public byte[] decrypt(byte[] ciphertext) throws Exception{
		cipher.init(Cipher.DECRYPT_MODE, key);
		byte[] plaintext = cipher.doFinal(ciphertext);
		return plaintext;
	}
}
