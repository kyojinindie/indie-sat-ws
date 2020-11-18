package com.kyojin.indie.controller;

import java.security.NoSuchAlgorithmException;

import com.kyojin.indie.service.ServiceImpl;

public class ControllerImpl implements Controller {
	
	ServiceImpl serviceImpl = new ServiceImpl();

	@Override
	public String generateUUIDCreate() {
		return serviceImpl.generateCreate();
	}

	@Override
	public String generateUUIDExpires() {
		return serviceImpl.generateExpires();
	}

	@Override
	public String createDigest(String canonicalTimestamp) throws NoSuchAlgorithmException {
		return serviceImpl.createDigest(canonicalTimestamp);
	}

	@Override
	public String generatecanonicalTimestamp(String created, String expires) {
		return serviceImpl.generatecanonicalTimestamp(created, expires);
	}

	@Override
	public String generateCanonicalSignedInfo(String digest) {
		return serviceImpl.generateCanonicalSignedInfo(digest);
	}

}
