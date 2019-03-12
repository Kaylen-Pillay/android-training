package com.takealot.viewmylyout;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView myName = (TextView) findViewById(R.id.TV_name);
    }

    public void onMakeLongString(View view) {
        TextView myName = findViewById(R.id.TV_name);
        myName.setText(getResources().getString(R.string.long_string));
        myName.setTextColor(getResources().getColor(R.color.takealotBlue));
    }
}
