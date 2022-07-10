package com.eastshine.rapcrew.svc;



import org.springframework.stereotype.Service;

import com.eastshine.rapcrew.dao.CardDAO;
import com.eastshine.rapcrew.mdl.Card;

@Service
public class CardService {

	
	private CardDAO cardDao;
	
	public CardService(CardDAO cardDao) {
		this.cardDao = cardDao;
	}
	
	
	public Card selectCard(Card card) {
		return cardDao.selectCard(card);
	}
	
	
}
