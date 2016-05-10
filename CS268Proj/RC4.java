
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

		keyBytes = new byte[] { 0x00, 0x01, 0x02, 0x03, 
				0x04, 0x05, 0x06, };
		
		keyBytes = new byte[] { 0x00, 0x01, 0x02, 0x03, 
								0x04, 0x05, 0x06, 0x07, 
							
			};
	   
		keyBytes = new byte[] { 0x00, 0x01, 0x02, 0x03, 
								0x04, 0x05, 0x06, 0x07, 
								0x08, 0x09, 0x0a, 0x0b, 
								0x0c, 0x0d, 0x0e, 0x0f, 
								0x00, 0x01, 0x02, 0x03, 
								0x04, 0x05, 0x06, 0x07, 
								};
//		
//		keyBytes = new byte[] { 0x00, 0x01, 0x02, 0x03, 
//								0x04, 0x05, 0x06, 0x07, 
//								0x08, 0x09, 0x0a, 0x0b, 
//								0x0a, 0x0d, 0x09, 0x0f,
//								0x06, 0x05, 0x0a, 0x07,  
//								0x00, 0x01, 0x02, 0x03,
//								0x03, 0x0d, 0x04, 0x0f,
//								0x00, 0x01, 0x02, 0x03};
		
		
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