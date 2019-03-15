package com.takealot.justjava;

import android.content.Intent;
import android.net.Uri;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.net.URI;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private static final int SINGLE_COFFEE_PRICE = 26;
    private static final int MAX_QUANTITY = 20;
    private static final int MIN_QUANTITY = 0;
    private static final String MAILTO_ADDRESS="kaylen.pillay@takealot.com";
    private static final String MAILTO_SUBJECT="Just Java Order:";
    private static final int MAILTO_REQUEST_CODE = 1;

    private Order order;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initialize();
    }
    
    private void initialize() {
        // Create the order object
        order = new Order();

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

        // Setting the onClickListener for the toppings checkboxes.
        CheckBox whippedCreamCheckBox = findViewById(R.id.whipped_cream_option);
        CheckBox chocolateCheckBox = findViewById(R.id.chocolate_option);

        whippedCreamCheckBox.setOnClickListener(toppingsCheckBoxOnClickListener);
        chocolateCheckBox.setOnClickListener(toppingsCheckBoxOnClickListener);
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
        order.setOrderQuantity(currentQuantity + 1);
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
        order.setOrderQuantity(currentQuantity - 1);
        return true;
    }

    private void increasePrice() {
        // Get the current price
        int currentTotalPrice = getCurrentTotalPrice();
        // Set to the new price
        setTotalPrice(currentTotalPrice + SINGLE_COFFEE_PRICE);
        order.setOrderPrice(currentTotalPrice + SINGLE_COFFEE_PRICE);
    }

    private void decreasePrice() {
        // Get the current price
        int currentTotalPrice = getCurrentTotalPrice();
        // Set to the new price
        setTotalPrice(currentTotalPrice - SINGLE_COFFEE_PRICE);
        order.setOrderPrice(currentTotalPrice - SINGLE_COFFEE_PRICE);
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

    private void sendOrderToEmail() {
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setType("text/plain");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, MAILTO_ADDRESS);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, MAILTO_SUBJECT);
        emailIntent.putExtra(Intent.EXTRA_TEXT, order.toString());

        if (emailIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(emailIntent, MAILTO_REQUEST_CODE);
        }
    }

    private String getName() {
        EditText nameEditText = findViewById(R.id.name_field);
        return nameEditText.getText().toString();
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
            order.setCustomerName(getName());
            sendOrderToEmail();
        }
    };

    private View.OnClickListener toppingsCheckBoxOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.whipped_cream_option:
                    if (((CheckBox)v).isChecked()){
                        order.addToppingToOrder("Whipped cream");
                    } else{
                        order.removeToppingFromOrder("Whipped cream");
                    }
                    break;
                case R.id.chocolate_option:
                    if (((CheckBox)v).isChecked()) {
                        order.addToppingToOrder("Chocolate sauce");
                    } else {
                        order.removeToppingFromOrder("Chocolate sauce");
                    }
                    break;
                default:
            }
        }
    };
}
