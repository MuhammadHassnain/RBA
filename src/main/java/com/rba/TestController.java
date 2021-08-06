package com.rba;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rba.exception.ApiRequestException;

@RestController(value= "/")
public class TestController {

	@GetMapping("/ex")
	public String index() {
		throw new ApiRequestException("DDKDKFKD");
//		throw new IllegalArgumentException("DKJKSJDFKJDSK");
	}
}
