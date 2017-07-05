package crypto;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

import org.apache.log4j.Logger;

public class AlgorithmAES implements IAlgorithm {

	private static Logger logger = Logger.getLogger(AlgorithmAES.class.getName());

	@Override
	public String encrypt(String toEncrypt, String secret) {
		Cipher cipher;
		try {
			cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
			cipher.init(Cipher.ENCRYPT_MODE, getSecretKeySpec(secret));
			return Base64.getEncoder().encodeToString(cipher.doFinal(toEncrypt.getBytes("UTF-8")));
		} catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException
				| BadPaddingException | UnsupportedEncodingException e) {
			logger.error("Error while encrypting: " + toEncrypt);
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String decrypt(String toDecrypt, String secret) {
		Cipher cipher;
		try {
			cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
			cipher.init(Cipher.DECRYPT_MODE, getSecretKeySpec(secret));
			return new String(cipher.doFinal(Base64.getDecoder().decode(toDecrypt)));
		} catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException
				| BadPaddingException e) {
			logger.error("Error while decrypting: " + toDecrypt);
			e.printStackTrace();
		}
		return null;
	}

	private SecretKeySpec getSecretKeySpec(String secret) {
		MessageDigest sha = null;
		byte[] key = null;
		try {
			key = secret.getBytes("UTF-8");
			sha = MessageDigest.getInstance("SHA-1");
		} catch (UnsupportedEncodingException | NoSuchAlgorithmException e) {
			// There should not be any exceptions, because UTF-8 and SHA-1 exist
			e.printStackTrace();
		}
		key = sha.digest(key);
		key = Arrays.copyOf(key, 16);
		return new SecretKeySpec(key, "AES");
	}

}
