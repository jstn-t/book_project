package com.example.book_app;

import android.app.Application;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class BookViewModel extends AndroidViewModel {
    private BookRepository repository;
    private LiveData<List<Book>> allBooks;
    public BookViewModel(@NonNull Application application) {
        super(application);
    }

    public void insert(Book book) {
        repository.insert(book);
    }
    public void update(Book book) {
        repository.update(book);
    }
    public void delete(Book book) {
        repository.delete(book);
    }
    public void deleteAllBooks() {
        repository.deleteAllBooks();
    }

    public LiveData<List<Book>> getAllBooks() {
        return allBooks;
    }
}
