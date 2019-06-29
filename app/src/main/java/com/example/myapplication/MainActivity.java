package com.example.myapplication;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */

public class MainActivity extends AppCompatActivity {

    int quantity=2,topPrice=0,crP=0,cP=0,vP=0;                                                      //global variable
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder( View view) {
        if(crP==1)
            topPrice+=6;
        if(cP==1)
            topPrice+=10;
        if(vP==1)
            topPrice+=8;

        displayPrice(quantity);
    }
    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView)findViewById(R.id.quant_text_view);
        quantityTextView.setText("" + number);
    }

    /**
     * This method displays the given price on the screen.
     */
    private void displayPrice(int number) {
        CheckBox chk1=(CheckBox)findViewById(R.id.checkBoxW);
        CheckBox chk2=(CheckBox)findViewById(R.id.checkBox2C);
        CheckBox chk3=(CheckBox)findViewById(R.id.checkBox3V);
        EditText namefield=(EditText)findViewById(R.id.editText4);
        String name=namefield.getText().toString();
        String message="Quantity: "+number+"\nTotal:"+NumberFormat.getCurrencyInstance().format(number*(15+topPrice))+"\nThank you!!! ";      //*NumberFormat library provided by android which formats the number according to whatever your local currency is
        if(chk1.isChecked())
        {
            chk1.toggle();
            crP=0;
        }
        if(chk2.isChecked())
        {
            chk2.toggle();
            cP=0;
        }
        if(chk3.isChecked())
        {
            chk3.toggle();
            vP=0;
        }
        topPrice=0;

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));                                 // only email apps should handle this
        intent.putExtra(Intent.EXTRA_SUBJECT,"Order for "+name);
        intent.putExtra(Intent.EXTRA_TEXT,message+"visit again ");
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }

        /*
        Intent intent=new Intent(Intent.ACTION_VIEW);                        //trying INTENT
          intent.setData(Uri.parse("geo:447.6,-122.3"));
          if(intent.resolveActivity(getPackageManager())!=null)
              startActivity(intent); */
    }

    /**
     * This method diplays the changed quantity on pressing '+' button
    */
    public void add(View view)
    {
        quantity++;
        display(quantity);
    }

    /**
     * This method diplays the changed quantity on pressing '-' button
     */
    public void minus(View view)
    {
        if(quantity==1)
            quantity=1;
        else {
            quantity--;
            display(quantity);
        }
    }

    /**
     * This method initiates when user selects "Whipped Cream"
     */
    public void cream(View view )
    {
        crP=1;
    }

    /**
     * This method initiates when user selects "Coco powder"
     */
    public void coco(View view )
    {
        cP=1;
    }

    /**
     * This method initiates when user selects "Vanilla"
     */
    public void van(View view )
    {
        vP=1;
    }
}




