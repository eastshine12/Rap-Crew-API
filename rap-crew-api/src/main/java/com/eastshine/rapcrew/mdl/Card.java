package com.eastshine.rapcrew.mdl;

public class Card {
	
	private Long cardId;
	private String title;
	private String content;
	
	
	public Long getCardId() {
		return cardId;
	}
	public void setCardId(Long cardId) {
		this.cardId = cardId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("card [");
		if (cardId != null) {
			builder.append("cardId=");
			builder.append(cardId);
			builder.append(", ");
		}
		if (title != null) {
			builder.append("title=");
			builder.append(title);
			builder.append(", ");
		}
		if (content != null) {
			builder.append("content=");
			builder.append(content);
		}
		builder.append("]");
		return builder.toString();
	}
	

	

}
