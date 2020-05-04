package com.example.book_app;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class BookRepository {
    private BookDao bookDao;
    private LiveData<List<Book>> allBooks;

    public BookRepository(Application application){
        BookDatabase database = BookDatabase.getInstance(application);
        bookDao = database.bookDao();
        allBooks = bookDao.getAllBooks();
    }

    public void insert(Book book){
        new InsertBookAsyncTask(bookDao).execute(book);
    }

    public void update(Book book){
        new UpdateBookAsyncTask(bookDao).execute(book);
    }

    public void delete(Book book){
        new DeleteBookAsyncTask(bookDao).execute(book);
    }

    public void deleteAllBooks(){
        new DeleteAllBooksAsyncTask(bookDao).execute();
    }

    public LiveData<List<Book>> getAllBooks() {
        return allBooks;
    }

    private static class InsertBookAsyncTask extends AsyncTask<Book,Void,Void>{
        private BookDao bookDao;

        private InsertBookAsyncTask(BookDao bookDao){
            this.bookDao = bookDao;
        }

        @Override
        protected Void doInBackground(Book... books){
            bookDao.insert(books[0]);
            return null;
        }
    }

    private static class UpdateBookAsyncTask extends AsyncTask<Book,Void,Void>{
        private BookDao bookDao;

        private UpdateBookAsyncTask(BookDao bookDao){
            this.bookDao = bookDao;
        }

        @Override
        protected Void doInBackground(Book... books){
            bookDao.update(books[0]);
            return null;
        }
    }

    private static class DeleteBookAsyncTask extends AsyncTask<Book,Void,Void>{
        private BookDao bookDao;

        private DeleteBookAsyncTask(BookDao bookDao){
            this.bookDao = bookDao;
        }

        @Override
        protected Void doInBackground(Book... books){
            bookDao.delete(books[0]);
            return null;
        }
    }

    private static class DeleteAllBooksAsyncTask extends AsyncTask<Void,Void,Void>{
        private BookDao bookDao;

        private DeleteAllBooksAsyncTask(BookDao bookDao){
            this.bookDao = bookDao;
        }

        @Override
        protected Void doInBackground(Void... voids){
            bookDao.deleteAllBooks();
            return null;
        }
    }

}
