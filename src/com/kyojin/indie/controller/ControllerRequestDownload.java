package com.kyojin.indie.controller;

import java.security.PrivateKey;

public interface ControllerRequestDownload {
	public String generatecanonicalTimestamp(String rfcTransmitter,
			String rfcReceiver, String rfcApplicant, String typeRequest);
	
	public String createDigest(String canonicalTimestamp);
	
	public String generateCanonicalSignedInfo(String digest);
	
	public String createSing(String canonicalSignedInfo,PrivateKey privateKey);
}
