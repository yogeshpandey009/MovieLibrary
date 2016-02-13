package edu.asu.msse.ypandey.labassign3;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
 * Purpose: main activity in a simple app to demonstrate expandable list view control and adapter.
 *
 * @author Yogesh Pandey ypandey@asu.edu
 *         MS Software Engineering, CIDSE, Arizona State University Polytechnic
 * @version February 08, 2016
 */
public class ExpandableStuffAdapter extends BaseExpandableListAdapter
        implements View.OnTouchListener,
        ExpandableListView.OnGroupExpandListener,
        ExpandableListView.OnGroupCollapseListener{
    private TextView currentSelectedTV = null;
    private MainActivity parent;
    private List<String> genre;

    //linked hash map ensures consistent order for iteration and toarray.
    private MovieLibrary movieLib;

    public ExpandableStuffAdapter(MainActivity parent, MovieLibrary movieLib) {
        android.util.Log.d(this.getClass().getSimpleName(), "in constructor so creating new model");
        this.movieLib = movieLib;
        this.parent = parent;
        genre = Arrays.asList(parent.getResources().getStringArray(R.array.genre_arrays));
        parent.elview.setOnGroupExpandListener(this);
        parent.elview.setOnGroupCollapseListener(this);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        List<String> moviesOfGenere = movieLib.getTitlesInGenre(genre.get(groupPosition));
        return moviesOfGenere.get(childPosition);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(int groupPosition, final int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {
        final String childText = (String) getChild(groupPosition, childPosition);
        if (convertView == null) {
            android.util.Log.d(this.getClass().getSimpleName(),"in getChildView null so creating new view");
            LayoutInflater inflater = (LayoutInflater) this.parent
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_item, null, false);
        }
        TextView txtListChild = (TextView)convertView.findViewById(R.id.lblListItem);
        convertView.setOnTouchListener(this);
        convertView.setBackgroundResource(R.color.dark_blue);
        txtListChild.setText(childText);
        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {

        List<String> moviesOfGenere = movieLib.getTitlesInGenre(genre.get(groupPosition));
        //android.util.Log.d(this.getClass().getSimpleName(),"in getChildrenCount for: "+groupPosition+" returning: "+
        //                   model.get(stuffTitles[groupPosition]).length);
        return moviesOfGenere.size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        //android.util.Log.d(this.getClass().getSimpleName(),"in getGroup returning: "+stuffTitles[groupPosition]);
        return genre.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        //android.util.Log.d(this.getClass().getSimpleName(),"in getGroupCount returning: "+stuffTitles.length);
        return genre.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        //android.util.Log.d(this.getClass().getSimpleName(),"in getGroupPosition returning: "+groupPosition);
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        String headerTitle = (String)getGroup(groupPosition);
        if (convertView == null) {
            android.util.Log.d(this.getClass().getSimpleName(),"in getGroupView null so creating new view");

            LayoutInflater inflater = (LayoutInflater) this.parent.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_group, null);
        }

        TextView lblListHeader = (TextView) convertView.findViewById(R.id.lblListHeader);
        //android.util.Log.d(this.getClass().getSimpleName(),"in getGroupView text is: "+lblListHeader.getText());
        lblListHeader.setTypeface(null, Typeface.BOLD);
        lblListHeader.setText(headerTitle);

        ImageView img = (ImageView)convertView.findViewById(R.id.group_image);
        if("Drama".equals(headerTitle)) {
            img.setImageResource(R.drawable.ic_drama);
        } else if("Comedy".equals(headerTitle)) {
            img.setImageResource(R.drawable.ic_comedy);
        } else if("Action".equals(headerTitle)){
            img.setImageResource(R.drawable.ic_action);
        } else if("Romance".equals(headerTitle)){
            img.setImageResource(R.drawable.ic_romance);
        } else if("Mystery".equals(headerTitle)){
            img.setImageResource(R.drawable.ic_mystery);
        } else if("Horror".equals(headerTitle)){
            img.setImageResource(R.drawable.ic_horror);
        } else if("Thriller".equals(headerTitle)){
            img.setImageResource(R.drawable.ic_thriller);
        } else if("Sci-Fi".equals(headerTitle)){
            img.setImageResource(R.drawable.ic_scifi);
        }
        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        //android.util.Log.d(this.getClass().getSimpleName(),"in hasStableIds returning false");
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        //String[] stuffTitles = model.keySet().toArray(new String[] {});
        //android.util.Log.d(this.getClass().getSimpleName(),"in isChildSelectable?  "+
        //                   model.get(stuffTitles[groupPosition])[childPosition]);
        return true;
    }

    public boolean onTouch(View v, MotionEvent event){
        // when the user touches an item, onTouch is called for action down and again for action up
        // we only want to do something on one of those actions. event tells us which action.
        if (event.getAction()==MotionEvent.ACTION_DOWN){
            android.util.Log.d(this.getClass().getSimpleName(),"in onTouch called for view of type: " +
                    v.getClass().toString());
            // onTouch is passed the textview's parent view, a linearlayout - what we set the
            // event on. Look at its children to find the textview
            if(v instanceof android.widget.LinearLayout){
                android.widget.LinearLayout layView = (android.widget.LinearLayout)v;
                for(int i=0; i<=layView.getChildCount(); i++){
                    if(layView.getChildAt(i) instanceof TextView){
                        // keep track of TV stuff was most recently touched to un-highlighted
                        if (currentSelectedTV != null){
                            currentSelectedTV.setBackgroundColor(
                                    parent.getResources().getColor(R.color.dark_blue));
                        }
                        TextView tmp = ((TextView)layView.getChildAt(i));
                        tmp.setBackgroundColor(Color.BLUE);
                        currentSelectedTV = tmp;
                        showMovieDialog(((TextView)layView.getChildAt(i)).getText().toString());
                        android.util.Log.d(this.getClass().getSimpleName(),"TextView "+
                                ((TextView)layView.getChildAt(i)).getText()+" selected.");
                    }
                }
            }
            // code below never executes. onTouch is called for textview's linearlayout parent
            if(v instanceof TextView){
                android.util.Log.d(this.getClass().getSimpleName(),"in onTouch called for: " +
                        ((TextView)v).getText());
            }
        }
        return true;
    }

    @Override
    public void onGroupExpand(int groupPosition){
        android.util.Log.d(this.getClass().getSimpleName(),"in onGroupExpand called for: "+
                genre.get(groupPosition));
        if (currentSelectedTV != null){
            currentSelectedTV.setBackgroundColor(parent.getResources().getColor(R.color.dark_blue));
            currentSelectedTV = null;
        }
        for (int i=0; i< this.getGroupCount(); i++) {
            if(i != groupPosition){
                parent.elview.collapseGroup(i);
            }
        }
    }

    @Override
    public void onGroupCollapse(int groupPosition){
        android.util.Log.d(this.getClass().getSimpleName(),"in onGroupCollapse called for: "+
                genre.get(groupPosition));
        if (currentSelectedTV != null){
            currentSelectedTV.setBackgroundColor(parent.getResources().getColor(R.color.dark_blue));
            currentSelectedTV = null;
        }
    }


    public void showMovieDialog(String mTitle) {
        Intent i = new Intent(parent, DialogActivity.class);
        //MovieDescription md = movieLib.getMovieDescription(mTitle);
        i.putExtra("action", "view");
        i.putExtra("mTitle", mTitle);
        //i.putExtra("movie", md.toJsonString());
        //Log.d(this.getClass().getSimpleName(), md.toJsonString());
        parent.startActivity(i);
    }

    public void filterData(String query){
        Log.d(this.getClass().getSimpleName(), "Query String:" + query);
        String[] all_genre = parent.getResources().getStringArray(R.array.genre_arrays);
        query = query.trim().toLowerCase();
        List<String> filterd_genre = new ArrayList<String>();

        if(query.isEmpty()){
            genre = Arrays.asList(all_genre);
        }
        else {
            for(String gen: all_genre){

                List<String> genreMovies = movieLib.getTitlesInGenre(gen);
                List<String> filteredMovies = new ArrayList<String>();
                for(String movie: genreMovies){
                    if(movie.toLowerCase().contains(query)){
                        filteredMovies.add(movie);
                    }
                }
                if(filteredMovies.size() > 0){
                    filterd_genre.add(gen);
                }
            }
            genre = filterd_genre;
        }
        notifyDataSetChanged();

    }

}
