package com.example.cz264.calculator;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView resultTv;
    EditText percentTf, numTf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        resultTv = (TextView) findViewById(R.id.resultTv);
        percentTf = (EditText) findViewById(R.id.percentTf);
        percentTf.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                percentTf.setText("");
            }
        });

        numTf = (EditText) findViewById(R.id.numTf);
        numTf.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                numTf.setText("");
            }
        });

        Button calBt = (Button) findViewById(R.id.calBt);
        calBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double percentage = Double.parseDouble(percentTf.getText().toString()) / 100;
                double num = Double.parseDouble(numTf.getText().toString());
                double res = percentage * num;
                resultTv.setText(String.valueOf(res));
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
