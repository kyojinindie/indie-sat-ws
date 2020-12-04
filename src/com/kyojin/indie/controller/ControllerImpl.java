package com.kyojin.indie.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.SignatureException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import com.kyojin.indie.service.ServiceImpl;

public class ControllerImpl implements Controller {
	
	ServiceImpl serviceImpl = new ServiceImpl();

	@Override
	public String generateCreate() {
		return serviceImpl.generateCreate();
	}

	@Override
	public String generateExpires() {
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

	@Override
	public String createSing(String canonicalSignedInfo, PrivateKey privateKey) throws InvalidKeyException, NoSuchAlgorithmException, SignatureException {
		return serviceImpl.createSing(canonicalSignedInfo, privateKey);
	}

	@Override
	public PrivateKey getPrivateKey(File file)
			throws UnrecoverableKeyException, KeyStoreException, NoSuchAlgorithmException,
			CertificateException, FileNotFoundException, IOException {
		return serviceImpl.getPrivateKey(file);
	}

	@Override
	public X509Certificate getCertificate(File file) throws CertificateException, FileNotFoundException {
		return serviceImpl.getCertificate(file);
	}

	@Override
	public String uuid() {
		return serviceImpl.uuid();
	}

	@Override
	public String decodeValue(String value) {
		return serviceImpl.decodeValue(value);
	}

}
