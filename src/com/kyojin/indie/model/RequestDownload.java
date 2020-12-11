package com.kyojin.indie.model;


import java.security.cert.CertificateEncodingException;
import java.util.Base64;

public class RequestDownload extends Request{
	
	private String rfcTransmitter;
	private String rfcReceiver;
	private String rfcApplicant;
	private String initialDate;
	private String finalDate;
	private String typeRequest;
		
	public RequestDownload() {
	}

	public String getRfcTransmitter() {
		return rfcTransmitter;
	}

	public void setRfcTransmitter(String rfcTransmitter) {
		this.rfcTransmitter = rfcTransmitter;
	}

	public String getRfcReceiver() {
		return rfcReceiver;
	}

	public void setRfcReceiver(String rfcReceiver) {
		this.rfcReceiver = rfcReceiver;
	}

	public String getRfcApplicant() {
		return rfcApplicant;
	}

	public void setRfcApplicant(String rfcApplicant) {
		this.rfcApplicant = rfcApplicant;
	}

	public String getInitialDate() {
		return initialDate;
	}

	public void setInitialDate(String initialDate) {
		this.initialDate = initialDate + "T00:00:00";
	}

	public String getFinalDate() {
		return finalDate;
	}

	public void setFinalDate(String finalDate) {
		this.finalDate = finalDate + "T23:59:59";
	}

	public String getTypeRequest() {
		return typeRequest;
	}

	public void setTypeRequest(String typeRequest) {
		this.typeRequest = typeRequest;
	}

	@Override
	public String generatecanonicalTimestamp() {
		
		return  "<des:SolicitaDescarga xmlns:des=\"http://DescargaMasivaTerceros.sat.gob.mx\">" +
                "<des:solicitud RfcEmisor=\"" + this.rfcTransmitter + "\" RfcReceptor=\"" + this.rfcReceiver + "\" RfcSolicitante=\"" + this.rfcApplicant + "\" FechaInicial=\"" + this.initialDate + "\" FechaFinal=\"" + this.finalDate + "\" TipoSolicitud=\"" + this.typeRequest + "\">" +
                "</des:solicitud>" +
                "</des:SolicitaDescarga>";
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


	@Override
	public StringBuilder generateRequest() throws CertificateEncodingException {
		return this.request
				.append("<s:Envelope xmlns:s=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:u=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd\" xmlns:des=\"http://DescargaMasivaTerceros.sat.gob.mx\" xmlns:xd=\"http://www.w3.org/2000/09/xmldsig#\">")
				.append("<s:Header/>")
				.append("<s:Body>")
				.append("<des:SolicitaDescarga>")
				.append("<des:solicitud RfcEmisor=\"" + this.rfcTransmitter + "\" RfcReceptor =\"" + this.rfcReceiver + "\" RfcSolicitante=\"" + this.rfcApplicant + "\" FechaInicial=\"" + this.initialDate + "\" FechaFinal =\"" + this.finalDate + "\" TipoSolicitud=\"" + this.typeRequest + "\">")
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
				.append("<X509Data>")
				.append("<X509IssuerSerial>")
				.append("<X509IssuerName>" + this.certificate.getIssuerX500Principal() + "</X509IssuerName>")
				.append("<X509SerialNumber>" + this.certificate.getSerialNumber() + "</X509SerialNumber>")
				.append("</X509IssuerSerial>")
				.append("<X509Certificate>" + Base64.getEncoder().encodeToString(this.certificate.getEncoded()) + "</X509Certificate>")
				.append("</X509Data>")
				.append("</KeyInfo>")
				.append("</Signature>")
				.append("</des:solicitud>")
				.append("</des:SolicitaDescarga>")
				.append("</s:Body>")
				.append("</s:Envelope>");
	}
	
	
}
