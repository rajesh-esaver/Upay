package com.calendar.shop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.calendar.shop.shoppurchase.ShopPurchaseActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final EditText et_id;
        setContentView(R.layout.activity_main);
et_id=findViewById(R.id.et_id);

        findViewById(R.id.bt_proceed).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                startActivity(new Intent(MainActivity.this, TransactionsActivity.class).putExtra("id",Integer.parseInt(et_id.getText().toString())));
            }
        });
    }
}
