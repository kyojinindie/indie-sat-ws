package com.kyojin.indie.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.InvalidKeyException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;

import com.kyojin.indie.client.Client;
import com.kyojin.indie.model.Authentication;
import com.kyojin.indie.model.RequestDownload;
import com.kyojin.indie.util.Util;

public class Service {
	public String consumeAuthenticate(Authentication auth,Client client)
			throws NoSuchAlgorithmException, CertificateException,
			UnrecoverableKeyException, KeyStoreException, IOException,
			InvalidKeyException, SignatureException {
		auth.setFilePFX(new File(Util.FILEPATHPFX));
		auth.setFileCer(new File(Util.FILEPATHCER));
		auth.setCreated(auth.generateCreate());
		auth.setExpires(auth.generateExpires());
		auth.setCanonicalTimestamp(auth.generatecanonicalTimestamp());
		auth.setDigest(auth.generateDigest());
		auth.setCanonicalSignedInfo(auth.generateCanonicalSignedInfo());
		auth.setCertificate(auth.generateCertificate());
		auth.setPrivateKey(auth.generatePrivateKey());
		auth.setUuid(auth.generateUuid());
		auth.setSignature(auth.generateSing());
		auth.setRequest(auth.generateRequest());
		
		return "WRAP access_token=\"" 
		+ client.send(new URL(Util.URLAUTHENTICATE),
				auth.getRequest().toString(), Util.URLAUTHENTICATEACTION, null) + "\"";
	}
	
	public String consumeRequestDownload(RequestDownload rqDownload, Client client, String authorization)
			throws NoSuchAlgorithmException, InvalidKeyException,
			SignatureException, MalformedURLException, IOException,
			CertificateException, UnrecoverableKeyException, KeyStoreException {
		rqDownload.setFilePFX(new File(Util.FILEPATHPFX));
		rqDownload.setFileCer(new File(Util.FILEPATHCER));
		rqDownload.setCertificate(rqDownload.generateCertificate());
		rqDownload.setPrivateKey(rqDownload.generatePrivateKey());
		rqDownload.setInitialDate("2020-08-24");
		rqDownload.setFinalDate("2020-08-24");
		rqDownload.setRfcApplicant(Util.RFCAPPLICANT);
		rqDownload.setRfcReceiver(Util.RFCRECEIVER);
		rqDownload.setRfcTransmitter(Util.RFCTRANSMITER);
		rqDownload.setTypeRequest(Util.TYPEREQUEST);
		rqDownload.setCanonicalTimestamp(rqDownload.generatecanonicalTimestamp());
		rqDownload.setDigest(rqDownload.generateDigest());
		rqDownload.setCanonicalSignedInfo(rqDownload.generateCanonicalSignedInfo());
		rqDownload.setSignature(rqDownload.generateSing());
		rqDownload.setRequest(rqDownload.generateRequest());
		return client.send(new URL(Util.URLREQUESTDOWNLOAD), rqDownload.getRequest().toString(),
				Util.URLREQUESTDOWNLOADACTION, authorization);
	}
	
	
}
