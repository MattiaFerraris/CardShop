package ch.supsi.web.cardgames.controller;
import ch.supsi.web.cardgames.model.Card;
import ch.supsi.web.cardgames.model.User;
import ch.supsi.web.cardgames.service.CardService;
import ch.supsi.web.cardgames.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

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
