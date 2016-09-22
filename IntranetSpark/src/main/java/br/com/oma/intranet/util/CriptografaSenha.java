package br.com.oma.intranet.util;

import java.io.Serializable;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class CriptografaSenha implements Serializable{

	private static final long serialVersionUID = 1L;

	public String retornaSenhaCript(String senha) throws NoSuchAlgorithmException{
		MessageDigest md = MessageDigest.getInstance("MD5"); // or "SHA -1"
		md.update(senha.getBytes());
		BigInteger hash = new BigInteger(1, md.digest());
		String senhaCriptografada = hash.toString(16);
		while (senhaCriptografada.length() < 32) { // 40 for SHA -1
			senhaCriptografada = "0" + senhaCriptografada;
		}
		return senhaCriptografada;
	}
}
