package com.takealot.justjava;

import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private static final int SINGLE_COFFEE_PRICE = 26;
    private static final int MAX_QUANTITY = 10;
    private static final int MIN_QUANTITY = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initialize();
    }

    private void initialize() {
        ConstraintLayout quantitySelector = findViewById(R.id.quantity_selector);

        // Trying to access the buttons in the quantitySelector child.
        // Could this be done better? Ask Jason/Jean-Louis if they know's a better way.
        // Initial thought:
        //        int decreaseButtonIndex = 0;
        //        int increaseButtonIndex = 2;
        //        Button increaseButton = (Button) quantitySelector.getChildAt(increaseButtonIndex);
        //        Button decreaseButton = (Button) quantitySelector.getChildAt(decreaseButtonIndex);
        //
        // Using ViewGroup's findViewByID

        Button increaseButton = quantitySelector.findViewById(R.id.increase_quantity);
        Button decreaseButton = quantitySelector.findViewById(R.id.decrease_quantity);

        increaseButton.setOnClickListener(increaseButtonClickListener);
        decreaseButton.setOnClickListener(decreaseButtonClickListener);

        // Setting the OnClickListener for the order button
        Button orderButton = findViewById(R.id.order_button);
        orderButton.setOnClickListener(orderButtonClickListener);
    }

    private boolean increaseQuantity() {
        // Get the current quantity
        int currentQuantity = getCurrentQuantity();
        if (currentQuantity == MAX_QUANTITY) {
            // Error
            // Reason: cannot increase the quantity above the maximum.
            return false;
        }

        setQuantity(currentQuantity + 1);
        return true;
    }

    private boolean decreaseQuantity() {
        // Get the current quantity
        int currentQuantity = getCurrentQuantity();
        if( currentQuantity == MIN_QUANTITY ){
            // Error
            // Reason: cannot decrease the quantity below the minimum.
            return false;
        }

        setQuantity(currentQuantity - 1);
        return true;
    }
    
    private void increasePrice() {
        // Get the current price
        // TODO: Implement this function.
    }

    private int getCurrentQuantity() {
        TextView quantityTextView = findViewById(R.id.quantity);
        return Integer.parseInt(quantityTextView.getText().toString());
    }

    private void setQuantity(int quantity) {
        TextView quantityTextView = findViewById(R.id.quantity);
        quantityTextView.setText(String.format(Locale.getDefault(),"%d" ,quantity));
    }

    private View.OnClickListener increaseButtonClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (! increaseQuantity()) {
                Toast.makeText(getApplicationContext(), "Cannot increase", Toast.LENGTH_SHORT).show();
            }
        }
    };

    private View.OnClickListener decreaseButtonClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (! decreaseQuantity()) {
                Toast.makeText(getApplicationContext(), "Cannot decrease", Toast.LENGTH_SHORT).show();
            }
        }
    };

    private View.OnClickListener orderButtonClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Toast.makeText(getApplicationContext(), "Order coffee!", Toast.LENGTH_SHORT).show();
        }
    };
}
