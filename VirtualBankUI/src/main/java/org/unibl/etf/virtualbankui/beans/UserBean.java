package org.unibl.etf.virtualbankui.beans;

import org.unibl.etf.virtualbankui.entities.CardEntity;
import org.unibl.etf.virtualbankui.services.CardService;

public class UserBean {

	private CardEntity card = null;
	private boolean isValidated = false;

	public boolean login(String cardNumber, String cvv) {
		card = CardService.selectOneByCardNumberAndCvv(cardNumber, cvv);
		
		if (card != null) {
			this.isValidated = true;
			return true;
		}
		
		return false;
	}

	public boolean isValidated() {
		return this.isValidated;
	}

	public void logout(){
		this.card = null;
		this.isValidated = false;
	}

	public CardEntity getCard() {
		return card;
	}
}
