package com.kyojin.indie.service;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.SignatureException;

public interface Service {
	
	public String generateCreate();
	
	public String generateExpires();
	
	public String generatecanonicalTimestamp(String created, String expires);
	
	public String createDigest(String canonicalTimestamp) throws NoSuchAlgorithmException;
	
	public String generateCanonicalSignedInfo(String digest);
	
	public String createSing(String canonicalSignedInfo,PrivateKey privateKey)
			throws NoSuchAlgorithmException, InvalidKeyException, SignatureException;
}
