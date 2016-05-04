
public interface Symmetric {
	public byte[] encrypt(byte[] plaintext) throws Exception;
	public byte[] decrypt(byte[] ciphertext) throws Exception;
}
