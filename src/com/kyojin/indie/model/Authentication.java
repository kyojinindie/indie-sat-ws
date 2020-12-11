package com.kyojin.indie.model;

import java.security.cert.CertificateEncodingException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Calendar;
import java.util.TimeZone;
import java.util.UUID;

public class Authentication extends Request{
	
	private String created;
	private String expires;
	private String uuid;
	
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

	public String generateCreate() {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
		simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
		Calendar calendarNow = Calendar.getInstance();
		return simpleDateFormat.format(calendarNow.getTime());
	}
	
	public String generateExpires() {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
		simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
		Calendar calendarNow = Calendar.getInstance();
		calendarNow.add(Calendar.SECOND, 300);
		return simpleDateFormat.format(calendarNow.getTime());
	}
	
	@Override
	public String generatecanonicalTimestamp() {
		return "<u:Timestamp xmlns:u=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd\" u:Id=\"_0\">" +
                "<u:Created>" + this.created + "</u:Created>" +
                "<u:Expires>" + this.expires + "</u:Expires>" +
                "</u:Timestamp>";
	}

	@Override
	public String generateCanonicalSignedInfo() {
		return "<SignedInfo xmlns=\"http://www.w3.org/2000/09/xmldsig#\">" +
                "<CanonicalizationMethod Algorithm=\"http://www.w3.org/2001/10/xml-exc-c14n#\"></CanonicalizationMethod>" +
                "<SignatureMethod Algorithm=\"http://www.w3.org/2000/09/xmldsig#rsa-sha1\"></SignatureMethod>" +
                "<Reference URI=\"#_0\">" +
                "<Transforms>" +
                "<Transform Algorithm=\"http://www.w3.org/2001/10/xml-exc-c14n#\"></Transform>" +
                "</Transforms>" +
                "<DigestMethod Algorithm=\"http://www.w3.org/2000/09/xmldsig#sha1\"></DigestMethod>" +
                "<DigestValue>" + this.digest + "</DigestValue>" +
                "</Reference>" +
                "</SignedInfo>";
	}
	
	public String generateUuid() {
		return "uuid-" + UUID.randomUUID().toString() + "-1";
	}

	@Override
	public StringBuilder generateRequest() throws CertificateEncodingException {
		// TODO Auto-generated method stub
		return this.request
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
	}
	

	
}
