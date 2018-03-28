package com.example.sanjuparihar.toolbox;

import android.content.Context;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;


public class note implements Serializable {

    private long mDateTime;
    private String mTitle;
    private String mContent;

    public note(long dateTime, String title,String content ){
        mContent =content;
        mDateTime=dateTime;
        mTitle = title;
    }

    public long getDateTime() {
        return mDateTime;
    }

    public void setDateTime(long dateTime) {
      mDateTime = dateTime;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getContent() {
        return mContent;
    }

    public void setContent(String content) {
        mContent = content;
    }


    public String getDateTimeFormatted(Context context){
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss"
                   ,context.getResources().getConfiguration().locale);
        sdf.setTimeZone(TimeZone.getDefault());
        return  sdf.format(new Date(mDateTime));

    }
}
