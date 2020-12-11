package com.kyojin.indie.main;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.kyojin.indie.controller.Controller;

public class Main {
	
	private final static Logger LOGGER = Logger.getLogger("bitacora.subnivel.Control");
	
	public static void main(String[] args) throws NoSuchAlgorithmException, UnrecoverableKeyException, KeyStoreException, CertificateException, FileNotFoundException, IOException, InvalidKeyException, SignatureException {
		
		Controller controller = new Controller();
		
		String token = controller.consumeAuthenticate();
		String idRequest = controller.consumeDownloadRequest(token);
		String idPackages = controller.consumeVerifyRequest(token, idRequest);
        String packageString = controller.consumeDownload(token, idPackages);
		
		LOGGER.log(Level.INFO, "Token: " + token);
		LOGGER.log(Level.INFO, "Id Request: " + idRequest);
		LOGGER.log(Level.INFO, "Id Packages: " + idPackages);
		LOGGER.log(Level.INFO, "packageString: " + packageString);
	}

}
