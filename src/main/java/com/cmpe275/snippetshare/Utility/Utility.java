package com.cmpe275.snippetshare.Utility;

import java.security.MessageDigest;
import com.mongodb.util.Base64Codec;

public class Utility {
	
	//Method to encrypt the given plain text
	public static String getEncryptedValue(String plainText){
		String hashedValue = "";
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-1");
			md.update(plainText.getBytes("UTF-16"));
			byte rawByte[] = md.digest();
			hashedValue= (new Base64Codec()).encode(rawByte);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return hashedValue;
	}
}
