package com.kyojin.indie.dto;

import com.kyojin.indie.client.Client;
import com.kyojin.indie.client.ClientAuthentication;
import com.kyojin.indie.client.ClientDownloadRequest;
import com.kyojin.indie.model.Authentication;
import com.kyojin.indie.model.RequestDownload;
import com.kyojin.indie.util.Util;

public class Dto {
	
	Authentication authentication;
	RequestDownload rqDownload;
	ClientAuthentication clientAuthentication;
	ClientDownloadRequest clientDownloadRequest;
	
	public Dto() {
		this.authentication = new Authentication();
		this.rqDownload = new RequestDownload();
		this.clientAuthentication = new ClientAuthentication();
		this.clientDownloadRequest = new ClientDownloadRequest();
		}
	
	public Authentication getAuthentication() {
		return authentication;
	}

	public RequestDownload getRqDownload() {
		return rqDownload;
	}

	public ClientAuthentication getClientAuthentication() {
		return clientAuthentication;
	}

	public ClientDownloadRequest getClientDownloadRequest() {
		return clientDownloadRequest;
	}
	
	
}
