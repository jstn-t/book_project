package com.example.book_app;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "book_table")
public class Book {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String title;

    private String author;

    private String state;

    private int page_count;

    public Book(String title, String author, String state, int page_count) {
        this.title = title;
        this.author = author;
        this.state = state;
        this.page_count = page_count;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getState() {
        return state;
    }

    public int getPage_count() {
        return page_count;
    }
}
