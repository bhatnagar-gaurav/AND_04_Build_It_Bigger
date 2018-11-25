package com.udacity.android.joke.library;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import static utils.AppConstants.BUNDLE_EXTRA_JOKE;
import static utils.AppConstants.KEY_JOKE_ARGUMENT;


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
        if (intent != null && intent.hasExtra(BUNDLE_EXTRA_JOKE)) {
            Bundle data = intent.getBundleExtra(BUNDLE_EXTRA_JOKE);
            if (data != null) {
                String joke = data.getString(KEY_JOKE_ARGUMENT);
                tVJoke.setText(joke);
            }
        }
    }


}
