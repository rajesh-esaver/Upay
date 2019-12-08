package com.calendar.upay.profile;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.calendar.upay.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileActivity extends AppCompatActivity {

    TextView tv_name,tv_email,tv_mobile,tv_limited_amt,tv_utilized_amt,tv_remained_amt;
    int remainedAmt=0;

    CircleImageView imageView;

    int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        init();

         id=getIntent().getIntExtra("id",0);

         if (id==2)
         {
             tv_name.setText("Dhanaraj peddireddy");
             tv_mobile.setText("9440640646");
             tv_email.setText("Dhanarajpeddireddy@gmail.com");
             tv_limited_amt.setText("30000 ₹");
             tv_utilized_amt.setText("20000 ₹");


             remainedAmt=10000;
             tv_remained_amt.setText(remainedAmt+" ₹");

             imageView.setImageDrawable(getDrawable(R.drawable.dhana));
         }


      else  if (id==1)
        {
            tv_name.setText("Rajesh Kumar Kella");
            tv_mobile.setText("8125990990");
            tv_email.setText("RajeshKella@gmail.com");
            tv_limited_amt.setText("50000 ₹");
            tv_utilized_amt.setText("20000 ₹");


            remainedAmt=30000;
            tv_remained_amt.setText(remainedAmt+" ₹");

            imageView.setImageDrawable(getDrawable(R.drawable.rajesh));
        }


    }

    private void init() {

        tv_name=findViewById(R.id.tv_name);
        tv_email=findViewById(R.id.tv_email);
        tv_mobile=findViewById(R.id.tv_mobile);
        tv_limited_amt=findViewById(R.id.tv_limitedAmount);
        tv_remained_amt=findViewById(R.id.tv_RemaingAmount);
        tv_utilized_amt=findViewById(R.id.tv_UtilizedAmount);
        imageView=findViewById(R.id.profile_image);

    }
}
