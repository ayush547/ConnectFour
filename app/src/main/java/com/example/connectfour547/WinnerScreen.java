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
        winner = intent.getIntExtra("winner",0); //-5 and 5 for comp and player respectively
        TextView textView = findViewById(R.id.winnerTextView);
        if(winner==0)
            textView.setText("Its a Draw :)");
        else if(winner==1||winner==-1)
            textView.setText("Congrats Player "+(winner==1?1:2));
        else if(winner==+5)
            textView.setText("AI Defeated!");
        else if(winner==-5)
            textView.setText("You Lose!");

    }

    public void restart(View view){
        Intent in = getIntent();
        Intent restartMain = new Intent(this,MainActivity.class);
        restartMain.putExtra("rows",in.getIntExtra("rows",6));
        restartMain.putExtra("cols",in.getIntExtra("cols",7));
        Intent restartSingle = new Intent(this,SinglePlayer.class);
        restartSingle.putExtra("rows",in.getIntExtra("rows",6));
        restartSingle.putExtra("cols",in.getIntExtra("cols",7));
        winSound.stop();
        buttonSound.start();
        if(in.getIntExtra("mode",1)==1) //mode 1 means MainActivity mode 2 means SinglePlayer
            startActivity(restartMain);
        else
            startActivity(restartSingle);
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
