package com.eastshine.rapcrew.dao;

import org.apache.ibatis.annotations.Mapper;

import com.eastshine.rapcrew.mdl.Card;

@Mapper
public interface CardDAO {
	
	public Card selectCard(Card card);

}
