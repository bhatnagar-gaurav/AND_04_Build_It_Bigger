package com.udacity.gradle.builditbigger;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

import com.udacity.android.joke.library.JokeDisplayActivity;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment implements EndPointsAsyncTask.ExecutionListener{

    private ProgressBar jokeProgress;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);

        return root;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        jokeProgress = view.findViewById(R.id.jokeProgressbar);

        Button showJoke = view.findViewById(R.id.showJokeButton);
        showJoke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new EndPointsAsyncTask(MainActivityFragment.this).execute();
            }
        });
    }


    @Override
    public void changeProgressBarViewStatus(boolean var) {
        if(var){
            jokeProgress.setVisibility(View.VISIBLE);
        } else {
            jokeProgress.setVisibility(View.GONE);
        }
    }

    @Override
    public void startDisplayActivity(String result) {
        Intent jokeIntent = new Intent(getActivity(),JokeDisplayActivity.class);
        jokeIntent.putExtra(getString(R.string.key_joke_argument), result);
        startActivity(jokeIntent);
    }
}