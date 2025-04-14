package ch.supsi.web.cardgames.service;

import ch.supsi.web.cardgames.Model.Card;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class CardService {
    List<Card> cardList = new ArrayList<>();

    public void addCardList(Card card) {
        card.setId(cardList.size());
        this.cardList.add(card);
    }

    public Card getCardById(int id) {
        for (Card card : cardList)
            if(card.getId() == id)
                return card;
        return null;
    }

    public void deleteCard(int id) {
        Card del = getCardById(id);
        if(del != null)
            cardList.remove(del);
    }

    public Card updateCard(Card card){
        Card cardRsc=getCardById(card.getId());
        if(cardRsc==null)
            return null;
        cardRsc.setName(card.getName());
        cardRsc.setDescription(card.getDescription());
        cardRsc.setAuthor(card.getAuthor());
        cardRsc.setCondition(card.getCondition());
        cardRsc.setType(card.getType());
        cardRsc.setDate(card.getDate());
        return cardRsc;
    }
}
