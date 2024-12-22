package mk.ukim.finki.wp.lab1.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String text;

    @ManyToOne
    private Song song;

    public Comment() {}

    public Comment(String text) {
        this.text = text;
    }

}