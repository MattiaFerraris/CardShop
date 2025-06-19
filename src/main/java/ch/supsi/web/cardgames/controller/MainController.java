package ch.supsi.web.cardgames.controller;
import ch.supsi.web.cardgames.model.Card;
import ch.supsi.web.cardgames.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class MainController {

    @Autowired
    CardService cardService;

    @GetMapping("/")
    public String getIndex(Model  model){
        model.addAttribute("cards", cardService.getCards());
        model.addAttribute("activePage", "home");
        return "mainPage";
    }

    @GetMapping("/card/new")
    public String newCardPage(Model model){
        model.addAttribute("activePage", "sell");
        return "cardSaleForm";
    }

    @GetMapping("/card/{id}/edit")
    public String editCardPage(@PathVariable Long id, Model model) {
        Card card = cardService.getCardById(id);
        if(card==null)
            return "redirect:/";
        model.addAttribute("card", card);
        return "editCard";
    }

}
