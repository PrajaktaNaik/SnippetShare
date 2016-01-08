package com.cmpe275.snippetshare.Utility;

import java.security.MessageDigest;

import org.apache.commons.codec.binary.Base64;

import com.mongodb.util.*;
public class Utility {

	// Method to encrypt the given plain text
	public static String getEncryptedValue(String plainText) {
		String hashedValue = "";
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-1");
			md.update(plainText.getBytes("UTF-16"));
			byte rawByte[] = md.digest();
			hashedValue = Base64.encodeBase64URLSafeString(rawByte);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return hashedValue;
	}
}
