package com.example.logsingsql_lab9;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private DatabaseHelper databaseHelper;
    private String currentEmail;
    private TextView counterText;
    private Button pokeButton, logoutButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        currentEmail = getIntent().getStringExtra("email");
        databaseHelper = new DatabaseHelper(this);

        counterText = findViewById(R.id.counterText);
        pokeButton = findViewById(R.id.pokeButton);
        logoutButton = findViewById(R.id.logoutButton);

        int currentCount = databaseHelper.getClickCount(currentEmail);
        counterText.setText(String.valueOf(currentCount));

        pokeButton.setOnClickListener(v -> {
            databaseHelper.incrementClickCount(currentEmail);
            int newCount = databaseHelper.getClickCount(currentEmail);
            counterText.setText(String.valueOf(newCount));
        });

        logoutButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        });
    }
}
