package com.example.connectfour547;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainMenu extends Activity {

    Intent out;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
    }

    public void singlePlayer(View view) {  //here
    }

    public void doublePlayer(View view) {
        out = new Intent(this,MainActivity.class);
        startActivity(out);
        finish();
    }

    public void adminPanel(View view) {
        out = new Intent(this,AdminPanel.class);
        startActivity(out);
        finish();
    }
}
