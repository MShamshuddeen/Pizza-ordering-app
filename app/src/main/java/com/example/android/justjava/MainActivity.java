package com.example.android.justjava;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 *
 */
public class MainActivity extends ActionBarActivity {

    int quantity=2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the plus button is clicked.
     */
    public void increment(View view){
        if(quantity == 100){
            Toast.makeText(this, "You cannot have more than 100 coffees", Toast.LENGTH_SHORT).show();

            return;
        }
        quantity=quantity+1;
        displayQuantity(quantity);


    }
    /**
     * This method is called when the minus button is clicked.
     */
    public void decrement(View view){
        if(quantity == 1){
            Toast.makeText(this, "You cannot have less than 1 coffee", Toast.LENGTH_SHORT).show();
            return;
        }
        quantity=quantity-1;
        displayQuantity(quantity);
    }
    public void submitOrder(View view){


        // Adding Whipped Cream topping
        CheckBox whippedCreamCheckbox = (CheckBox) findViewById(R.id.whipped_cream_checkbox);
        boolean hasWhippedCream = whippedCreamCheckbox.isChecked();
//
//        //adding Chocolate topping
        CheckBox ChocolateCheckbox = (CheckBox) findViewById(R.id.chocolate_checkbox);
        boolean hasChocolate = ChocolateCheckbox.isChecked();
//
        EditText nameTextField = (EditText) findViewById(R.id.name_textfield);
        String addNameField =  nameTextField.getText().toString();
//
        int price=calculatePrice(hasWhippedCream, hasChocolate);
        String summary=createOrderSummary(price, hasWhippedCream, hasChocolate, addNameField);

//     //  String priceMessage="Total: $"+price+"\nThank you!";

//      /*
    //       display(quantity);
//        displayPrice(quantity*5);
//
//        */


        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_SUBJECT, "Just Java for "+addNameField);
        intent.putExtra(Intent.EXTRA_TEXT, summary);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }


    }

    /**
     * This method displays the given text on the screen.
     */


    public int calculatePrice(boolean addWhippedCream, boolean addChocolate){
        int basePrice = 5;

        if(addWhippedCream){
            basePrice = basePrice+1;
        }
        if(addChocolate){
            basePrice = basePrice+2;
        }

       return quantity*basePrice;
    }
    private String createOrderSummary(int price, boolean addWhippedCream, boolean addChocolate, String addName){

        String summary="Name: "+addName+"\nAdd whipped cream: "+addWhippedCream+"\nAdd Chocolate: "+addChocolate+"\nQuantity: "+quantity+"\nTotal: "+price+"\nThank you!";
        return summary;


    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void    displayQuantity(int number) {
        TextView quantityTextView = (TextView) findViewById(
                R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    /**
     * This method displays the given price on the screen.
     */


}