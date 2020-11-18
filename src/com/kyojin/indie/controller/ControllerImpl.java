package com.kyojin.indie.controller;

import com.kyojin.indie.service.ServiceImpl;

public class ControllerImpl implements Controller {
	
	ServiceImpl serviceImpl = new ServiceImpl();

	@Override
	public String generateUUIDCreate() {
		// TODO Auto-generated method stub
		return serviceImpl.generateUUIDCreate();
	}

}
