//derived from http://www.java2s.com/Code/Java/Security/Basicsymmetricencryptionexample.htm


import javax.crypto.Cipher;
import javax.crypto.spec.RC5ParameterSpec;
import javax.crypto.spec.SecretKeySpec;
public class RC5 implements Symmetric {
	private byte[] plaintext;
	private byte[] keyBytes;
	private SecretKeySpec key;
	private Cipher cipher;
	private byte[] ciphertext;
	private RC5ParameterSpec spec;
	
	public RC5() throws Exception{
	
		keyBytes = new byte[] { 0x00, 0x01, 0x02, 0x03, 
								0x04, 0x05, 0x06, 0x07, 
								0x08, 0x09, 0x0a, 0x0b, 
								0x0c, 0x0d, 0x0e, 0x0f };
	    key = new SecretKeySpec(keyBytes, "RC5");
	    spec = new RC5ParameterSpec(1, 12, 16, new byte[8]);
	    cipher = Cipher.getInstance("RC5");
	   
	}
	
	public byte[] encrypt(byte[] plaintext) throws Exception{		
		cipher.init(Cipher.ENCRYPT_MODE, key, spec);
		byte[] ciphertext = cipher.doFinal(plaintext);
	   // System.out.println("ciphertext: " + new String(ciphertext));
	    return ciphertext;
	}
	
	public byte[] decrypt(byte[] ciphertext) throws Exception{
		cipher.init(Cipher.DECRYPT_MODE, key, spec);
		byte[] plaintext = cipher.doFinal(ciphertext);
		return plaintext;
	}
}