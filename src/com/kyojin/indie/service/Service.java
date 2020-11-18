package com.kyojin.indie.service;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.SignatureException;

public interface Service {
	
	public String generateUUIDCreate();
	
	public String generateUUIDExpires();
	
	public String createDigest(String canonicalTimestamp) throws NoSuchAlgorithmException;
	
	public String createSing(String canonicalSignedInfo,PrivateKey privateKey)
			throws NoSuchAlgorithmException, InvalidKeyException, SignatureException;
}
