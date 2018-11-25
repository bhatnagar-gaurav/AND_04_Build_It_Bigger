package com.udacity.gradle.builditbigger;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.udacity.android.joke.library.JokeDisplayActivity;

import static com.udacity.gradle.builditbigger.utils.AppConstants.BUNDLE_EXTRA_JOKE;
import static com.udacity.gradle.builditbigger.utils.AppConstants.KEY_JOKE_ARGUMENT;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment implements EndPointsAsyncTask.ExecutionListener{

    private ProgressBar mJokeProgress;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        MobileAds.initialize(getContext(), "ca-app-pub-1337842656041626~5678817490");
        initAddMob(view);
        mJokeProgress = view.findViewById(R.id.jokeProgressbar);

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
        if (var) {
            mJokeProgress.setVisibility(View.VISIBLE);
        } else {
            mJokeProgress.setVisibility(View.GONE);
        }
    }

    @Override
    public void startDisplayActivity(String result) {
        if (TextUtils.isEmpty(result)) {
            Toast.makeText(getContext(), R.string.error_string, Toast.LENGTH_LONG).show();
            return;
        }
        Intent jokeIntent = new Intent(getActivity(), JokeDisplayActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString(KEY_JOKE_ARGUMENT,result);
        jokeIntent.putExtra(BUNDLE_EXTRA_JOKE, bundle);
        startActivity(jokeIntent);
    }
}