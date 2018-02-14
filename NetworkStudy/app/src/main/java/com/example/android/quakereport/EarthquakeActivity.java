/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.quakereport;

//import android.os.AsyncTask;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.net.HttpURLConnection;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import android.app.LoaderManager;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.Loader;
import android.widget.ProgressBar;
import android.widget.TextView;

public class EarthquakeActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<EarthQuake>> {

    public static final String LOG_TAG = EarthquakeActivity.class.getName();

    private static final String USGS_REQUEST_URL = "https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&eventtype=earthquake&orderby=time&minmag=6&limit=10";

    private EarthQuakeAdapter mAdapter;
    private static final int EARTHQUAKE_LOADER_ID = 1;

    private TextView mEmptyStateTextView;

//    private ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.earthquake_activity);


//        EarthQuakeAsyncTask task = new EarthQuakeAsyncTask();
//        task.execute(USGS_REQUEST_URL);

        createUi();


        ConnectivityManager connMgr = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        if(networkInfo != null && networkInfo.isConnected()) {
            LoaderManager loaderManager = getLoaderManager();
            loaderManager.initLoader(EARTHQUAKE_LOADER_ID, null, this);

        } else {
            View mProgressBar = (ProgressBar) findViewById(R.id.loading_spinner);
            mProgressBar.setVisibility(View.GONE);
            mEmptyStateTextView.setText(R.string.no_internet_connection);
        }

    }

    /**
     * Update the UI with the given earthquake information.
     */
    private void createUi() {
        // Find a reference to the {@link ListView} in the layout
        ListView earthquakeListView = (ListView) findViewById(R.id.list);

        // Create a new {@link ArrayAdapter} of earthquakes
        mAdapter = new EarthQuakeAdapter(this, new ArrayList<EarthQuake>());

        // Set the adapter on the {@link ListView}
        // so the list can be populated in the user interface
        earthquakeListView.setAdapter(mAdapter);

        mEmptyStateTextView = (TextView) findViewById(R.id.empty_view);
        earthquakeListView.setEmptyView(mEmptyStateTextView);


    }


//    private class EarthQuakeAsyncTask extends AsyncTask<String, Void, List<EarthQuake>> {
//        @Override
//        protected List<EarthQuake> doInBackground(String... urls) {
//            if(urls.length < 1 || urls[0] == null) {
//                return null;
//            }
//
//            // Create a fake list of earthquake locations.
//            final ArrayList<EarthQuake> earthquakes = (ArrayList<EarthQuake>) QueryUtils.fetchEarthquakeData(urls[0]);
//            return earthquakes;
//
//        }
//
//        @Override
//        protected void onPostExecute(List<EarthQuake> earthQuakes) {
//
//            if (earthQuakes == null || earthQuakes.isEmpty()) {
//                return;
//            }
//            mAdapter.clear();
//            mAdapter.addAll(earthQuakes);
//        }
//    }


    @Override
    public Loader<List<EarthQuake>> onCreateLoader(int id, Bundle args) {
        return new EarthquakeLoader(this, USGS_REQUEST_URL);
    }

    @Override
    public void onLoadFinished(Loader<List<EarthQuake>> loader, List<EarthQuake> data) {

        View mProgressBar = (ProgressBar) findViewById(R.id.loading_spinner);
        mProgressBar.setVisibility(View.GONE);

        Log.i(LOG_TAG, "Log is refreshed by....");
        mAdapter.clear();
//        mProgressBar.setVisibility(View.GONE);

        if (data != null && !data.isEmpty()) {
            mAdapter.addAll(data);
        } else {
            mEmptyStateTextView.setText(R.string.no_earthquakes);
        }

    }

    @Override
    public void onLoaderReset(Loader<List<EarthQuake>> loader) {
        mAdapter.clear();
//        mProgressBar.setVisibility(View.VISIBLE);

    }
}
