package ch.supsi.web.cardgames.service;

import ch.supsi.web.cardgames.Model.Card;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class CardService {
    List<Card> cardList = new ArrayList<>();

    public void addCardList(Card card) {
        this.cardList.add(card);
    }

    public Card getCardById(int id) {
        for (Card card : cardList)
            if(card.getId() == id)
                return card;

        return null;
    }
}
