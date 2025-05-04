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

    public void updateCard(Card card){
        Card update=getCardById(card.getId());
        if(update==null)
            return;
        update.setName(card.getName());
        update.setGame(card.getGame());
        update.setDescription(card.getDescription());
        update.setImage(card.getImage());
        update.setDate(card.getDate());
        update.setAuthor(card.getAuthor());
        update.setCondition(card.getCondition());
    }
}
