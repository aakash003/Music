package com.sash.music;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.LinearLayoutManager;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Aakash on 24-Aug-16.
 */
public class Playlist extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    //private RecyclerView.Adapter mAdapter;
    Context context;
    public MyAdapter adapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private Toolbar toolbar;
    public ArrayList<HashMap<String, String>> mDataset = new ArrayList<HashMap<String, String>>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.playlist);
        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        toolbar=(Toolbar)findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        adapter = new MyAdapter(getApplicationContext(),new SongsManager().songlist);
        //adapter.add(mDataset);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));

        mRecyclerView.setAdapter(adapter);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(context, mRecyclerView ,new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        int songIndex = position;
                        // Starting new intent
                        Intent in = new Intent(getApplicationContext(),
                              MusicPlayer.class);
                        // Sending songIndex to PlayerActivity
                        in.putExtra("songIndex", songIndex);
                        setResult(100, in);
                        startActivity(in);
                        // Closing PlayListView
                        //finish();
                    }

                    @Override
                    public void onLongItemClick(View view, int position) {

                    }
                })
        );
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_playlist,menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int id=item.getItemId();
        if(id==R.id.action_settings)
        {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
