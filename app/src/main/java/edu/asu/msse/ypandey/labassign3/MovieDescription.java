package edu.asu.msse.ypandey.labassign3;

import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by yogeshpandey on 17/01/16.
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
 * This assignment shows the use of Android's
 * org.json package in creating json string of a Java object and showing it on UI
 *
 * Ser594 Mobile Systems
 * see http://pooh.poly.asu.edu/Mobile
 * @author Yogesh ypandey@asu.edu
 *         Student, MS Software Engineering, CIDSE, ASU Poly
 * @version January 2016
 *
 */
public class MovieDescription implements Serializable {


    private String title;
    private String year;
    private String rated;
    private String released;
    private String runtime;
    private String genre;
    private String actors;
    private String plot;

    public MovieDescription() {

    }
    public MovieDescription(String jsonStr, String title) {
        try {
            this.title = title;
            JSONObject jo = new JSONObject(jsonStr);
            year = jo.getString("Year");
            rated = jo.getString("Rated");
            released = jo.getString("Released");
            runtime = jo.getString("Runtime");
            genre = jo.getString("Genre");
            actors = jo.getString("Actors");
            plot = jo.getString("Plot");
        } catch (Exception ex) {
            android.util.Log.w(this.getClass().getSimpleName(),
                    "error converting to/from json");
        }
    }

    public String toJsonString() {
        String ret = "";
        try {
            JSONObject jo = new JSONObject();
            jo.put("Title", title);
            jo.put("Year", year);
            jo.put("Rated", rated);
            jo.put("Released", released);
            jo.put("Runtime", runtime);
            jo.put("Genre", genre);
            jo.put("Actors", actors);
            jo.put("Plot", plot);
            ret = jo.toString(3);
        } catch (Exception ex) {
            android.util.Log.w(this.getClass().getSimpleName(),
                    "error converting to/from json");
        }
        return ret;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getRated() {
        return rated;
    }

    public void setRated(String rated) {
        this.rated = rated;
    }

    public String getReleased() {
        return released;
    }

    public void setReleased(String released) {
        this.released = released;
    }

    public String getRuntime() {
        return runtime;
    }

    public void setRuntime(String runtime) {
        this.runtime = runtime;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getActors() {
        return actors;
    }

    public void setActors(String actors) {
        this.actors = actors;
    }

    public String getPlot() {
        return plot;
    }

    public void setPlot(String plot) {
        this.plot = plot;
    }
}
