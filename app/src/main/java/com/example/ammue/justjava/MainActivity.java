package com.example.ammue.justjava;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int quantity = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void submitOrder(View view) {
      /*  display(quantity);
        displayPrice(quantity * 5); */
        CheckBox whippingCreamCheckbox = (CheckBox) findViewById(R.id.checkBox_wc);
        boolean hasWhippingCream = whippingCreamCheckbox.isChecked();
        CheckBox chocalateCheckbox = (CheckBox) findViewById(R.id.checkBox_ch);
        boolean hasChocalate = chocalateCheckbox.isChecked();
        int price = calculatePrice(hasWhippingCream, hasChocalate);

        String priceMessage = createOrderSummary(price, hasWhippingCream, hasChocalate);
        String[] addresses = {"learnfrommynotebook@gmail.com"};
        composeEmail(addresses, "Coffee order", priceMessage);
      /*  displayMessage(priceMessage);            */

    }

    public void showMap(Uri geoLocation) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(geoLocation);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    public void composeEmail(String[] addresses, String subject, String text) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_EMAIL, addresses);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, text);

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    public int calculatePrice(boolean hasWhippingCream, boolean hasChocalate) {
        int pricePerCup = 10;

        int price = quantity * pricePerCup;

        if (hasChocalate) {
            pricePerCup = pricePerCup + 2;
        }
        if (hasWhippingCream) {
            pricePerCup = pricePerCup + 3;
        }

        return quantity * pricePerCup;
    }

    public String createOrderSummary(int price, boolean hasWhippingCream, boolean hasChocalate) {
        EditText name = (EditText) findViewById(R.id.editText);
        String orderSummary = getString(R.string.name) + " : " + name.getText().toString() + "\n";
        orderSummary += getString(R.string.whipping_Cream) + " : " + hasWhippingCream + "\n";
        orderSummary += getString(R.string.chocalate) + " : " + hasChocalate + "\n";
        orderSummary += getString(R.string.quantity) + " : " + quantity + "\n";
        orderSummary += getString(R.string.total) + " : $" + price + "\n";
        orderSummary += getString(R.string.thank_you);
        return orderSummary;
    }

    public void increment(View view) {
        quantity++;
        display(quantity);
    }

    public void decrement(View view) {
        quantity--;
        display(quantity);

    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = findViewById(R.id.qNumber);
        quantityTextView.setText("" + number);
    }

}