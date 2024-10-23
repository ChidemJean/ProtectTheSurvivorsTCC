package com.psurvivors.utils;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.xml.bind.DatatypeConverter;

public class GenerateToken {
	
	public static String generateToken(String nome, String salt){
		try {
			MessageDigest d = MessageDigest.getInstance("SHA-256");
			try {
				String token = salt + nome;
				for (int i = 0; i < 5; i++){
					byte[] dBy = d.digest(token.getBytes("UTF-8"));
					token = new String(DatatypeConverter.printBase64Binary(dBy));
				}
				return token;
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static String randomSALT() {

		String lista = new String(); 
		String salt = new String();
		
		for (int i = 65; i < 91; i++) { 
			char a = (char) i; 
			lista += String.valueOf(a);
		} 
		for (int i = 48; i < 58; i++) { 
			char a = (char) i; 
			lista += String.valueOf(a); 
		} 
		for (int i = 0; i < 13; i++){
			int r = (int)(Math.random() * lista.length());
			int to = 1 + (int)(Math.random() * 2);
			if (to == 1){
				salt += String.valueOf(lista.charAt(r)).toLowerCase();
			}else {
				salt += lista.charAt(r);
			}
		}
		
		System.out.println("Salt: " + salt);

		return salt;
		
	}
	
}
