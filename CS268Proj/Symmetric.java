
public interface Symmetric {
	public byte[] encrypt(byte[] plaintext) throws Exception;
	public void decrypt(byte[] ciphertext) throws Exception;
}
