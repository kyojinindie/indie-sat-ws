package com.kyojin.indie.service;

import java.security.PrivateKey;

public interface Service {
	
	public String generateUUIDCreate();
	
	public String generateUUIDExpires();
	
	public String createDigest(String canonicalTimestamp);
	
	public String createSing(String canonicalSignedInfo,PrivateKey privateKey);
}
