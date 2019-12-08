package com.calendar.shop;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ShopProfileActivity extends AppCompatActivity {

    TextView tv_name,tv_email,tv_mobile,tv_address;
    int remainedAmt=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        init();


        tv_name.setText("Shop Data Name");
        tv_mobile.setText("9440640646");
        tv_email.setText("Dhanarajpeddireddy@gmail.com");
        tv_address.setText("Vijayawada 521225 ");




    }

    private void init() {

        tv_name=findViewById(R.id.tv_name);
        tv_email=findViewById(R.id.tv_email);
        tv_mobile=findViewById(R.id.tv_mobile);
        tv_address=findViewById(R.id.tv_address);



    }
}
