package com.kyojin.indie.controller;

import java.io.IOException;
import java.net.MalformedURLException;
import java.security.InvalidKeyException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

import com.kyojin.indie.dto.Dto;
import com.kyojin.indie.service.Service;

public class Controller {
	
	Dto authenticate = new Dto();
	Dto requestDownload = new Dto();
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

}
