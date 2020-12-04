package com.kyojin.indie.service;

import java.security.PrivateKey;

public interface ServiceRequestDownload {
	
	public String generatecanonicalTimestamp(String rfcTransmitter,
			String rfcReceiver, String rfcApplicant, String typeRequest);
	
	public String createDigest(String canonicalTimestamp);
	
	public String generateCanonicalSignedInfo(String digest);
	
	public String createSing(String canonicalSignedInfo,PrivateKey privateKey);

}
