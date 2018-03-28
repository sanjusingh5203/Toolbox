package com.example.sanjuparihar.toolbox;

import android.content.Context;
import android.provider.ContactsContract.CommonDataKinds.Note;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;


public class NoteUtilities {
    public static final String FILE_EXTENSION = ".bin";

    public  static  boolean saveNotes(Context context,Note note){
      String fileName = String.valueOf(note.toString()) + FILE_EXTENSION;

        FileOutputStream fos;
        ObjectOutputStream oos;

        try {
            fos = context.openFileOutput(fileName, context.MODE_PRIVATE);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(note);
            oos.close();
            fos.close();
        }catch(IOException e){
            e.printStackTrace();
            return false;
        }
        return true;

    }

}
