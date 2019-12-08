package com.calendar.shop.shoppurchase;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.calendar.shop.R;

public class PurchaseAcceptActivity extends AppCompatActivity {

    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase_accept);

        imageView=findViewById(R.id.iv_image);

        int i=getIntent().getIntExtra("value",0);

        if (i==1)
        {
            imageView.setImageDrawable(getDrawable(R.drawable.rajesh));
        }
       else if (i==2)
        {
            imageView.setImageDrawable(getDrawable(R.drawable.dhana));
        }


       findViewById(R.id.bt_accept).setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Toast.makeText(getApplicationContext(),"Success",Toast.LENGTH_SHORT).show();
           }
       });


        findViewById(R.id.bt_reject).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"Fail",Toast.LENGTH_SHORT).show();
            }
        });

    }
}
