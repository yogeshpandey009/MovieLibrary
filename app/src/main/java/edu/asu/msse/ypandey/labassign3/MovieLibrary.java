package edu.asu.msse.ypandey.labassign3;

import android.content.Context;
import android.util.Log;

import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by yogeshpandey on 06/02/16.
 * Copyright 2016 Yogesh Pandey,
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * Purpose: SER598 LabAssign3
 * This movie library is to load/add/update/remove movies
 *
 * Ser594 Mobile Systems
 * see http://pooh.poly.asu.edu/Mobile
 * @author Yogesh ypandey@asu.edu
 *         Student, MS Software Engineering, CIDSE, ASU Poly
 * @version January 2016
 *
 */
public class MovieLibrary extends Object implements Serializable {
    protected Hashtable<String, MovieDescription> movies;
    private static MovieLibrary instance;
    private static final boolean debugOn = true;

    private MovieLibrary() {
        movies = new Hashtable<String, MovieDescription>();
    }

    public synchronized static MovieLibrary initInstance(Context appContext) {
        if(instance == null) {
            instance = new MovieLibrary();
            instance.loadMovies(appContext);
        }
        return instance;
    }

    public static MovieLibrary getInstance() {
        return instance;
    }

    private void debug(String msg) {
        if(debugOn)
            Log.d(this.getClass().getSimpleName(), msg);
    }

    public void loadMovies(Context appContext) {
        try {
            movies.clear();
            String fileName = "movies.json";
            InputStream is = appContext.getResources().openRawResource(R.raw.movies);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            JSONObject moviesJson = new JSONObject(new JSONTokener(br.readLine()));
            Iterator<String> it = moviesJson.keys();
            while(it.hasNext()) {
                String mTitle = it.next();
                JSONObject aMovie = moviesJson.optJSONObject(mTitle);
                debug("importing movie description titled " + mTitle + " json is " +aMovie.toString());
                if(aMovie != null) {
                    MovieDescription md = new MovieDescription(aMovie.toString(), mTitle);
                    movies.put(mTitle, md);
                }
            }
        } catch(Exception e) {
            Log.d(this.getClass().getSimpleName(), "Exception while loading movies " + e.getMessage());
        }
    }

    public void addMovie(MovieDescription aMovie) {
        debug("adding a movie named:" + aMovie.getTitle());
        movies.put(aMovie.getTitle(), aMovie);
    }

    public boolean removeMovie(String mTitle) {
        boolean ret = false;
        debug("deleting a movie named:" + mTitle);
        if(movies.containsKey(mTitle)) {
            movies.remove(mTitle);
            ret = true;
        }
        return true;
    }

    public void modify(String mTitle, MovieDescription md) {
        debug("modifying movie titled: " + mTitle);
        movies.put(mTitle, md);
    }

    public List<String> getTitlesInGenre(String genre) {
        Iterator<Map.Entry<String, MovieDescription>> it = movies.entrySet().iterator();
        List<String> titlesInGenre = new ArrayList<String>();
        while (it.hasNext()) {
            Map.Entry<String, MovieDescription> entry = it.next();
            MovieDescription md = entry.getValue();
            if(genre.equals(md.getGenre())) {
                titlesInGenre.add(md.getTitle());
            }
        }
        return titlesInGenre;
    }

    public MovieDescription getMovieDescription(String mTitle) {
        return movies.get(mTitle);
    }
}
