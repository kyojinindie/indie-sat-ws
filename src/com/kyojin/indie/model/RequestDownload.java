package com.kyojin.indie.model;

import java.security.cert.CertificateEncodingException;
import java.security.cert.X509Certificate;
import java.util.Base64;

public class RequestDownload {
	private X509Certificate certificate;
	private String rfcEmisor;
	private String rfcReceptor;
	private String rfcSolicitante;
	private String fechaInicial;
    private String fechaFinal;
    private String typeRequest;
	private String digest;
	private String signature;
	private StringBuilder requestDownload;
	
	
	
	public RequestDownload(X509Certificate certificate, String rfcEmisor, String rfcReceptor, String rfcSolicitante,
			String fechaInicial, String fechaFinal, String typeRequest, String digest, String signature) {
		super();
		this.certificate = certificate;
		this.rfcEmisor = rfcEmisor;
		this.rfcReceptor = rfcReceptor;
		this.rfcSolicitante = rfcSolicitante;
		this.fechaInicial = fechaInicial  + "T00:00:00";
		this.fechaFinal = fechaFinal + "T23:59:59";
		this.typeRequest = typeRequest;
		this.digest = digest;
		this.signature = signature;
		this.requestDownload = new StringBuilder();
	}



	public String createRequest() throws CertificateEncodingException {
		this.requestDownload
		.append("<s:Envelope xmlns:s=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:u=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd\" xmlns:des=\"http://DescargaMasivaTerceros.sat.gob.mx\" xmlns:xd=\"http://www.w3.org/2000/09/xmldsig#\">")
		.append("<s:Header/>")
		.append("<s:Body>")
		.append("<des:SolicitaDescarga>")
		.append("<des:solicitud RfcEmisor=\"" + rfcEmisor + "\" RfcReceptor =\"" + rfcReceptor + "\" RfcSolicitante=\"" + rfcSolicitante + "\" FechaInicial=\"" + fechaInicial + "\" FechaFinal =\"" + fechaFinal + "\" TipoSolicitud=\"" + this.typeRequest + "\">")
		.append("<Signature xmlns=\"http://www.w3.org/2000/09/xmldsig#\">")
		.append("<SignedInfo>")
		.append("<CanonicalizationMethod Algorithm=\"http://www.w3.org/2001/10/xml-exc-c14n#\"/>")
		.append("<SignatureMethod Algorithm=\"http://www.w3.org/2000/09/xmldsig#rsa-sha1\"/>")
		.append("<Reference URI=\"#_0\">")
		.append("<Transforms>")
		.append("<Transform Algorithm=\"http://www.w3.org/2001/10/xml-exc-c14n#\"/>")
		.append("</Transforms>")
		.append("<DigestMethod Algorithm=\"http://www.w3.org/2000/09/xmldsig#sha1\"/>")
		.append("<DigestValue>" + digest + "</DigestValue>")
		.append("</Reference>")
		.append("</SignedInfo>")
		.append("<SignatureValue>" + signature + "</SignatureValue>")
		.append("<KeyInfo>")
		.append("<X509Data>")
		.append("<X509IssuerSerial>")
		.append("<X509IssuerName>" + certificate.getIssuerX500Principal() + "</X509IssuerName>")
		.append("<X509SerialNumber>" + certificate.getSerialNumber() + "</X509SerialNumber>")
		.append("</X509IssuerSerial>")
		.append("<X509Certificate>" + Base64.getEncoder().encodeToString(certificate.getEncoded()) + "</X509Certificate>")
		.append("</X509Data>")
		.append("</KeyInfo>")
		.append("</Signature>")
		.append("</des:solicitud>")
		.append("</des:SolicitaDescarga>")
		.append("</s:Body>")
		.append("</s:Envelope>");
		return this.requestDownload.toString();
	}
	
	
}
