package com.kyojin.indie.controller;

import com.kyojin.indie.service.ServiceImpl;

public class ControllerImpl implements Controller {
	
	ServiceImpl serviceImpl = new ServiceImpl();

	@Override
	public String generateUUIDCreate() {
		return serviceImpl.generateUUIDCreate();
	}

	@Override
	public String generateUUIDExpires() {
		return serviceImpl.generateUUIDExpires();
	}

}
