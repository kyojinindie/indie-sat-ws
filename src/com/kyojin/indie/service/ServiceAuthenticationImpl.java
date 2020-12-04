package com.kyojin.indie.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.security.Certificate;
import java.security.InvalidKeyException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Calendar;
import java.util.Enumeration;
import java.util.TimeZone;
import java.util.UUID;

public class ServiceAuthenticationImpl implements ServiceAuthentication {
	
    
	
	@Override
	public String generateCreate() {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
		simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
		Calendar calendarNow = Calendar.getInstance();
		return simpleDateFormat.format(calendarNow.getTime());
	}

	@Override
	public String generateExpires() {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
		simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
		Calendar calendarNow = Calendar.getInstance();
		calendarNow.add(Calendar.SECOND, 300);
		return simpleDateFormat.format(calendarNow.getTime());
	}

	@Override
	public String createDigest(String canonicalTimestamp) throws NoSuchAlgorithmException {
		MessageDigest digest = MessageDigest.getInstance("SHA-1");
		digest.reset();
        digest.update(canonicalTimestamp.getBytes());
		return Base64.getEncoder().encodeToString(digest.digest());
	}

	@Override
	public String generatecanonicalTimestamp(String created, String expires) {
		return "<u:Timestamp xmlns:u=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd\" u:Id=\"_0\">" +
                "<u:Created>" + created + "</u:Created>" +
                "<u:Expires>" + expires + "</u:Expires>" +
                "</u:Timestamp>";
	}

	@Override
	public String generateCanonicalSignedInfo(String digest) {
		return "<SignedInfo xmlns=\"http://www.w3.org/2000/09/xmldsig#\">" +
                "<CanonicalizationMethod Algorithm=\"http://www.w3.org/2001/10/xml-exc-c14n#\"></CanonicalizationMethod>" +
                "<SignatureMethod Algorithm=\"http://www.w3.org/2000/09/xmldsig#rsa-sha1\"></SignatureMethod>" +
                "<Reference URI=\"#_0\">" +
                "<Transforms>" +
                "<Transform Algorithm=\"http://www.w3.org/2001/10/xml-exc-c14n#\"></Transform>" +
                "</Transforms>" +
                "<DigestMethod Algorithm=\"http://www.w3.org/2000/09/xmldsig#sha1\"></DigestMethod>" +
                "<DigestValue>" + digest + "</DigestValue>" +
                "</Reference>" +
                "</SignedInfo>";
	}

	@Override
	public PrivateKey getPrivateKey(File file)
			throws KeyStoreException, NoSuchAlgorithmException,
			CertificateException, FileNotFoundException,
			IOException, UnrecoverableKeyException {	
		KeyStore ks = KeyStore.getInstance("PKCS12");
		ks.load(new FileInputStream(file), null);
		String alias = ks.aliases().nextElement();
		return (PrivateKey) ks.getKey(alias, null);
	}
	
	@Override
	public X509Certificate getCertificate(File file) throws CertificateException, FileNotFoundException  {
		CertificateFactory certFactory = CertificateFactory.getInstance("X.509");
    	FileInputStream is = new FileInputStream(file);
    	
        return  (X509Certificate) certFactory.generateCertificate(is);
	}
	
	@Override
	public String createSing(String canonicalSignedInfo, PrivateKey privateKey)
			throws NoSuchAlgorithmException, InvalidKeyException, SignatureException {
		Signature sig = Signature.getInstance("SHA1WithRSA");
		sig.initSign(privateKey);
		sig.update(canonicalSignedInfo.getBytes());
		return Base64.getEncoder().encodeToString(sig.sign());
	}

	@Override
	public String uuid() {
		return "uuid-" + UUID.randomUUID().toString() + "-1";
	}

	@Override
	public String decodeValue(String value) {
		try {
            return URLDecoder.decode(value, StandardCharsets.UTF_8.toString());
        } catch (UnsupportedEncodingException ex) {
            throw new RuntimeException(ex.getCause());
        }
	}



}
