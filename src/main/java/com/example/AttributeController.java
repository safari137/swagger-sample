package com.example;

import javax.ws.rs.Path;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/people")
@Api(tags = {"people"}, description="*")
public class AttributeController {

	@RequestMapping(value = "{pidm}/attributes", method = RequestMethod.GET)
	@ApiOperation(value = "Gets Attributes")
	@Path("/people/attributes")
	public Attribute getAttributes(
			@ApiParam(value="pidm of user", required=true, defaultValue="12345")
			@PathVariable String pidm) {
		return new Attribute();
	}
}
