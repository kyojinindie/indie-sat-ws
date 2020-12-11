package com.kyojin.indie.client;

import org.w3c.dom.Document;

public class ClientAuthentication extends Client {

	@Override
	public String getResult(String xmlResponse) {
		 Document doc = convertStringToXMLDocument(xmlResponse);
	        //Verify XML document is build correctly
	        if (doc != null)
	            return doc.getElementsByTagName("AutenticaResult").item(0).getTextContent();

	        return null;
	}

}
