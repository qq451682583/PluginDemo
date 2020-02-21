package com.android.plugin_model2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.android.grouter.GRouterManager;
import com.android.grouter_annotaion.GProtocol;

/**
 * @author holy
 */
@GProtocol("holy://model2/Model2Activity")
public class Model2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_model2);

        findViewById(R.id.txt).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("holy", "plugin demo");
                GRouterManager.getInstance().startIntent(Model2Activity.this, "holy://model1/Model1Activity", bundle);
            }
        });
    }
}
