package com.udacity.android.joke.library;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;


public class JokeDisplayActivity extends AppCompatActivity {

    private TextView tVJoke;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke_display);
        tVJoke = findViewById(R.id.textView_joke);
        showJokeFromIntent();
    }

    private void showJokeFromIntent() {
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra(getString(R.string.key_joke_argument))) {
            Bundle data = intent.getExtras();
            if (data != null) {
                String joke = data.getString(getString(R.string.key_joke_argument));
                tVJoke.setText(joke);
            }
        }
    }


}
