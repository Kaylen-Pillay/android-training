package com.takealot.viewmylyout;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button displayMessageButton = (Button) findViewById(R.id.display_message_button);
        displayMessageButton.setOnClickListener(displayMessageButtonClickListener);
    }

    private View.OnClickListener displayMessageButtonClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            TextView displayMessageTextView = (TextView) findViewById(R.id.display_message);
            Button displayMessageButton = (Button) view;

            // Toggle the display message.
            if (displayMessageTextView.isShown()) {
                displayMessageButton.setText(getResources().getString(R.string.show));
                displayMessageTextView.setVisibility(View.GONE);
            } else {
                displayMessageButton.setText(getResources().getString(R.string.hide));
                displayMessageTextView.setVisibility(View.VISIBLE);

                if (displayMessageTextView.getText().equals("")) {
                    displayMessageTextView.setText(getResources().getString(R.string.long_string));
                }
            }
        }
    };
}
