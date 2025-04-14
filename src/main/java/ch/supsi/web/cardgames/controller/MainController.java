package ch.supsi.web.cardgames.controller;

import ch.supsi.web.cardgames.Model.Card;
import ch.supsi.web.cardgames.service.CardService;
import ch.supsi.web.cardgames.service.MainPageService;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MainController {

    MainPageService mainService = new MainPageService();
    CardService cardService = new CardService();

    @GetMapping("/")
    public String getIndex(Model  model){
        model.addAttribute("cards", cardService.getCardList());
        return "mainPage";
    }


    @GetMapping("/card/new")
    public String newCard(){
        return "cardSaleForm";
    }

    @PostMapping("/card/new")
    public String createCard(Card card) {
        this.cardService.addCardList(card);
        return "redirect:/";
    }


}
