package com.eastshine.rapcrew.rst;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eastshine.rapcrew.mdl.Card;
import com.eastshine.rapcrew.svc.CardService;
import com.eastshine.rapcrew.utl.ApiResponse;


@RestController
@RequestMapping("/card/*")
public class CardController {
	
	private static Logger log = LoggerFactory.getLogger(CardController.class);  
	
	@Autowired
	private CardService cardService;
	
	@GetMapping("/{cardId}")
	public ApiResponse test(Card card) {
		
		log.info("test log.");
		

		return ApiResponse.ok(cardService.selectCard(card));
	}
	
}
