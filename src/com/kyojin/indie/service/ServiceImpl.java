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
	public String generateUUIDCreate() {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
		simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
		Calendar calendarNow = Calendar.getInstance();
		return simpleDateFormat.format(calendarNow.getTime());
	}

	@Override
	public String generateUUIDExpires() {
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

}
