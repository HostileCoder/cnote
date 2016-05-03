
//derived from http://www.java2s.com/Code/Java/Security/Basicsymmetricencryptionexample.htm


import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
public class AES implements Symmetric {
	private byte[] plaintext;
	private byte[] keyBytes;
	private SecretKeySpec key;
	private Cipher cipher;
	private byte[] ciphertext;

	
	public AES() throws Exception{
	
		keyBytes = new byte[] { 0x00, 0x01, 0x02, 0x03, 
				0x04, 0x05, 0x06, 0x07, 
				0x08, 0x09, 0x0a, 0x0b, 
				0x0c, 0x0d, 0x0e, 0x0f };
	   
//		keyBytes = new byte[] { 0x00, 0x01, 0x02, 0x03, 
//								0x04, 0x05, 0x06, 0x07, 
//								0x08, 0x09, 0x0a, 0x0b, 
//								0x0a, 0x0d, 0x09, 0x0f,
//								0x0c, 0x05, 0x0e, 0x0f,
//								0x0f, 0x0d, 0x04, 0x0f};
		
//		keyBytes = new byte[] { 0x00, 0x01, 0x02, 0x03, 
//								0x04, 0x05, 0x06, 0x07, 
//								0x08, 0x09, 0x0a, 0x0b, 
//								0x0c, 0x0d, 0x0e, 0x0f, 
//								0x00, 0x01, 0x02, 0x03, 
//								0x04, 0x05, 0x06, 0x07, 
//								0x08, 0x09, 0x0a, 0x0b, 
//								0x0c, 0x0d, 0x0e, 0x0f };
		
	    cipher = Cipher.getInstance("AES/CBC/NoPadding", "SunJCE");
		//cipher = Cipher.getInstance("AES");
		key = new SecretKeySpec(keyBytes, "AES");
	   
	   
	}
	
	public byte[] encrypt(byte[] plaintext) throws Exception{
//		cipher.init(Cipher.ENCRYPT_MODE, key);
//		byte[] ciphertext = cipher.doFinal(plaintext);
//		plaintext="test text 123\0\0\0".getBytes();
	    cipher.init(Cipher.ENCRYPT_MODE, key,new IvParameterSpec("AAAAAAAAAAAAAAAA".getBytes()));
	    byte[] ciphertext =  cipher.doFinal(plaintext);

	    return ciphertext;
	}
	
	public byte[]  decrypt(byte[] ciphertext) throws Exception{
//		cipher.init(Cipher.DECRYPT_MODE, key);
//		byte[] plaintext = cipher.doFinal(ciphertext);
		
		
	    cipher.init(Cipher.DECRYPT_MODE, key,new IvParameterSpec("AAAAAAAAAAAAAAAA".getBytes()));
	    byte[] plaintext = cipher.doFinal(ciphertext);
	    return plaintext;
	}
}
