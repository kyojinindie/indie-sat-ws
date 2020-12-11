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
import com.kyojin.indie.model.Download;
import com.kyojin.indie.model.RequestDownload;
import com.kyojin.indie.model.VerifyRequest;
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
		rqDownload.setInitialDate("2020-07-01");
		rqDownload.setFinalDate("2020-12-11");
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
	
	public String consumeVerifyRequest(VerifyRequest verifyRequest, Client client,String authorization, String idRequest)
			throws CertificateException, UnrecoverableKeyException,
			KeyStoreException, NoSuchAlgorithmException, IOException,
			InvalidKeyException, SignatureException {
		verifyRequest.setIdRequest(idRequest);
		verifyRequest.setFilePFX(new File(Util.FILEPATHPFX));
		verifyRequest.setFileCer(new File(Util.FILEPATHCER));
		verifyRequest.setCertificate(verifyRequest.generateCertificate());
		verifyRequest.setPrivateKey(verifyRequest.generatePrivateKey());
		verifyRequest.setRfcApplicant(Util.RFCAPPLICANT);
		verifyRequest.setCanonicalTimestamp(verifyRequest.generatecanonicalTimestamp());
		verifyRequest.setDigest(verifyRequest.generateDigest());
		verifyRequest.setCanonicalSignedInfo(verifyRequest.generateCanonicalSignedInfo());
		verifyRequest.setSignature(verifyRequest.generateSing());
		verifyRequest.setRequest(verifyRequest.generateRequest());
		return client.send(new URL(Util.URLVERIFYREQUEST), verifyRequest.getRequest().toString(),
				Util.URLVERIFYREQUESTACTION, authorization);
	}
	
	public String consumeDownload(Download download, Client client,String authorization, String idPackage)
			throws CertificateException, UnrecoverableKeyException,
			KeyStoreException, NoSuchAlgorithmException, IOException,
			InvalidKeyException, SignatureException {
		download.setIdPackage(idPackage);
		download.setFilePFX(new File(Util.FILEPATHPFX));
		download.setFileCer(new File(Util.FILEPATHCER));
		download.setCertificate(download.generateCertificate());
		download.setPrivateKey(download.generatePrivateKey());
		download.setRfcApplicant(Util.RFCAPPLICANT);
		download.setCanonicalTimestamp(download.generatecanonicalTimestamp());
		download.setDigest(download.generateDigest());
		download.setCanonicalSignedInfo(download.generateCanonicalSignedInfo());
		download.setSignature(download.generateSing());
		download.setRequest(download.generateRequest());
		return client.send(new URL(Util.URLDOWNLOADREQUEST), download.getRequest().toString(),
				Util.URLDOWNLOADREQUESTACTION, authorization);
	}
	
}
