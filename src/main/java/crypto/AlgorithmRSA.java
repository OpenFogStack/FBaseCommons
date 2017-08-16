package crypto;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.apache.log4j.Logger;

import exceptions.FBaseEncryptionException;

public class AlgorithmRSA implements IAlgorithm {

	private static Logger logger = Logger.getLogger(AlgorithmRSA.class.getName());

	@Override
	public String encrypt(String toEncrypt, String publicKey) {
		Cipher cipher;
		try {
			cipher = Cipher.getInstance("RSA");
			PublicKey key = RSAHelper.getPublicKeyFromString(publicKey);
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
	public String decrypt(String toDecrypt, String privateKey) {
		Cipher cipher;
		try {
			cipher = Cipher.getInstance("RSA");
			PrivateKey key = RSAHelper.getPrivateKeyFromString(privateKey);
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

	@Override
	public String sign(String content, String privateKey) throws FBaseEncryptionException {
		try {
			Signature privateSignature = Signature.getInstance("SHA256withRSA");
			PrivateKey key = RSAHelper.getPrivateKeyFromString(privateKey);
			privateSignature.initSign(key);
			privateSignature.update(content.getBytes("UTF-8"));
			byte[] signature = privateSignature.sign();
			return Base64.getEncoder().encodeToString(signature);
		} catch (InvalidKeyException | NoSuchAlgorithmException | InvalidKeySpecException
				| SignatureException | UnsupportedEncodingException e) {
			logger.error("Error while signing: " + content);
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean verify(String content, String publicKey, String signature)
			throws FBaseEncryptionException {
		try {
			Signature publicSignature = Signature.getInstance("SHA256withRSA");
			PublicKey key = RSAHelper.getPublicKeyFromString(publicKey);
		    publicSignature.initVerify(key);
		    publicSignature.update(content.getBytes("UTF-8"));
		    byte[] signatureBytes = Base64.getDecoder().decode(signature);
		    return publicSignature.verify(signatureBytes);
		} catch (NoSuchAlgorithmException | InvalidKeySpecException | InvalidKeyException
				| SignatureException | UnsupportedEncodingException e) {
			logger.error("Error while verifying: " + content);
			e.printStackTrace();
		}
		return false;
	}

}
