package br.com.oma.intranet.util;

import java.io.Serializable;
import java.net.URLEncoder;
import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

public class AESencrp implements Serializable{

	private static final long serialVersionUID = 1L;

	private static final String ALGO = "AES";
	/*
	 * private static final byte[] KEY = new byte[] { 'T', 'h', 'e', 'B', 'e',
	 * 's', 't', 'S', 'e', 'c', 'r', 'e', 't', 'K', 'e', 'y' };
	 */

	private static final byte[] KEY = new byte[] { 'O', 'm', 'a', '@', '@', '-', '6', '3', '0', '5', '9', '*', 'X', 'A', 'Z', 'D', };

	public static String encrypt(String Data) throws Exception {
		Key key = generateKey();
		Cipher c = Cipher.getInstance(ALGO);
		c.init(Cipher.ENCRYPT_MODE, key);
		byte[] encVal = c.doFinal(Data.getBytes());
		String encryptedValue = Base64.encodeBase64String(encVal);
		String urlEncodeddata = URLEncoder.encode(encryptedValue, "UTF-8");
		return urlEncodeddata;
	}

	public static String decrypt(String encryptedData) throws Exception {
		Key key = generateKey();
		Cipher c = Cipher.getInstance(ALGO);
		c.init(Cipher.DECRYPT_MODE, key);
		byte[] decordedValue = Base64.decodeBase64(encryptedData);
		byte[] decValue = c.doFinal(decordedValue);
		String decryptedValue = new String(decValue);
		return decryptedValue;
	}

	private static Key generateKey() throws Exception {
		Key key = new SecretKeySpec(KEY, ALGO);
		return key;
	}

}
