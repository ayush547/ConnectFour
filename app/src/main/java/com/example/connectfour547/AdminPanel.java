package com.example.connectfour547;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class AdminPanel extends Activity {

    int rows,cols;
    MediaPlayer buttonSound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_panel);
        buttonSound = MediaPlayer.create(this,R.raw.button);
    }

    public void save(int i) {
        buttonSound.start();
        EditText editText1,editText2;
        TextView textView = findViewById(R.id.check);
        editText1 = findViewById(R.id.editText);
        editText2 = findViewById(R.id.editText2);
        rows = Integer.parseInt("0"+editText1.getText().toString());
        cols = Integer.parseInt("0"+editText2.getText().toString());

        if(rows<3||rows>10||cols<3||cols>10) textView.setText("Invalid");
        else
        {
            Intent outDouble = new Intent(this,MainActivity.class);
            outDouble.putExtra("rows",rows);
            outDouble.putExtra("cols",cols);
            Intent outSingle = new Intent(this,SinglePlayer.class);
            outSingle.putExtra("rows",rows);
            outSingle.putExtra("cols",cols);
            if(i==1)
                startActivity(outSingle);
            else
                startActivity(outDouble);
            finish();
        }
    }

    public void doubleStart(View view) {
        save(2);
    }

    public void singleStart(View view) {
        save(1);
    }
}
