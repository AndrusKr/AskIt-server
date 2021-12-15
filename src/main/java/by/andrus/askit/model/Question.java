package by.andrus.askit.model;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "questions")
public class Question {
    @Id
    @Column(name = "id", unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;

    @Column(name = "text", nullable = false)
    private String text;

    @Column(name = "answered")
    private Date answered;

    @Column(name = "asked", nullable = false)
    private Date asked;

    @Column(name = "last_edited_at", nullable = false)
    private Date last_edited_at;

    //    @ManyToOne
//    @JoinColumn(name = "author", nullable = false)
    @JoinColumn(name = "author")
//    private User author;
    private UUID author;

    public String getId() {
        return id.toString();
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getAnswered() {
        return answered;
    }

    public void setAnswered(Date answered) {
        this.answered = answered;
    }

    public Date getAsked() {
        return asked;
    }

    public void setAsked(Date asked) {
        this.asked = asked;
    }

    public Date getLast_edited_at() {
        return last_edited_at;
    }

    public void setLast_edited_at(Date last_edited_at) {
        this.last_edited_at = last_edited_at;
    }

    public UUID getAuthor() {
        return author;
    }

    public void setAuthor(UUID author) {
        this.author = author;
    }
}
