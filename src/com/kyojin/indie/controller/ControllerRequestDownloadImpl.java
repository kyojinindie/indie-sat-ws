package com.kyojin.indie.controller;

import java.security.PrivateKey;

public class ControllerRequestDownloadImpl implements ControllerRequestDownload {

	@Override
	public String generatecanonicalTimestamp(String rfcTransmitter, String rfcReceiver, String rfcApplicant,
			String typeRequest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String createDigest(String canonicalTimestamp) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String generateCanonicalSignedInfo(String digest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String createSing(String canonicalSignedInfo, PrivateKey privateKey) {
		// TODO Auto-generated method stub
		return null;
	}

}
