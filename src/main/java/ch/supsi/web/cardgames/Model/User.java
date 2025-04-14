package ch.supsi.web.cardgames.Model;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class User {

    private String firstName;

    private String lastName;

    private String username;

    private String password;

    private String passwordConfirm;

}
