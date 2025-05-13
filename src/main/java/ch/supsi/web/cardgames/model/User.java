package ch.supsi.web.cardgames.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
public class User {

    @Id
    private int id;

    private String firstName;

    private String lastName;

    private String username;

    private String password;

    private String passwordConfirm;

}
