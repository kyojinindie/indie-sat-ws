package com.kyojin.indie.service;

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

public interface Service {
	
	public String generateCreate();
	
	public String generateExpires();
	
	public String uuid ();
	
	public String generatecanonicalTimestamp(String created, String expires);
	
	public String createDigest(String canonicalTimestamp) throws NoSuchAlgorithmException;
	
	public String generateCanonicalSignedInfo(String digest);
	
	public PrivateKey getPrivateKey(File file)
			throws KeyStoreException, NoSuchAlgorithmException, CertificateException,
			FileNotFoundException, IOException, UnrecoverableKeyException;
	
	public X509Certificate getCertificate(File file)
			throws CertificateException, FileNotFoundException;
	
	public String createSing(String canonicalSignedInfo,PrivateKey privateKey)
			throws NoSuchAlgorithmException, InvalidKeyException, SignatureException;
	
	public String decodeValue(String value);
}
