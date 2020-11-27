package com.kyojin.indie.main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.kyojin.indie.controller.ControllerImpl;

public class Main {
	
	private final static Logger LOGGER = Logger.getLogger("bitacora.subnivel.Control");
	
	public static void main(String[] args) throws NoSuchAlgorithmException, UnrecoverableKeyException, KeyStoreException, CertificateException, FileNotFoundException, IOException {
		String filePathPFX = "/media/erick/Nuevo vol/IndieKioyin/Proyectos/Descarga-Masiva-SAT/Documentacion/Chu/cuhf950505gj0.pfx";
		String filePathCer = "/media/erick/Nuevo vol/IndieKioyin/Proyectos/Descarga-Masiva-SAT/Documentacion/Chu/cuhf950505gj0.cer";
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
		
		LOGGER.log(Level.INFO, "Create " + created);
		LOGGER.log(Level.INFO, "Expires " + expires);
		LOGGER.log(Level.INFO, "anonicalTimestamp " + canonicalTimestamp);
		LOGGER.log(Level.INFO, "digest " + digest);
		LOGGER.log(Level.INFO, "canonicalSignedInfo " + canonicalSignedInfo);
		LOGGER.log(Level.INFO, "privateKey " + privateKey);
		LOGGER.log(Level.INFO, "certificate " + certificate);
	}

}
