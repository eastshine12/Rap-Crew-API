package com.eastshine.rapcrew.rst;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.eastshine.rapcrew.utl.ApiResponse;


@RestController
@RequestMapping("/card/*")
public class CardController {
	
	private static Logger log = LoggerFactory.getLogger(CardController.class);  
	
	
	@GetMapping("/{cardId}")
	public ApiResponse test(@PathVariable("cardId") Long cardId) {
		
		log.info("test log.");
		
		return ApiResponse.ok("test response");
	}
	
}
