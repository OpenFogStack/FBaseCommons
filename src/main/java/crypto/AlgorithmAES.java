package crypto;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;
import java.util.Random;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

import org.apache.log4j.Logger;

import exceptions.FBaseEncryptionException;

public class AlgorithmAES implements IAlgorithm {

	private static Logger logger = Logger.getLogger(AlgorithmAES.class.getName());

	@Override
	public String encrypt(String toEncrypt, String secret) {
		Cipher cipher;
		try {
			cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
			cipher.init(Cipher.ENCRYPT_MODE, getSecretKeySpec(secret));
			return Base64.getEncoder().encodeToString(cipher.doFinal(toEncrypt.getBytes("UTF-8")));
		} catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException
				| IllegalBlockSizeException | BadPaddingException
				| UnsupportedEncodingException e) {
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
		} catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException
				| IllegalBlockSizeException | BadPaddingException | IllegalArgumentException e) {
			logger.error("Error while decrypting: " + toDecrypt);
			e.printStackTrace();
		}
		return null;
	}

	public static String generateNewSecret() {
		String possibleCharacterString = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ"
				+ "1234567890!§$%&/()=?¿≠}{|][¢¶“¡";
		char[] chars = possibleCharacterString.toCharArray();
		StringBuilder builder = new StringBuilder();
		Random random = new Random();
		for (int i = 0; i < 22; i++) {
			char c = chars[random.nextInt(chars.length)];
			builder.append(c);
		}
		String output = builder.toString();
		return output;
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

	@Override
	public AlgorithmType getType() {
		return AlgorithmType.SYMMETRIC;
	}

	@Override
	public String sign(String content, String privateKey) throws FBaseEncryptionException {
		throw new FBaseEncryptionException(FBaseEncryptionException.NOT_SUPPORTED);
	}

	@Override
	public boolean verify(String content, String publicKey, String signature)
			throws FBaseEncryptionException {
		throw new FBaseEncryptionException(FBaseEncryptionException.NOT_SUPPORTED);
	}

}
