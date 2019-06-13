package com.example.connectfour547;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class WinnerScreen extends Activity {

    int winner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_winner_screen);
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
        startActivity(restart);
        finish();
    }

    public void mainMenu(View view){
        Intent mainMenu = new Intent(this,MainMenu.class);
        startActivity(mainMenu);
        finish();
    }
}
