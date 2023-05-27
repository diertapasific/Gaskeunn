package com.example.uts;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

public class TicketDetailPage extends AppCompatActivity {
    ImageView ticketImage;
    TextView ticketName, ticketPrice, ticketDesc;
    EditText quantity;
    Button purchaseBtn;
    SharedPreferences sp,sp2;
    private boolean isQuantityValid(){
        if(quantity.getText().toString().matches("")){
            return false;
        }
        int value = Integer.parseInt(quantity.getText().toString());
        return value < 1;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket_detail_page);

        ticketImage = findViewById(R.id.ticketImage);
        ticketName = findViewById(R.id.ticketName);
        ticketPrice = findViewById(R.id.ticketPrice);
        ticketDesc = findViewById(R.id.ticketDesc);
        quantity = findViewById(R.id.ticketQ);
        purchaseBtn = findViewById(R.id.buttonPurchase);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            ticketName.setText(extras.getString("ticketName"));
            ticketPrice.setText(extras.getString("ticketPrice"));
            if (extras.getString("ticketPrice").equals("40USD")){
                ticketDesc.setText(R.string.tdesc1);
                ticketImage.setImageResource(R.drawable.ticket1);
            } else if (extras.getString("ticketPrice").equals("50USD")){
                ticketDesc.setText(R.string.tdesc2);
                ticketImage.setImageResource(R.drawable.ticket2);
            } else if (extras.getString("ticketPrice").equals("80USD")){
                ticketDesc.setText(R.string.tdesc3);
                ticketImage.setImageResource(R.drawable.ticket3);
            } else if (extras.getString("ticketPrice").equals("30USD")){
                ticketDesc.setText(R.string.tdesc4);
                ticketImage.setImageResource(R.drawable.ticket4);
            } else if (extras.getString("ticketPrice").equals("60USD")){
                ticketDesc.setText(R.string.tdesc5);
                ticketImage.setImageResource(R.drawable.ticket5);
            }
        }
        purchaseBtn.setOnClickListener(view -> {
            if(isQuantityValid()){
                Toast.makeText(this, "Input valid quantity!", Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(this, "Purchase has been made!", Toast.LENGTH_SHORT).show();
                Date currentDate = new Date();
                DateFormat dateFormat = new SimpleDateFormat("dd MMMM yyyy");
                String dateString = dateFormat.format(currentDate);

                //ambil siapa yg login
                sp = getSharedPreferences("loggedIn", MODE_PRIVATE);
                String loggedIn = sp.getString("loggedIn","");

                //siapin sp atas nama yg login
                sp2 = getSharedPreferences(loggedIn+"cart", MODE_PRIVATE);

                //cek ada berapa barang
                Map<String, ?> allEntries = sp2.getAll();
                int count = allEntries.size();
                String counter = Integer.toString(count);

                //masukin barang dengan id
                SharedPreferences.Editor editor = sp2.edit();
                editor.putString("name"+counter,extras.getString("ticketName"));
                editor.putString("q"+counter,quantity.getText().toString());
                editor.putString("date"+counter,dateString);
                editor.apply();

                Intent intent = new Intent(this, TicketPage.class);
                startActivity(intent);
            }
        });
    }
}