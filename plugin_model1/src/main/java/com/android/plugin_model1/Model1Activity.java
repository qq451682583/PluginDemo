package com.android.plugin_model1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.android.grouter_annotaion.GProtocol;


/**
 * @author holy
 */
@GProtocol("holy://model1/Model1Activity")
public class Model1Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_model1);

        ((TextView)findViewById(R.id.txt)).setText(getIntent().getStringExtra("holy"));

        Log.w("holy", getIntent().getStringExtra("holy"));
    }
}
