package com.example.sanjuparihar.toolbox;

/**
 * Created by sanju parihar on 08-11-2017.
 */

public class songs {

    private long id;
    private String title;
    private String artist;

    public songs(long songID, String songTitle, String songArtist, double duration) {
        id=songID;
        title=songTitle;
        artist=songArtist;
    }

    public long getID(){return id;}
    public String getTitle(){return title;}
    public String getArtist(){return artist;}
}
