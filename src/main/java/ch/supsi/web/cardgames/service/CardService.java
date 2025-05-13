package ch.supsi.web.cardgames.service;

import ch.supsi.web.cardgames.model.Card;
import ch.supsi.web.cardgames.repository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

//https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/stereotype/Service.html
@Service
public class CardService {

    @Autowired
    private CardRepository cardRepository;

    public List<Card> getCards() {
        return cardRepository.findAll();
    }

    public Card getCardById(long id){
        return cardRepository.findById(id).orElse(null);
    }

    public void saveCard(Card card) {
        cardRepository.save(card);
    }

    public void updateCard(Card newCard,Card oldCard){

        oldCard.setName(newCard.getName());
        oldCard.setDescription(newCard.getDescription());
        oldCard.setDate(newCard.getDate());
        oldCard.setAuthor(newCard.getAuthor());
        oldCard.setCardCondition(newCard.getCardCondition());
        oldCard.setGame(newCard.getGame());         ;
        oldCard.setImageData(newCard.getImageData());
        oldCard.setPrice(newCard.getPrice());
        // Update the card in the repository
        cardRepository.save(oldCard);
    }

    public void deleteCard(Card card){
        cardRepository.deleteById(card.getId());
    }


}

