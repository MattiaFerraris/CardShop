package ch.supsi.web.cardgames.controller;
import ch.supsi.web.cardgames.model.Card;
import ch.supsi.web.cardgames.model.User;
import ch.supsi.web.cardgames.service.CardService;
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
    public String newCard(Model model){
        model.addAttribute("activePage", "sell");
        return "cardSaleForm";
    }

    @PostMapping("/card/new")
    public String createCard(@ModelAttribute Card card, @RequestParam("imageFile") MultipartFile imageFile) {
        try {
            if (imageFile != null && !imageFile.isEmpty()) {
                card.setImageData(imageFile.getBytes());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.cardService.saveCard(card);
        return "redirect:/";
    }

    @GetMapping("/card/image/{id}")
    @ResponseBody
    public ResponseEntity<byte[]> getCardImage(@PathVariable Long id) throws IOException {
        Card card = cardService.getCardById(id);
        byte[] imageBytes = card.getImageData();

        if (imageBytes == null || imageBytes.length == 0) {
            return ResponseEntity.notFound().build();
        }

        String mimeType = URLConnection.guessContentTypeFromStream(new ByteArrayInputStream(imageBytes));
        if (mimeType == null) mimeType = MediaType.IMAGE_PNG_VALUE;

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(mimeType));

        return new ResponseEntity<>(imageBytes, headers, HttpStatus.OK);
    }



    @GetMapping("/card/{id}")
    public String getCard(@PathVariable("id") Long id, Model model){
        Card card=cardService.getCardById(id);
        if(card==null)
            return "redirect:/";
        model.addAttribute("card",card);
        return "card";
    }

    @GetMapping("/register")
    public String registerForm(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("activePage", "register");
        return "login";
    }

    @PostMapping("/register")
    public String sendForm(Model model) {
        return "redirect:/";
    }

    @GetMapping("/card/{id}/edit")
    public String editCard(@PathVariable Long id, Model model) {
        Card card = cardService.getCardById(id);
        if(card==null)
            return "redirect:/";
        model.addAttribute("card", card);
        return "editCard";
    }

    @GetMapping("/card/{id}/delete")
    public String deleteCard(@PathVariable Long id, Model model) {
        Card card = cardService.getCardById(id);

        cardService.deleteCard(card);
        return "redirect:/";
    }

    @PostMapping("/card/{cardId}/edit")
    public String editCard(@PathVariable int cardId, @ModelAttribute Card card, @RequestParam("image") MultipartFile imageFile) throws IOException{

        Card existingCard = cardService.getCardById(cardId);
        if (existingCard == null) {
            return "redirect:/card/not-found";
        }

        if (!imageFile.isEmpty()) {
            card.setImageData(imageFile.getBytes());
        } else {
            card.setImageData(existingCard.getImageData());
        }

        this.cardService.updateCard(card, existingCard);
        return "redirect:/card/" + cardId;
    }



}
