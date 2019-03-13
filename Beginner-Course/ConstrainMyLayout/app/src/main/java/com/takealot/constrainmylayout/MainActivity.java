package com.takealot.constrainmylayout;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeList();
    }

    private void initializeList() {
        LinearLayout listings = (LinearLayout) findViewById(R.id.listings);
        int total = 2;

        for (int i = 0; i < total; i ++) {
            View listItem = getLayoutInflater().inflate(R.layout.my_fancy_list_item, null);
            listings.addView(listItem);
        }
    }
}
