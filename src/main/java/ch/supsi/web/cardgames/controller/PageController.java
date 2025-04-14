package ch.supsi.web.cardgames.controller;

import ch.supsi.web.cardgames.Model.Card;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PageController {
    @GetMapping("/")
    public String getIndex(){
        return "mainPage";
    }

    @GetMapping("/card/new")
    public String newCard(){
        return "cardSaleForm";
    }

}
