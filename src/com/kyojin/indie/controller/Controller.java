package com.kyojin.indie.controller;

import java.io.IOException;
import java.net.MalformedURLException;
import java.security.InvalidKeyException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

import com.kyojin.indie.client.Client;
import com.kyojin.indie.dto.Dto;
import com.kyojin.indie.model.Download;
import com.kyojin.indie.service.Service;

public class Controller {
	
	Dto authenticate = new Dto();
	Dto requestDownload = new Dto();
	Dto verifyRequest = new Dto();
	Dto download = new Dto();
	Service service = new Service();
	
	public String consumeAuthenticate()
			throws NoSuchAlgorithmException, UnrecoverableKeyException,
			InvalidKeyException, CertificateException, KeyStoreException,
			SignatureException, IOException {
		return service.consumeAuthenticate(authenticate.getAuthentication(), authenticate.getClientAuthentication());
	}
	
	public String consumeDownloadRequest(String authorization)
			throws InvalidKeyException, NoSuchAlgorithmException, SignatureException,
			MalformedURLException, IOException, UnrecoverableKeyException,
			CertificateException, KeyStoreException {
		return service.consumeRequestDownload(requestDownload.getRqDownload(),
				requestDownload.getClientDownloadRequest(), authorization);
	}
	
	public String consumeVerifyRequest(String authorization, String idRequest)
			throws UnrecoverableKeyException, InvalidKeyException,
			CertificateException, KeyStoreException, NoSuchAlgorithmException,
			SignatureException, IOException {
		return service.consumeVerifyRequest(verifyRequest.getVerifyRequest(),
				verifyRequest.getClientVerifyRequest(),
				authorization, idRequest);
	}
	
	public String consumeDownload(String authorization, String idPackage)
			throws CertificateException, UnrecoverableKeyException,
			KeyStoreException, NoSuchAlgorithmException, IOException,
			InvalidKeyException, SignatureException {
		return service.consumeDownload(download.getDownload(), download.getClientDownload(), authorization, idPackage);
	}

}
