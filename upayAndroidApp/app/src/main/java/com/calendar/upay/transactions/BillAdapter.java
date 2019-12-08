package com.calendar.upay.transactions;

import android.app.Activity;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.calendar.upay.R;

import java.util.ArrayList;

public class BillAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private ArrayList<Bill> list;
    public Activity activity;



    public BillAdapter(ArrayList<Bill> list, Activity activity) {
        this.list = list;
        this.activity = activity;

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.bill_item, parent, false);
        return new TransactionsAdapter.ViewHolder(listItem);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int position) {
     /*   final Bill item=list.get(position);

        final ViewHolder holder= (ViewHolder) viewHolder;

        holder.tv_name.setText(item.getProductName());
        holder.tv_amount.setText(item.getQuantity()+" * "+ item.getPrice() +" ₹ ");*/


    }

    @Override
    public int getItemCount() {
        return  5;//list.size();
    }


    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_name,tv_amount;

        ViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_name=itemView.findViewById(R.id.tv_shop);
            tv_amount=itemView.findViewById(R.id.tv_amount);

        }
    }
}


