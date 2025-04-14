package ch.supsi.web.cardgames.service;

import ch.supsi.web.cardgames.Model.Card;

import java.util.ArrayList;
import java.util.List;

public class CardService {
    List<Card> cardList = new ArrayList<>();

    public List<Card> getCardList() {
        return cardList;
    }

    public void addCardList(Card card) {
        this.cardList.add(card);
    }
}
