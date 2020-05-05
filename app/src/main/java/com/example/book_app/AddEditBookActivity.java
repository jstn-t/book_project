package com.example.book_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.Toast;

public class AddEditBookActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    public static final String EXTRA_ID =
            "com.example.book_app.EXTRA_ID";
    public static final String EXTRA_TITLE =
            "com.example.book_app.EXTRA_TITLE";
    public static final String EXTRA_AUTHOR =
            "com.example.book_app.EXTRA_AUTHOR";
    public static final String EXTRA_PAGES =
            "com.example.book_app.EXTRA_PAGES";
    public static final String EXTRA_STATUS =
            "com.example.book_app.EXTRA_STATUS";


    private EditText editTextTitle;
    private EditText editTextAuthor;
    private EditText editTextPage;
    private Spinner spinnerStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);

        editTextTitle = findViewById(R.id.edit_text_title);
        editTextAuthor = findViewById(R.id.edit_text_author);
        editTextPage = findViewById(R.id.edit_text_pages);

        spinnerStatus = findViewById(R.id.spinner_status);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.stuses, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerStatus.setAdapter(adapter);
        spinnerStatus.setOnItemSelectedListener(this);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);
        Intent intent = getIntent();

        if(intent.hasExtra(EXTRA_ID)) {
            setTitle("Edit Book");
            editTextTitle.setText(intent.getStringExtra(EXTRA_TITLE));
            editTextAuthor.setText(intent.getStringExtra(EXTRA_AUTHOR));
            //spinnerStatus.;
            editTextPage.setText(intent.getStringExtra(EXTRA_PAGES));

        } else {
            setTitle("Add Book");
        }
    }

    private void saveBook() {
        String title = editTextTitle.getText().toString();
        String author = editTextAuthor.getText().toString();
        //int pages = Integer.parseInt(editTextPage.getText().toString());
        int pages = Integer.parseInt("0" + editTextPage.getText().toString());
        String status = spinnerStatus.getSelectedItem().toString();

        if (title.trim().isEmpty() || author.trim().isEmpty() || pages==0) {
            Toast.makeText(this, "Please fill out all the elements", Toast.LENGTH_SHORT);
            return;
        }

        Intent data = new Intent();
        data.putExtra(EXTRA_TITLE, title);
        data.putExtra(EXTRA_AUTHOR, author);
        data.putExtra(EXTRA_PAGES, pages);
        data.putExtra(EXTRA_STATUS, status);

        int id = getIntent().getIntExtra(EXTRA_ID, -1);
        if (id!=-1) {
            data.putExtra(EXTRA_ID, id);
        }

        setResult(RESULT_OK, data);
        finish();

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_book_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save_book:
                saveBook();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();
        //Toast.makeText(parent.getContext(), text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
