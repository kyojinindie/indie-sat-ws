package com.kyojin.indie.dto;

import com.kyojin.indie.client.ClientAuthentication;
import com.kyojin.indie.client.ClientDownload;
import com.kyojin.indie.client.ClientDownloadRequest;
import com.kyojin.indie.client.ClientVerifyRequest;
import com.kyojin.indie.model.Authentication;
import com.kyojin.indie.model.Download;
import com.kyojin.indie.model.RequestDownload;
import com.kyojin.indie.model.VerifyRequest;

public class Dto {
	
	private Authentication authentication;
	private RequestDownload rqDownload;
	private VerifyRequest verifyRequest;
	private Download download;
	private ClientAuthentication clientAuthentication;
	private ClientDownloadRequest clientDownloadRequest;
	private ClientVerifyRequest clientVerifyRequest;
	private ClientDownload clientDownload;
	
	public Dto() {
		this.authentication = new Authentication();
		this.rqDownload = new RequestDownload();
		this.verifyRequest = new VerifyRequest();
		this.download = new Download();
		this.clientVerifyRequest = new ClientVerifyRequest();
		this.clientAuthentication = new ClientAuthentication();
		this.clientDownloadRequest = new ClientDownloadRequest();
		this.clientDownload = new ClientDownload();
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

	public VerifyRequest getVerifyRequest() {
		return verifyRequest;
	}

	public ClientVerifyRequest getClientVerifyRequest() {
		return clientVerifyRequest;
	}

	public Download getDownload() {
		return download;
	}

	public ClientDownload getClientDownload() {
		return clientDownload;
	}
	
	
}
