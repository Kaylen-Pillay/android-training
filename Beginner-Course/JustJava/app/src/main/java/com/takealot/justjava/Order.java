package com.takealot.justjava;

import android.support.annotation.NonNull;

import java.util.ArrayList;

public class Order {
    private ArrayList<String> m_ToppingsList;
    private String m_CustomerName;
    private int m_OrderPrice;
    private int m_OrderQuantity;

    public Order() {
        m_ToppingsList = new ArrayList<>();
        m_OrderPrice = 0;
        m_OrderQuantity = 0;
    }

    public void setCustomerName(String name) {
        m_CustomerName = name;
    }

    public String getCustomerName() {
        if (m_CustomerName.length() > 0) {
            return m_CustomerName;
        } else {
            return "No Customer Name";
        }
    }

    public void setOrderPrice(int orderPrice) {
        m_OrderPrice = orderPrice;
    }

    public int getOrderPrice() {
        return m_OrderPrice;
    }

    public void setOrderQuantity(int quantity) {
        m_OrderQuantity = quantity;
    }

    public int getOrderQuantity() {
        return m_OrderQuantity;
    }

    public void addToppingToOrder(String toppingName) {
        if (! m_ToppingsList.contains(toppingName)) {
            m_ToppingsList.add(toppingName);
        }
    }

    public void removeToppingFromOrder(String toppingName) {
        m_ToppingsList.remove(toppingName);
    }

    @Override
    @NonNull
    public String toString() {
        // Initialize the string builder to the customers name
        StringBuilder orderStringBuilder = new StringBuilder("Customer Name: " + m_CustomerName + "\n");
        // Add the users toppings to the order string
        orderStringBuilder.append("Toppings: [ ");
        for(String topping: m_ToppingsList) {
            orderStringBuilder.append(topping);
            orderStringBuilder.append(", ");
        }
        orderStringBuilder.append("]\n");
        // Add the quantity to the order
        orderStringBuilder.append("Quantity: ");
        orderStringBuilder.append(m_OrderQuantity);
        orderStringBuilder.append("\n");
        // Add the total price to the order
        orderStringBuilder.append("Total R ");
        orderStringBuilder.append(m_OrderPrice);

        return orderStringBuilder.toString();
    }
}
