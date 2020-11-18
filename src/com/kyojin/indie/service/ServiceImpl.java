package com.kyojin.indie.service;

import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.Signature;
import java.security.SignatureException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Calendar;
import java.util.TimeZone;

public class ServiceImpl implements Service {

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
	public String createSing(String canonicalSignedInfo, PrivateKey privateKey)
			throws NoSuchAlgorithmException, InvalidKeyException, SignatureException {
		Signature sig = Signature.getInstance("SHA1WithRSA");
		sig.initSign(privateKey);
		sig.update(canonicalSignedInfo.getBytes());
		return Base64.getEncoder().encodeToString(sig.sign());
	}

	@Override
	public String generatecanonicalTimestamp(String created, String expires) {
		// TODO Auto-generated method stub
		return "<u:Timestamp xmlns:u=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd\" u:Id=\"_0\">" +
                "<u:Created>" + created + "</u:Created>" +
                "<u:Expires>" + expires + "</u:Expires>" +
                "</u:Timestamp>";
	}

}
