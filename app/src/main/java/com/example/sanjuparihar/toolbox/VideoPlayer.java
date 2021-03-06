package com.example.sanjuparihar.toolbox;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Created by sanju parihar on 14-11-2017.
 */

public class VideoPlayer extends AppCompatActivity{
    private Cursor videocursor;
    private int video_column_index;
    ListView videolist;
    int count;
    String[] thumbColumns = { MediaStore.Video.Thumbnails.DATA,
            MediaStore.Video.Thumbnails.VIDEO_ID };

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_player);
        init_phone_video_grid();
    }

    @SuppressWarnings("deprecation")
    private void init_phone_video_grid() {
        System.gc();
        String[] proj = { MediaStore.Video.Media._ID,
                MediaStore.Video.Media.DATA,
                MediaStore.Video.Media.DISPLAY_NAME,
                MediaStore.Video.Media.SIZE };
        videocursor = managedQuery(MediaStore.Video.Media.EXTERNAL_CONTENT_URI,
                proj, null, null, null);

        count = videocursor.getCount();
        videolist = (ListView) findViewById(R.id.VideoList);
        videolist.setAdapter(new VideoAdapter(getApplicationContext()));
        videolist.setOnItemClickListener(videogridlistener);
    }

    private AdapterView.OnItemClickListener videogridlistener = new AdapterView.OnItemClickListener() {
        public void onItemClick(AdapterView parent, View v, int position,
                                long id) {
            System.gc();
            video_column_index = videocursor
                    .getColumnIndexOrThrow(MediaStore.Video.Media.DATA);
            videocursor.moveToPosition(position);
            String filename = videocursor.getString(video_column_index);
            Intent intent = new Intent(VideoPlayer.this,
                    play_video.class);
            intent.putExtra("videofilename", filename);
            startActivity(intent);
        }
    };

    public class VideoAdapter extends BaseAdapter {
        private Context vContext;

        public VideoAdapter(Context c) {
            vContext = c;
        }

        public int getCount() {
            return count;
        }

        public Object getItem(int position) {
            return position;
        }

        public long getItemId(int position) {
            return position;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            System.gc();
            ViewHolder holder;
            String id = null;
            convertView = null;
            if (convertView == null) {
                convertView = LayoutInflater.from(vContext).inflate(
                        R.layout.video_list, parent, false);
                holder = new ViewHolder();
                holder.txtTitle = (TextView) convertView
                        .findViewById(R.id.title);
                holder.txtSize = (TextView) convertView
                        .findViewById(R.id.size);
                holder.thumbImage = (ImageView) convertView
                        .findViewById(R.id.VideosImage);

                video_column_index = videocursor
                        .getColumnIndexOrThrow(MediaStore.Video.Media.DISPLAY_NAME);
                videocursor.moveToPosition(position);
                id = videocursor.getString(video_column_index);
                video_column_index = videocursor
                        .getColumnIndexOrThrow(MediaStore.Video.Media.SIZE);
                videocursor.moveToPosition(position);
                // id += " Size(KB):" +
                // videocursor.getString(video_column_index);
                holder.txtTitle.setText(id);
                holder.txtSize.setText(" Size(KB):"
                        + videocursor.getString(video_column_index));

                String[] proj = { MediaStore.Video.Media._ID,
                        MediaStore.Video.Media.DISPLAY_NAME,
                        MediaStore.Video.Media.DATA };
               // @SuppressWarnings("deprecation")
                Cursor cursor = managedQuery(
                        MediaStore.Video.Media.EXTERNAL_CONTENT_URI, proj,
                        MediaStore.Video.Media.DISPLAY_NAME + "=?",
                        new String[] { id }, null);
                cursor.moveToFirst();
                long ids = cursor.getLong(cursor
                        .getColumnIndex(MediaStore.Video.Media._ID));

                ContentResolver crThumb = getContentResolver();
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inSampleSize = 1;
                Bitmap curThumb = MediaStore.Video.Thumbnails.getThumbnail(
                        crThumb, ids, MediaStore.Video.Thumbnails.MICRO_KIND,
                        options);
                holder.thumbImage.setImageBitmap(curThumb);
                curThumb = null;

            } /*
    * else holder = (ViewHolder) convertView.getTag();
    */
            return convertView;
        }
    }

    static class ViewHolder {

        TextView txtTitle;
        TextView txtSize;
        ImageView thumbImage;
    }
}

