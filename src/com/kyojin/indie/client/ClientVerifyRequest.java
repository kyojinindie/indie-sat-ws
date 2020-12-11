package com.kyojin.indie.client;

import org.w3c.dom.Document;

public class ClientVerifyRequest extends Client {

	@Override
	public String getResult(String xmlResponse) {
		Document doc = convertStringToXMLDocument(xmlResponse);

        //Verify XML document is build correctly
        if (doc != null) {
            int stateRequest = Integer.parseInt(doc.getElementsByTagName("VerificaSolicitudDescargaResult")
                    .item(0)
                    .getAttributes()
                    .getNamedItem("EstadoSolicitud").getTextContent());

            if (stateRequest == 3)
                return doc.getElementsByTagName("IdsPaquetes").item(0).getTextContent();
        }

        return null;
	}

}
