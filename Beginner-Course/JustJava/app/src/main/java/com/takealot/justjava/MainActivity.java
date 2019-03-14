package com.takealot.justjava;

import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private static final int SINGLE_COFFEE_PRICE = 26;
    private static final int MAX_QUANTITY = 20;
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
        // Could this be done better? Ask Jason/Jean-Louis if they know a better way.
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
        int currentTotalPrice = getCurrentTotalPrice();
        // Set to the new price
        setTotalPrice(currentTotalPrice + SINGLE_COFFEE_PRICE);
    }

    private void decreasePrice() {
        // Get the current price
        int currentTotalPrice = getCurrentTotalPrice();
        // Set to the new price
        setTotalPrice(currentTotalPrice - SINGLE_COFFEE_PRICE);
    }

    private int getCurrentTotalPrice() {
        TextView totalPrice = findViewById(R.id.total_price);
        return Integer.parseInt(totalPrice.getText().toString());
    }

    private void setTotalPrice(int total) {
        TextView totalPrice = findViewById(R.id.total_price);
        totalPrice.setText(String.format(Locale.getDefault(), "%d", total));
    }

    private int getCurrentQuantity() {
        TextView quantityTextView = findViewById(R.id.quantity);
        return Integer.parseInt(quantityTextView.getText().toString());
    }

    private void setQuantity(int quantity) {
        TextView quantityTextView = findViewById(R.id.quantity);
        quantityTextView.setText(String.format(Locale.getDefault(),"%d" ,quantity));
    }

    private void displayToast(String message) {
        View toastLayout = getLayoutInflater().inflate(R.layout.cutom_toast,(ViewGroup)findViewById(R.id.custom_toast_container));

        TextView toastMessageTextView = toastLayout.findViewById(R.id.text);
        toastMessageTextView.setText(message);

        Toast toast = new Toast(getApplicationContext());
        toast.setGravity(Gravity.FILL_HORIZONTAL | Gravity.BOTTOM, 0, 0);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(toastLayout);
        toast.show();
    }

    private View.OnClickListener increaseButtonClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (! increaseQuantity()) {
                displayToast("Cannot add more coffee's to your order.");
            } else {
                increasePrice();
            }
        }
    };

    private View.OnClickListener decreaseButtonClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (! decreaseQuantity()) {
                displayToast("Cannot decrease quantity of your order.");
            } else {
                decreasePrice();
            }
        }
    };

    private View.OnClickListener orderButtonClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            // Display the order details here.
            displayToast("Coffee ordered!");
        }
    };
}
