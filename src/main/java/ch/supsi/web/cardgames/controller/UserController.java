package ch.supsi.web.cardgames.controller;

import ch.supsi.web.cardgames.model.User;
import ch.supsi.web.cardgames.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/register")
    public String registerForm(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("activePage", "register");
        return "login";
    }

    @PostMapping("/register")
    public String sendForm(@ModelAttribute User user, Model model) {
        userService.saveUser(user);
        return "redirect:/";
    }
}
