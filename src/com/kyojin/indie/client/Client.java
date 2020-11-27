package com.kyojin.indie.client;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;

public class Client {
	public String send(URL url, String request, String SOAPAction) throws IOException {
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		
		 // Set timeout as per needs
        conn.setConnectTimeout(20000);
        conn.setReadTimeout(20000);
        
        // Set DoOutput to true if you want to use URLConnection for output.
        // Default is false
        conn.setDoOutput(true);
        
        // Set Headers
        conn.setRequestProperty("Accept-Charset", "UTF_8");
        conn.setRequestProperty("Content-type", "text/xml; charset=utf-8");
        conn.setRequestProperty("SOAPAction", SOAPAction);
        
        // Write XML
        OutputStream outputStream = conn.getOutputStream();
        outputStream.write(request.getBytes(StandardCharsets.UTF_8));
        outputStream.flush();
        outputStream.close();

        InputStream inputStream = conn.getInputStream();
        // Read XML
        byte[] res = new byte[2048];
        int i;
        StringBuilder response = new StringBuilder();
        while ((i = inputStream.read(res)) != -1) {
            response.append(new String(res, 0, i));
        }
        inputStream.close();
		return getResult(response.toString());
		
	}
	
	public String getResult(String xmlResponse) {
        Document doc = convertStringToXMLDocument(xmlResponse);

        //Verify XML document is build correctly
        if (doc != null)
            return doc.getElementsByTagName("AutenticaResult").item(0).getTextContent();

        return null;
    }
	
	public  Document convertStringToXMLDocument(String xmlString) {
        //Parser that produces DOM object trees from XML content
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        //API to obtain DOM Document instance
        DocumentBuilder builder;
        try {
            //Create DocumentBuilder with default configuration
            builder = factory.newDocumentBuilder();

            //Parse the content to Document object
            return builder.parse(new InputSource(new StringReader(xmlString)));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
