package crypto;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.apache.log4j.Logger;

public class AlgorithmRSA_PrivateEncrypt implements IAlgorithm {

	private static Logger logger = Logger.getLogger(AlgorithmRSA_PrivateEncrypt.class.getName());

	@Override
	public String encrypt(String toEncrypt, String secret) {
		Cipher cipher;
		try {
			cipher = Cipher.getInstance("RSA");
			PrivateKey key = RSAHelper.getPrivateKeyFromString(secret);
			cipher.init(Cipher.ENCRYPT_MODE, key);
			return Base64.getEncoder().encodeToString(cipher.doFinal(toEncrypt.getBytes("UTF-8")));
		} catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException
				| IllegalBlockSizeException | BadPaddingException | UnsupportedEncodingException
				| InvalidKeySpecException e) {
			logger.error("Error while encrypting: " + toEncrypt);
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String decrypt(String toDecrypt, String secret) {
		Cipher cipher;
		try {
			cipher = Cipher.getInstance("RSA");
			PublicKey key = RSAHelper.getPublicKeyFromString(secret);
			cipher.init(Cipher.DECRYPT_MODE, key);
			return new String(cipher.doFinal(Base64.getDecoder().decode(toDecrypt)));
		} catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException
				| IllegalBlockSizeException | BadPaddingException | IllegalArgumentException
				| InvalidKeySpecException e) {
			logger.error("Error while decrypting: " + toDecrypt);
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public AlgorithmType getType() {
		return AlgorithmType.ASYMMETRIC;
	}

}
