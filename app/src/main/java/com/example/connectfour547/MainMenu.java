package com.example.connectfour547;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;

public class MainMenu extends Activity {

    Intent out;
    MediaPlayer buttonSound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        buttonSound = MediaPlayer.create(this,R.raw.button);
    }

    public void singlePlayer(View view) {
        buttonSound.start();
        out = new Intent(this,SinglePlayer.class);
        startActivity(out);
        finish();

    }

    public void doublePlayer(View view) {
        buttonSound.start();
        out = new Intent(this,MainActivity.class);
        startActivity(out);
        finish();
    }

    public void adminPanel(View view) {
        buttonSound.start();
        out = new Intent(this,AdminPanel.class);
        startActivity(out);
        finish();
    }
}
