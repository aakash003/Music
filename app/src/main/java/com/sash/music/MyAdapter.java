package com.sash.music;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Aakash on 28-Aug-16.
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {



public LayoutInflater inflater;
    Context context;

    public ArrayList<HashMap<String, String>> mDataset = new ArrayList<HashMap<String, String>>();
    SongsManager plm =  new SongsManager();
    public MyAdapter(Context context,ArrayList<HashMap<String, String>> mDataset) {
        this.context=context;
        inflater=LayoutInflater.from(context);
        this.mDataset=mDataset;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = inflater.inflate(R.layout.playlist_item, parent, false);
        // set the view's size, margins, paddings and layout parameters
         ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position)
    {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        final String name = mDataset.get(position).get("songTitle");

        holder.txtHead.setText(name.substring(0,name.indexOf("-")-1));
        holder.txtFoot.setText(name.substring(name.indexOf("-")+1,name.length()));
        //holder.txtFoot.setText("Path: " + mDataset.get(position).get("songPath"));

    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView txtHead;
        public TextView txtFoot;
        public ImageView img;

        public ViewHolder(View v)
        {
            super(v);
            txtHead=(TextView)v.findViewById(R.id.firstLine);
            txtFoot=(TextView)v.findViewById(R.id.secondLine);
            img=(ImageView)v.findViewById(R.id.icon);
        }

    }


    public void add(ArrayList<HashMap<String, String>> songsList )
    {
        songsList=plm.songlist;
        // looping through playlist
        /*for (int i = 0; i < songsList.size(); i++) {
            // creating new HashMap
            HashMap<String, String> song = songsList.get(i);

            // adding HashList to ArrayList
            mDataset.add(song);
        }*/
        notifyDataSetChanged();

    }

}
