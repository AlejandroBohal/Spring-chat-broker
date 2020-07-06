package edu.eci.springboot.chat.models.documents;

import java.io.Serializable;

public class Message implements Serializable {
    private String text;
    private Long date;

    private static final long serialVersionUID = -25454161L;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Long getDate() {
        return date;
    }

    public void setDate(Long date) {
        this.date = date;
    }
}
