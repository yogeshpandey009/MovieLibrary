package edu.asu.msse.ypandey.labassign3;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;


/**
 * Copyright © 2016 Yogesh Pandey,
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * <p/>
 * Purpose: Lab Assignment 3
 *
 * @author Yogesh Pandey ypandey@asu.edu
 *         MS Software Engineering, CIDSE, Arizona State University Polytechnic
 * @version February 08, 2016
 */

public class DialogActivity extends AppCompatActivity {
    TextView ratedTV = null;
    TextView releasedTV = null;
    TextView titleTV = null;
    Spinner genreSP = null;
    TextView actorsTV = null;
    TextView plotTV = null;
    MovieLibrary movieLib = null;
    Button remBtn = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        android.util.Log.d(this.getClass().getSimpleName(), "called onCreate()");
        setContentView(R.layout.activity_dialog);
        /*
         * Display a message particular to which action bar button has been selected.
         * That message is passed as the string value of the message attribute which is
         * added to the Intent object. You can visualize the Intent as a Dictionary/Map
         * To get the action bar to not display in the dialog activity, you can define
         * a new theme in styles.xml that is based on the dialog theme, but adds to it
         * to not show the action bar or the activity's title.
         */
        setContentView(R.layout.activity_dialog);
        ratedTV = (TextView) findViewById(R.id.ratedText);
        releasedTV = (TextView) findViewById(R.id.releasedText);
        titleTV = (TextView) findViewById(R.id.titleText);
        genreSP = (Spinner) findViewById(R.id.genreSpinner);
        actorsTV = (TextView) findViewById(R.id.actorsText);
        plotTV = (TextView) findViewById(R.id.plotText);
        remBtn = (Button) findViewById(R.id.btn_rem);
        movieLib = MovieLibrary.getInstance();
        Intent i = getIntent();
        if(i.getStringExtra("action").equals("view")) {
            setTitle("View/Edit Movie");
            loadView(i.getStringExtra("mTitle"));
            titleTV.setFocusable(false);
            remBtn.setVisibility(View.VISIBLE);
        } else {
            setTitle("Add Movie");
            titleTV.setFocusable(true);
            remBtn.setVisibility(View.INVISIBLE);
        }

    }

    /**
     * Callback method for the button in the View (activity_dialog.xml)
     * @param v
     */
    public void finishDialog(View v) {
        android.util.Log.d("DialogActivity:", "called finishDialog()");
        DialogActivity.this.finish();
    }

    public void saveMovie(View v) {
        android.util.Log.d("DialogActivity:", "called saveMovie()");
        MovieDescription md = new MovieDescription();
        md.setTitle(titleTV.getText().toString());
        md.setRated(ratedTV.getText().toString());
        md.setReleased(releasedTV.getText().toString());
        md.setGenre(String.valueOf(genreSP.getSelectedItem()));
        md.setActors(actorsTV.getText().toString());
        md.setPlot(plotTV.getText().toString());
        movieLib.addMovie(md);
        DialogActivity.this.finish();
    }

    public void removeMovie(View v) {
        android.util.Log.d("DialogActivity:", "called removeMovie()");
        movieLib.removeMovie(titleTV.getText().toString());
        DialogActivity.this.finish();
    }

    public void loadView(String mTitle) {
        Log.w(this.getLocalClassName(), "loading movie");
        MovieDescription md = movieLib.getMovieDescription(mTitle);
        titleTV.setText(md.getTitle());
        ratedTV.setText(md.getRated());
        releasedTV.setText(md.getReleased());
        genreSP.setSelection(((ArrayAdapter) genreSP.getAdapter()).getPosition(md.getGenre()));
        actorsTV.setText(md.getActors());
        plotTV.setText(md.getPlot());
    }
}
