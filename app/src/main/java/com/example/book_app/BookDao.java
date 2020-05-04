package com.example.book_app;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface BookDao {

    @Insert
    void insert(Book book);

    @Update
    void update(Book book);

    @Delete
    void delete(Book book);

    @Query("DELETE FROM BOOK_TABLE")
    void deleteAllBooks();

    @Query("SELECT * FROM BOOK_TABLE ORDER BY title DESC")
    LiveData<List<Book>> getAllBooks();
}
