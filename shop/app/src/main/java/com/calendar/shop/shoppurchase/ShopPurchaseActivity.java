package com.calendar.shop.shoppurchase;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.calendar.shop.R;

public class ShopPurchaseActivity extends AppCompatActivity implements View.OnClickListener, View.OnLongClickListener {

    EditText et_id,et_amount;
    ImageView iv_finger;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_purchase);
        init();
    }

    private void init() {
        et_amount=findViewById(R.id.et_price);
        et_id=findViewById(R.id.et_id);
        iv_finger=findViewById(R.id.iv_finger);

        iv_finger.setOnLongClickListener(this);
    }

    @Override
    public void onClick(View view) {

        if (view.getId()==R.id.iv_finger)
        {
            startActivity(new Intent(this,PurchaseAcceptActivity.class));
        }
    }

    @Override
    public boolean onLongClick(View view) {
        if (view.getId()==R.id.iv_finger)
        {
            startActivity(new Intent(this,PurchaseAcceptActivity.class).putExtra("amount",Integer.parseInt(et_amount.getText().toString())).putExtra("value",Integer.parseInt(et_id.getText().toString())));
        }
        return true;
    }
}
