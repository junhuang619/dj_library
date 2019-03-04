package com.djcps.library.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/")
public class IndexController {

	@RequestMapping(value = "index")
	public String index() {
		return "index";
	}

	@RequestMapping()
	public String index1() {
		return "index";
	}
}
