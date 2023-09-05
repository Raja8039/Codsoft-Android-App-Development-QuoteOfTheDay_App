package com.example.quotestoday;

public class Quote {
    private String text;
    private String author;
    private boolean isFavorite;

    public Quote(String text, String author) {
        this.text = text;
        this.author = author;
        this.isFavorite = false; // Initialize as not favorite
    }

    // Getter for text
    public String getText() {
        return text;
    }

    // Setter for text
    public void setText(String text) {
        this.text = text;
    }

    // Getter for author
    public String getAuthor() {
        return author;
    }

    // Setter for author
    public void setAuthor(String author) {
        this.author = author;
    }

    // Getter for isFavorite
    public boolean isFavorite() {
        return isFavorite;
    }

    // Setter for isFavorite
    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }
}

