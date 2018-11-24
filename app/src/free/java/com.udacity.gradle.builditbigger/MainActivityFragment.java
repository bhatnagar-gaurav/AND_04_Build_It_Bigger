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

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
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
        MobileAds.initialize(getContext(), "ca-app-pub-1337842656041626~5678817490");
        initAddMob(view);
        jokeProgress = view.findViewById(R.id.jokeProgressbar);

        Button showJoke = view.findViewById(R.id.showJokeButton);
        showJoke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new EndPointsAsyncTask(MainActivityFragment.this).execute();
            }
        });
    }


    private void initAddMob(@NonNull View view) {
        AdView mAdView = view.findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder()
                .build();
        mAdView.loadAd(adRequest);
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