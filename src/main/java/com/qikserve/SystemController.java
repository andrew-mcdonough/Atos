package com.qikserve;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qikserve.supermarket.utilities.DAOUtilities;

@Controller
@RequestMapping(path = "/system")
public class SystemController {
	private static final Logger logger = LoggerFactory.getLogger(SystemController.class);

	@Autowired
	DAOUtilities doaUtilities;

	@GetMapping(path = "/healthcheck")
	@CrossOrigin
	public @ResponseBody ResponseEntity getHealthCheck() {
		return new ResponseEntity<>("healthcheck ", HttpStatus.OK);
	}

	@GetMapping(path = "/test")
	@CrossOrigin
	public @ResponseBody ResponseEntity getTest() {
		doaUtilities.getProducts();
		return new ResponseEntity<>("Load Database", HttpStatus.OK);
	}

}
