package ch.supsi.web.cardgames.Model;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor @ToString
public class Card {

    private int Id;

    private String name;

    private CardGame game;
    private String description;
    private byte[] image;
    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private Date date;
    private String author;
    private Condition condition;
    private double price;

}
