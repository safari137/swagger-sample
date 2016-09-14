package com.example;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/people")
@Api(tags = {"people"}, description="")
public class PidmController {

	@RequestMapping(value = "{id}/pidm", method = RequestMethod.GET)
	@ApiOperation(value = "Gets Pidm")
	public String getAttributes(@PathVariable String id) {
		return "pidm";
	}
}
