package com.kyojin.indie.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.Base64;

public abstract class Request {
	
	protected String digest;
	protected String signature;
	protected File filePFX;
	protected File fileCer;
	protected X509Certificate certificate;
	protected PrivateKey privateKey;
	protected String canonicalTimestamp;
	protected String canonicalSignedInfo;
	protected StringBuilder request;
	
	public Request() {
		this.request = new StringBuilder();
	}
	
	public String getDigest() {
		return digest;
	}

	public void setDigest(String digest) {
		this.digest = digest;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public File getFilePFX() {
		return filePFX;
	}

	public void setFilePFX(File filePFX) {
		this.filePFX = filePFX;
	}

	public File getFileCer() {
		return fileCer;
	}

	public void setFileCer(File fileCer) {
		this.fileCer = fileCer;
	}

	public X509Certificate getCertificate() {
		return certificate;
	}

	public void setCertificate(X509Certificate certificate) {
		this.certificate = certificate;
	}

	public PrivateKey getPrivateKey() {
		return privateKey;
	}

	public void setPrivateKey(PrivateKey privateKey) {
		this.privateKey = privateKey;
	}

	public String getCanonicalTimestamp() {
		return canonicalTimestamp;
	}

	public void setCanonicalTimestamp(String canonicalTimestamp) {
		this.canonicalTimestamp = canonicalTimestamp;
	}

	public String getCanonicalSignedInfo() {
		return canonicalSignedInfo;
	}

	public void setCanonicalSignedInfo(String canonicalSignedInfo) {
		this.canonicalSignedInfo = canonicalSignedInfo;
	}

	public StringBuilder getRequest() {
		return request;
	}

	public void setRequest(StringBuilder request) {
		this.request = request;
	}

	public abstract String generatecanonicalTimestamp();
	
	public String generateDigest()throws NoSuchAlgorithmException {
		MessageDigest digest = MessageDigest.getInstance("SHA-1");
		digest.reset();
        digest.update(this.canonicalTimestamp.getBytes());
		return Base64.getEncoder().encodeToString(digest.digest());
	}
	
	public abstract String generateCanonicalSignedInfo();
	
	public PrivateKey generatePrivateKey()
			throws KeyStoreException, NoSuchAlgorithmException,
			CertificateException, FileNotFoundException,
			IOException, UnrecoverableKeyException {	
		KeyStore ks = KeyStore.getInstance("PKCS12");
		ks.load(new FileInputStream(this.filePFX), null);
		String alias = ks.aliases().nextElement();
		return (PrivateKey) ks.getKey(alias, null);
	}
	
	public X509Certificate generateCertificate() throws CertificateException, FileNotFoundException  {
		CertificateFactory certFactory = CertificateFactory.getInstance("X.509");
    	FileInputStream is = new FileInputStream(this.fileCer);
        return  (X509Certificate) certFactory.generateCertificate(is);
	}
	
	public String generateSing()
			throws NoSuchAlgorithmException, InvalidKeyException, SignatureException{
		Signature sig = Signature.getInstance("SHA1WithRSA");
		sig.initSign(this.privateKey);
		sig.update(this.canonicalSignedInfo.getBytes());
		return Base64.getEncoder().encodeToString(sig.sign());
	}
	
	public abstract StringBuilder generateRequest() throws CertificateEncodingException;
}
