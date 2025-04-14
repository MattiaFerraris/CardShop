package ch.supsi.web.cardgames.service;
import ch.supsi.web.cardgames.Model.Card;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;


import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;
import java.util.stream.Collectors;


public class MainPageService {

    public Resource getNewCard(){
        return new ClassPathResource("templates/cardSaleForm.html");
    }

}
