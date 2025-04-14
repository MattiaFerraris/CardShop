package ch.supsi.web.cardgames.service;
import ch.supsi.web.cardgames.Model.Card;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;


import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;
import java.util.stream.Collectors;


public class MainPageService {

    public String getHomePage(List<Card> cards){
        Resource resource = new ClassPathResource("templates/mainPage.html");
        String file = null;

        try{
            file = resource.getContentAsString(Charset.defaultCharset());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println(cards);
        String cardLis = cards.stream().map(card -> "<li>" + card.getName() +"<li>").collect(Collectors.joining());
        file = file.replace("$$cards" ,cardLis);
        return file;
    }

    public Resource getNewCard(){
        return new ClassPathResource("templates/cardSaleForm.html");
    }

}
