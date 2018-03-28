package com.example.sanjuparihar.toolbox;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class NotePad extends AppCompatActivity {


    private ListView mListViewNotes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_pad);

        mListViewNotes =(ListView)findViewById(R.id.ListView_notes);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_note,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_main_new_note:
                //start note activity in new note mode
                startActivity(new Intent(this,NoteActivity.class));
                break;

        }
        return  true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        mListViewNotes.setAdapter(null);

        ArrayList<note> notes = Utilities.getAllsavedNotes(this);

        if(notes == null || notes.size() == 0){
            Toast.makeText(this,"you have no saved notes!",Toast.LENGTH_SHORT).show();
            return;
        }else {
            NoteAdapter na = new NoteAdapter(this,R.layout.item_note,notes);
            mListViewNotes.setAdapter(na);

           mListViewNotes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
               @Override
               public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                   String fileName =((note)mListViewNotes.getItemAtPosition(position)).getDateTime()
                           + Utilities.FILE_EXTENSION;


                   Intent viewNoteIntent = new Intent(getApplicationContext(),NoteActivity.class);
                   viewNoteIntent.putExtra("NOTE_FILE",fileName);
                   startActivity(viewNoteIntent);
               }
           });
        }
    }
}
