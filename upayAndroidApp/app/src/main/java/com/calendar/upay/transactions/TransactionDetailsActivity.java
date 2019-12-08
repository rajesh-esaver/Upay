package com.calendar.upay.transactions;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.calendar.upay.R;

public class TransactionDetailsActivity extends AppCompatActivity {

    RecyclerView rv_content;
    BillAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction_details);


        rv_content=findViewById(R.id.rv_content);
        rv_content.setLayoutManager(new LinearLayoutManager(this));
        adapter=new BillAdapter(null,this);
        rv_content.setAdapter(adapter);
    }
}
