package ch.supsi.web.cardgames.controller;

import ch.supsi.web.cardgames.model.Role;
import ch.supsi.web.cardgames.model.User;
import ch.supsi.web.cardgames.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserController(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/register")
    public String registerForm(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("activePage", "register");
        return "signUp";
    }

    @PostMapping("/register")
    public String sendForm(
            @RequestParam String firstname,
            @RequestParam String lastname,
            @RequestParam String username,
            @RequestParam String password,
            @RequestParam String passwordtwo,
            RedirectAttributes redirectAttributes
    ) {
        logger.info("Received registration request: username={}, firstname={}, lastname={}",
                username, firstname, lastname);
        if (!password.equals(passwordtwo)) {
            logger.warn("Passwords do not match");
            redirectAttributes.addFlashAttribute("error", "Passwords do not match");
            return "redirect:/register";
        }
        try {
            User user = new User();
            user.setFirstName(firstname);
            user.setLastName(lastname);
            user.setUsername(username);
            user.setPassword(passwordEncoder.encode(password));
            user.setRole(Role.ROLE_USER);
            userService.saveUser(user);
            logger.info("User saved successfully: {}", username);
            return "redirect:/";
        } catch (Exception e) {
            logger.error("Error saving user: {}", e.getMessage(), e);
            redirectAttributes.addFlashAttribute("error", "Failed to create user: " + e.getMessage());
            return "redirect:/register";
        }
    }

    @GetMapping("/login")
    public String loginForm(@RequestParam(value = "error", required = false) String error, Model model) {
        if (error != null) {
            model.addAttribute("error", "Username or password incorrect");
        }
        return "login";
    }

    @GetMapping("/logout")
    public String logOut() {
        return "redirect:/";
    }
}