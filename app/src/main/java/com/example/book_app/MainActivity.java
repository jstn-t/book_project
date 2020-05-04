package com.example.book_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {
public static final int ADD_BOOK_REQUEST = 1;
    private BookViewModel bookViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton buttonAddBook = findViewById(R.id.button_add_book);
        buttonAddBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddBookActivity.class);
                startActivityForResult(intent,ADD_BOOK_REQUEST);
            }
        });

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        final BookAdapter adapter = new BookAdapter();
        recyclerView.setAdapter(adapter);


        bookViewModel = new ViewModelProvider(this
                , ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication()))
                .get(BookViewModel.class);

        bookViewModel.getAllBooks().observe(this, new Observer<List<Book>>() {
            @Override
            public void onChanged(List<Book> books) {
                adapter.setBooks(books);
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode==ADD_BOOK_REQUEST && resultCode==RESULT_OK) {
            String title = data.getStringExtra(AddBookActivity.EXTRA_TITLE);
            String author = data.getStringExtra(AddBookActivity.EXTRA_AUTHOR);
            String status = data.getStringExtra(AddBookActivity.EXTRA_STATUS);
            int pages = data.getIntExtra(AddBookActivity.EXTRA_PAGES, 0);

            Book book = new Book(title, author, status, pages);
            bookViewModel.insert(book);

            Toast.makeText(this, "Book Saved", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Failed to save!", Toast.LENGTH_SHORT).show();
        }

    }

}