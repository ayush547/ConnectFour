package com.example.connectfour547;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class AdminPanel extends Activity {

    int rows,cols;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_panel);
    }

    public void save(View view) {
        EditText editText1,editText2;
        TextView textView = findViewById(R.id.check);
        editText1 = findViewById(R.id.editText);
        editText2 = findViewById(R.id.editText2);
        rows = Integer.parseInt("0"+editText1.getText().toString());
        cols = Integer.parseInt("0"+editText2.getText().toString());

        if(rows<3||rows>10||cols<3||cols>10) textView.setText("Invalid");
        else
        {
            Intent out = new Intent(this,MainActivity.class);  //here
            out.putExtra("rows",rows);
            out.putExtra("cols",cols);
            startActivity(out);
            finish();
        }
    }
}
