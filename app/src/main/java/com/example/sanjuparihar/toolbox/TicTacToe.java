package com.example.sanjuparihar.toolbox;

/**
 * Created by sanju parihar on 20-11-2017.
 */

import android.app.Activity;
import android.os.Bundle;

public class TicTacToe extends Activity {
    private Game game1;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        game1 = new Game(this);
        setContentView(game1);
    }
}