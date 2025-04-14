package ch.supsi.web.cardgames.controller;

import ch.supsi.web.cardgames.Model.Card;
import ch.supsi.web.cardgames.service.CardService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MainController {

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

    @GetMapping("card/{id}")
    public String getCard(@PathVariable("id") int id, Model model){
        Card card=cardService.getCardById(id);
        if(card==null)
            return "redirect:/";
        model.addAttribute("card",card);
        return "card";
    }


}
