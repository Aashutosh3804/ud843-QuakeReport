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

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class EarthquakeActivity extends AppCompatActivity {

    String url="https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&eventtype=earthquake&orderby=time&minmag=6&limit=10";
   private CustomAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.earthquake_activity);

        // Create a fake list of earthquake locations.
        ListView earthquakeListView = (ListView) findViewById(R.id.list);
         adapter = new CustomAdapter(EarthquakeActivity.this, new ArrayList<QuakeData>());
        earthquakeListView.setAdapter(adapter);
       DataAsync task=new DataAsync();
       task.execute(url);



        // Create a new {@link ArrayAdapter} of earthquakes

    }
    private class DataAsync extends AsyncTask<String,Void, List<QuakeData>>{
        @Override
        protected void onPostExecute(List<QuakeData> quakeData) {
            adapter.clear();
            if(!quakeData.isEmpty()&&quakeData !=null){
                adapter.addAll(quakeData);
            }

            // Set the adapter on the {@link ListView}
            // so the list can be populated in the user interface

        }

        @Override
        protected List<QuakeData> doInBackground(String... urls) {
            if(urls.length<1 || urls==null){
                return null;
            }

            List<QuakeData> data=QueryUtils.fetchEathQuakeData(urls[0]);

            return data;
        }
    }
}
