package com.example.connectfour547;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class WinnerScreen extends Activity {

    int winner;
    MediaPlayer winSound,buttonSound;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_winner_screen);
        winSound = MediaPlayer.create(this,R.raw.win);
        buttonSound = MediaPlayer.create(this,R.raw.button);
        winSound.start();
        Intent intent = getIntent();
        winner = intent.getIntExtra("winner",0);
        TextView textView = findViewById(R.id.winnerTextView);
        if(winner==0)
            textView.setText("Its a Draw :)");
        else
            textView.setText("Congrats Player "+(winner==1?1:2));
    }

    public void restart(View view){
        Intent restart = new Intent(this,MainActivity.class);
        Intent in = getIntent();
        restart.putExtra("rows",in.getIntExtra("rows",6));
        restart.putExtra("cols",in.getIntExtra("cols",7));
        winSound.stop();
        buttonSound.start();
        startActivity(restart);
        finish();
    }

    public void mainMenu(View view){
        Intent mainMenu = new Intent(this,MainMenu.class);
        winSound.stop();
        buttonSound.start();
        startActivity(mainMenu);
        finish();
    }
}
