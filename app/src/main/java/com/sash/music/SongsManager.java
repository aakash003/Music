package com.sash.music;

import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Aakash on 24-Aug-16.
 */
public class SongsManager {

    final String MEDIA_PATH =  new String("/sdcard0/");
    public ArrayList<HashMap<String, String>> songlist = new ArrayList<HashMap<String, String>>();
    public SongsManager()
    {
        getPlayList(Environment.getExternalStorageDirectory());
    }

   /* public ArrayList<HashMap<String, String>> getPlaylist()           //to read all mp3 from sdcard and store in arrayList
    {
        File home =new File(MEDIA_PATH);
        if (home.listFiles(new FileExtensionFilter()).length > 0)
        {
            for (File file : home.listFiles(new FileExtensionFilter()))
            {
                HashMap<String, String> song = new HashMap<String, String>();
                song.put("songTitle", file.getName().substring(0, file.getName().length() - 4));//.mp3
                song.put("songPath", file.getPath());
                Log.d("Music added","Added");
                songlist.add(song);
            }
        }
        return songlist;
    }*/
    public void getPlayList(File root)
    {
        File[] files = root.listFiles();
        for(File singleFile : files)
        {
            if(singleFile.isDirectory()&&!singleFile.isHidden())
            {
                getPlayList(singleFile);
            }
            else
            {
                if(singleFile.getName().endsWith(".mp3"))
                {
                    HashMap<String,String>song=new HashMap<String,String>();
                    song.put("songTitle", singleFile.getName().substring(0, singleFile.getName().length() - 4));//.mp3
                    song.put("songPath", singleFile.getPath());
                    Log.d("Music added","Added");
                    songlist.add(song);
                }
            }
        }
    }

    class FileExtensionFilter implements FilenameFilter
    {               //filtering out the mp3 files
        public boolean accept(File dir, String name)
        {
            return (name.endsWith(".mp3") || name.endsWith(".MP3"));
        }
    }
}
