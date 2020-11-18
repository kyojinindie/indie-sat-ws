package com.kyojin.indie.model;

import java.security.cert.CertificateEncodingException;
import java.security.cert.X509Certificate;
import java.util.Base64;

public class Authentication{
	
	private String created;
	private String expires;
	private String uuid;
	private String digest;
	private String signature;
	private X509Certificate certificate;
	private StringBuilder athenticationRq;
	
	public Authentication(String created, String expires, String uuid, String digest, String signature,
			X509Certificate certificate, StringBuilder athenticationRq) {
		super();
		this.created = created;
		this.expires = expires;
		this.uuid = uuid;
		this.digest = digest;
		this.signature = signature;
		this.certificate = certificate;
		this.athenticationRq = athenticationRq;
	}
	
	public Authentication() {}

	public String getCreated() {
		return created;
	}

	public void setCreated(String created) {
		this.created = created;
	}

	public String getExpires() {
		return expires;
	}

	public void setExpires(String expires) {
		this.expires = expires;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getDigest() {
		return digest;
	}

	public void setDigest(String digest) {
		this.digest = digest;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public StringBuilder getAthenticationRq() {
		return athenticationRq;
	}
	
	public X509Certificate getCertificate() {
		return certificate;
	}

	public void setCertificate(X509Certificate certificate) {
		this.certificate = certificate;
	}

	public String createResquest() throws CertificateEncodingException {
		this.athenticationRq
		.append("<s:Envelope xmlns:s=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:u=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd\">")
		.append("<s:Header>")
		.append("<o:Security s:mustUnderstand=\"1\" xmlns:o=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd\">")
		.append("<u:Timestamp u:Id=\"_0\">")
		.append("<u:Created>" + this.created + "</u:Created>")
		.append("<u:Expires>" + this.expires + "</u:Expires>")
		.append("</u:Timestamp>")
		.append("<o:BinarySecurityToken u:Id=\"" + uuid + "\" ValueType=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-x509-token-profile-1.0#X509v3\" EncodingType=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-soap-message-security-1.0#Base64Binary\">")
		.append(Base64.getEncoder().encodeToString(this.certificate.getEncoded()))
		.append("</o:BinarySecurityToken>")
		.append("<Signature xmlns=\"http://www.w3.org/2000/09/xmldsig#\">")
		.append("<SignedInfo>")
		.append("<CanonicalizationMethod Algorithm=\"http://www.w3.org/2001/10/xml-exc-c14n#\"/>")
		.append("<SignatureMethod Algorithm=\"http://www.w3.org/2000/09/xmldsig#rsa-sha1\"/>")
		.append("<Reference URI=\"#_0\">")
		.append("<Transforms>")
		.append("<Transform Algorithm=\"http://www.w3.org/2001/10/xml-exc-c14n#\"/>")
		.append("</Transforms>")
		.append("<DigestMethod Algorithm=\"http://www.w3.org/2000/09/xmldsig#sha1\"/>")
		.append("<DigestValue>" + this.digest + "</DigestValue>")
		.append("</Reference>")
		.append("</SignedInfo>")
		.append("<SignatureValue>" + this.signature + "</SignatureValue>")
		.append("<KeyInfo>")
		.append("<o:SecurityTokenReference>")
		.append("<o:Reference ValueType=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-x509-token-profile-1.0#X509v3\" URI=\"#" + this.uuid + "\"/>")
		.append("</o:SecurityTokenReference>")
		.append("</KeyInfo>")
		.append("</Signature>")
		.append("</o:Security>")
		.append("</s:Header>")
		.append("<s:Body>")
		.append("<Autentica xmlns=\"http://DescargaMasivaTerceros.gob.mx\"/>")
		.append("</s:Body>")
		.append("</s:Envelope>");
		
		return this.athenticationRq.toString();
	}
	
}
