package com.geekbrains.notepad;

public class CardNote {
    private String id;
    private String title;
    private String description;
    private String date;

    public CardNote() {
    }

    public CardNote (String title, String description, String date){

        this.title = title;
        this.description = description;
        this.date = date;
    }


    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getDate() {
        return date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}


