package ch.supsi.web.cardgames.controller;

import ch.supsi.web.cardgames.Model.Card;
import ch.supsi.web.cardgames.service.CardService;
import ch.supsi.web.cardgames.service.MainPageService;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MainController {

    MainPageService mainService = new MainPageService();
    CardService cardService = new CardService();

    @GetMapping("/api")
    public ResponseEntity<String> getIndex(){
        return ResponseEntity.ok()
                .contentType(MediaType.TEXT_HTML)
                .body(this.mainService.getHomePage(cardService.getCardList()));
    }


    @GetMapping("/api/card/new")
    public ResponseEntity<Resource> newCard(){
        return ResponseEntity.ok()
                .contentType(MediaType.TEXT_HTML)
                .body(this.mainService.getNewCard());
    }

    @PostMapping("/api/card/new")
    public String createCard(Card card) {
        this.cardService.addCardList(card);
        return "redirect:/api";
    }


}
