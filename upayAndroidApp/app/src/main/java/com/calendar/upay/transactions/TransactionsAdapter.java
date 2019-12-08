package com.calendar.upay.transactions;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.calendar.upay.R;

import java.util.ArrayList;

public class TransactionsAdapter  extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private ArrayList<Transactions> list;
    public Activity activity;

    callBack callBack;
   public interface callBack
   {
       void onClick(int position);
   }


    public TransactionsAdapter(ArrayList<Transactions> list, Activity activity,callBack callBack) {
        this.list = list;
        this.activity = activity;
        this.callBack=callBack;

        Log.e("in","innnn");
        Log.e("in",list.size()+"");
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.transaction_item, parent, false);
        return new ViewHolder(listItem);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int position) {
        final ViewHolder holder= (ViewHolder) viewHolder;

        Transactions item=list.get(position);


        holder.tv_shop.setText(item.getShopName());
        holder.tv_amount.setText(item.getAmount()+" ₹");
        holder.tv_time.setText(item.getTimestamp()+"");

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callBack.onClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_shop,tv_amount,tv_time;
        CardView cardView;
        ViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_shop=itemView.findViewById(R.id.tv_shop);
            tv_time=itemView.findViewById(R.id.tv_time);
            tv_amount=itemView.findViewById(R.id.tv_amount);
            cardView=itemView.findViewById(R.id.cardview);
        }
    }
}
