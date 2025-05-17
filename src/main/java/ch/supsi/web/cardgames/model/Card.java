package ch.supsi.web.cardgames.model;

import jakarta.persistence.*;
import org.springframework.web.multipart.MultipartFile;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor @ToString
@Entity
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;

    private String name;

    private CardGame game;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Transient
    private MultipartFile imageFile;

    @Lob
    private byte[] imageData;

    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private Date date;
    private String author;

    @Enumerated(EnumType.STRING)
    private Condition cardCondition;

    private Double price;

    @ManyToOne
    private User owner;

}
