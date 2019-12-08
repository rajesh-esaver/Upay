package com.calendar.shop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.calendar.shop.shoppurchase.ShopPurchaseActivity;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class TransactionsActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, TransactionsAdapter.callBack {
int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transactions);


        init();
        id=getIntent().getIntExtra("id",id);

        getTransactions();

    }

    DrawerLayout drawer;
    NavigationView navigationView;
    RecyclerView rv_content;
    TransactionsAdapter adapter;
    TextView textView;


    private void init() {
        drawer = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        textView = findViewById(R.id.tv_noContent);
        navigationView.setNavigationItemSelectedListener(this);

        rv_content=findViewById(R.id.rv_content);
        rv_content.setLayoutManager(new LinearLayoutManager(this));
        adapter=new TransactionsAdapter(null,this,this);
        rv_content.setAdapter(adapter);


    }



    private void getTransactions() {


        if (id==1)
        {
            rv_content.setVisibility(View.GONE);
            textView.setVisibility(View.VISIBLE);
        }
        else if (id==2)
        {
            rv_content.setVisibility(View.VISIBLE);
            textView.setVisibility(View.GONE);

            ArrayList<Transactions> transactions=new ArrayList<>();

            Transactions one=new Transactions();

            one.setShopName("Dhana");
            one.setTimestamp("08-dec-2019");
            one.setAmount(2600);


            Transactions two=new Transactions();


            two.setShopName("Rajesh");
            two.setTimestamp("03-dec-2019");
            two.setAmount(5300);

            transactions.add(one);
            transactions.add(two);

            adapter=new TransactionsAdapter(transactions,this,this);
            rv_content.setAdapter(adapter);
        }

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        int id = menuItem.getItemId();


        if (id==R.id.nav_profile)
        {
            startActivity(new Intent(this, ShopProfileActivity.class));
        }
        else if (id==R.id.nav_purchase)
        {
            startActivity(new Intent(this, ShopPurchaseActivity.class));
        }


        else if (id==R.id.nav_switch)
        {
            startActivity(new Intent(this, MainActivity.class));
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onClick(int position) {

    }
}
