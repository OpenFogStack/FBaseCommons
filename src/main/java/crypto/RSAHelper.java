package crypto;

import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

import org.javatuples.Pair;

public class RSAHelper {

	public static PrivateKey getPrivateKeyFromString(String secret)
			throws InvalidKeySpecException, NoSuchAlgorithmException {
		byte[] keyBytes = Base64.getDecoder().decode(secret);
		PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(keyBytes);
		KeyFactory kf = KeyFactory.getInstance("RSA");
		return kf.generatePrivate(spec);
	}

	public static PublicKey getPublicKeyFromString(String secret)
			throws NoSuchAlgorithmException, InvalidKeySpecException {
		byte[] keyBytes = Base64.getDecoder().decode(secret);
		X509EncodedKeySpec spec = new X509EncodedKeySpec(keyBytes);
		KeyFactory kf = KeyFactory.getInstance("RSA");
		return kf.generatePublic(spec);
	}

	public static Pair<PublicKey, PrivateKey> generateKeyPair(int keylength)
			throws NoSuchAlgorithmException {
		KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
		keyGen.initialize(keylength);
		KeyPair keyPair = keyGen.generateKeyPair();
		return new Pair<PublicKey, PrivateKey>(keyPair.getPublic(), keyPair.getPrivate());
	}
	
	public static String getEncodedStringFromKey(Key key) {
		return Base64.getEncoder().encodeToString(key.getEncoded());
	}

	public static void main(String[] args) throws NoSuchAlgorithmException {
		System.out.println("Printing keypair");
		Pair<PublicKey, PrivateKey> keys = generateKeyPair(512);
		System.out.println("Public: " + getEncodedStringFromKey(keys.getValue0()));
		System.out.println("Private: " + getEncodedStringFromKey(keys.getValue1()));
	}
	
}
