package com.kyojin.indie.main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.InvalidKeyException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.SignatureException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.kyojin.indie.client.Client;
import com.kyojin.indie.controller.ControllerImpl;
import com.kyojin.indie.model.Authentication;

public class Main {
	
	private final static Logger LOGGER = Logger.getLogger("bitacora.subnivel.Control");
	
	public static void main(String[] args) throws NoSuchAlgorithmException, UnrecoverableKeyException, KeyStoreException, CertificateException, FileNotFoundException, IOException, InvalidKeyException, SignatureException {
		String filePathPFX = "/media/erick/Nuevo vol/IndieKioyin/Proyectos/Descarga-Masiva-SAT/Documentacion/Chu/cuhf950505gj0.pfx";
		String filePathCer = "/media/erick/Nuevo vol/IndieKioyin/Proyectos/Descarga-Masiva-SAT/Documentacion/Chu/cuhf950505gj0.cer";
		String urlAutentica = "https://cfdidescargamasivasolicitud.clouda.sat.gob.mx/Autenticacion/Autenticacion.svc";
	    String urlAutenticaAction = "http://DescargaMasivaTerceros.gob.mx/IAutenticacion/Autentica";
        File filePFX = new File(filePathPFX);
        File fileCer= new File(filePathCer);
		ControllerImpl controller = new ControllerImpl();
		String created = controller.generateCreate();
		String expires = controller.generateExpires();
		String canonicalTimestamp = controller.generatecanonicalTimestamp(created, expires);
		String digest = controller.createDigest(canonicalTimestamp);
		String canonicalSignedInfo = controller.generateCanonicalSignedInfo(digest);
		X509Certificate certificate = controller.getCertificate(fileCer);
		PrivateKey privateKey = controller.getPrivateKey(filePFX);
		String uuid = controller.uuid();
		String signature = controller.createSing(canonicalSignedInfo, privateKey);
		Authentication authentication = new Authentication(created, expires, uuid, digest, signature, certificate);
		String authenticationRq = authentication.createRequest();
		Client client = new Client();
		String response = client.send(new URL(urlAutentica), authenticationRq, urlAutenticaAction);
		String token = "WRAP access_token=\"" + controller.decodeValue(response) + "\"";
		
		
		LOGGER.log(Level.INFO, "Create " + created);
		LOGGER.log(Level.INFO, "Expires " + expires);
		LOGGER.log(Level.INFO, "anonicalTimestamp " + canonicalTimestamp);
		LOGGER.log(Level.INFO, "digest " + digest);
		LOGGER.log(Level.INFO, "canonicalSignedInfo " + canonicalSignedInfo);
		LOGGER.log(Level.INFO, "privateKey " + privateKey);
		LOGGER.log(Level.INFO, "certificate " + certificate);
		LOGGER.log(Level.INFO, "uuid " + uuid);
		LOGGER.log(Level.INFO, "signature " + signature);
		LOGGER.log(Level.INFO, "authenticationRq " + authenticationRq);
		LOGGER.log(Level.INFO, "response " + response);
		LOGGER.log(Level.INFO, "token " + token);
	}

}
