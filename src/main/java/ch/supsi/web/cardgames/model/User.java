package ch.supsi.web.cardgames.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.id.factory.internal.AutoGenerationTypeStrategy;

@Getter @Setter
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String firstName;

    private String lastName;

    private String username;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private Role role;

    private String password;
}
