package com.kyojin.indie.controller;

import java.security.NoSuchAlgorithmException;

public interface Controller {
	public String generateUUIDCreate();

	public String generateUUIDExpires();
	
	public String generatecanonicalTimestamp(String created, String expires);
	
	public String createDigest(String canonicalTimestamp) throws NoSuchAlgorithmException;
}
