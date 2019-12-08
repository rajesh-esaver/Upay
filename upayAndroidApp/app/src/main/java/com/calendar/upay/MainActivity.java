package com.calendar.upay;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.calendar.upay.profile.ProfileActivity;
import com.calendar.upay.transactions.TransactionDetailsActivity;
import com.calendar.upay.transactions.Transactions;
import com.calendar.upay.transactions.TransactionsAdapter;
import com.calendar.upay.userpayment.UserPaymentActivity;
import com.google.android.material.navigation.NavigationView;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, TransactionsAdapter.callBack {

    int id;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        init();

        getTransactions();

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

            one.setShopName("D Mart");
            one.setTimestamp("08-dec-2019");
            one.setAmount(2600);


            Transactions two=new Transactions();


            two.setShopName("Big Bazar");
            two.setTimestamp("03-dec-2019");
            two.setAmount(5300);

            transactions.add(one);
            transactions.add(two);

            adapter=new TransactionsAdapter(transactions,this,this);
            rv_content.setAdapter(adapter);
        }

    }

    DrawerLayout drawer;
    NavigationView navigationView;
    RecyclerView rv_content;
    TransactionsAdapter adapter;


    private void init() {
        id=getIntent().getIntExtra("id",0);
        drawer = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        rv_content=findViewById(R.id.rv_content);
        rv_content.setLayoutManager(new LinearLayoutManager(this));


        textView=findViewById(R.id.tv_noContent);

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        int id = menuItem.getItemId();


        if (id==R.id.nav_profile)
        {
            startActivity(new Intent(this, ProfileActivity.class).putExtra("id",this.id));
        }
       else if (id==R.id.nav_payment)
        {
            startActivity(new Intent(this, UserPaymentActivity.class));
        }


        else if (id==R.id.nav_switch)
        {
            startActivity(new Intent(this, LauncherActivity.class));
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onClick(int position) {

    }
}





/*   RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://www.googleapis.com/customsearch/v1?key=AIzaSyBmSXUzVZBKQv9FJkTpZXn0dObKgEQOIFU&cx=014099860786446192319:t5mr0xnusiy&q=AndroidDev&alt=json&searchType=image";
        JsonObjectRequest jsObjRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        }){

            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("name", "Androidhive");
                params.put("email", "abc@androidhive.info");
                params.put("password", "password123");

                return params;
            }};
        queue.add(jsObjRequest);*/