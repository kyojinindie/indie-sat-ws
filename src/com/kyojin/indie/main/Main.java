package com.kyojin.indie.main;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.kyojin.indie.controller.ControllerImpl;

public class Main {
	private final static Logger LOGGER = Logger.getLogger("bitacora.subnivel.Control");
	
	public static void main(String[] args) {
		ControllerImpl controller = new ControllerImpl();
		LOGGER.log(Level.INFO, "UUIDCreate " + controller.generateUUIDCreate());
	}

}
