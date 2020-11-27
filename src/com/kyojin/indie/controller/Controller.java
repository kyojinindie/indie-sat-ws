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

public interface Controller {
	public String generateCreate();

	public String generateExpires();
	
	public String uuid ();
	
	public String generatecanonicalTimestamp(String created, String expires);
	
	public String createDigest(String canonicalTimestamp) throws NoSuchAlgorithmException;
	
	public String generateCanonicalSignedInfo(String digest);
	
	public PrivateKey getPrivateKey(File file)
			throws UnrecoverableKeyException, KeyStoreException,
			NoSuchAlgorithmException, CertificateException, FileNotFoundException, IOException;
	
	public X509Certificate getCertificate(File file)
			 throws CertificateException, FileNotFoundException;
	
	public String createSing(String canonicalSignedInfo,PrivateKey privateKey)
			throws InvalidKeyException, NoSuchAlgorithmException, SignatureException;
}
