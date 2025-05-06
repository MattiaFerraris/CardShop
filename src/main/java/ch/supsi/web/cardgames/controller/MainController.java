package ch.supsi.web.cardgames.controller;
import ch.supsi.web.cardgames.Model.Card;
import ch.supsi.web.cardgames.Model.User;
import ch.supsi.web.cardgames.service.CardService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MainController {

    CardService cardService = new CardService();

    @GetMapping("/")
    public String getIndex(Model  model){
        model.addAttribute("cards", cardService.getCardList());
        model.addAttribute("activePage", "home");
        return "mainPage";
    }

    @GetMapping("/card/new")
    public String newCard(Model model){
        model.addAttribute("activePage", "sell");
        return "cardSaleForm";
    }

    @PostMapping("/card/new")
    public String createCard(Card card) {
        this.cardService.addCardList(card);
        return "redirect:/";
    }

    @GetMapping("/card/{id}")
    public String getCard(@PathVariable("id") int id, Model model){
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
    public String editCard(@PathVariable int id, Model model) {
        Card card = cardService.getCardById(id);
        if(card==null)
            return "redirect:/";
        model.addAttribute("card", card);
        return "editCard";
    }

    @GetMapping("/card/{id}/delete")
    public String deleteCard(@PathVariable int id, Model model) {
        cardService.deleteCard(id);
        return "redirect:/";
    }

    @PostMapping("/card/{id}/edit")
    public String updateCard(@PathVariable int id, @ModelAttribute Card card) {
        cardService.updateCard(card);
        return "redirect:/";
    }


}
