package ch.supsi.web.cardgames.Model;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor @ToString
public class Card {

    private String name;

    private CardGame game;
    private String desciption;

    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private Date date;

    private String author;

    private Condition condition;
}
