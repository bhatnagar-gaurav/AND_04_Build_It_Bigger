package com.udacity.gradle.builditbigger;

import android.os.AsyncTask;
import android.util.Log;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.udacity.gradle.builditbigger.backend.myApi.MyApi;
import com.udacity.gradle.builditbigger.utils.AppConstants;

import java.io.IOException;

class EndPointsAsyncTask extends AsyncTask<Void, Void, String> {
    private static MyApi sMyApiService = null;
    private ExecutionListener listener;

    EndPointsAsyncTask(ExecutionListener listener) {
        this.listener = listener;
    }

    interface ExecutionListener {
        void changeProgressBarViewStatus(boolean var);
        void startDisplayActivity(String result);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        listener.changeProgressBarViewStatus(true);
    }

    @Override
    protected String doInBackground(Void... voids) {
        if(sMyApiService == null) {
            MyApi.Builder builder = new MyApi.Builder(
                    AndroidHttp.newCompatibleTransport()
                    , new AndroidJsonFactory(), null
            ).setRootUrl(AppConstants.ROOT_URL)
                    .setGoogleClientRequestInitializer(
                            new GoogleClientRequestInitializer() {
                                @Override
                                public void initialize(AbstractGoogleClientRequest<?> request) throws IOException {
                                    request.setDisableGZipContent(true);
                                }
                            }
                    );
            sMyApiService = builder.build();
        }
        try {
            return sMyApiService.retrieveJokeFromRobustBackend().execute().getData();
        } catch (IOException e) {
            Log.d("myTag", e.getMessage());
            return null;
        }
    }



    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        listener.changeProgressBarViewStatus(false);
        listener.startDisplayActivity(s);
    }
}