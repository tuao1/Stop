package com.example.tuao.stop.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.tuao.stop.R;

import java.util.List;
import java.util.Map;

import static android.support.constraint.Constraints.TAG;

public class MyWalletRVAdapter extends RecyclerView.Adapter<MyWalletRVAdapter.ViewHolder>{

    private List<Map<String, String>> list;
    // 指定position Item下面添加margin
    private int[] array;

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textLeft, textRight;
        public ViewHolder(View itemView) {
            super(itemView);
            textLeft = itemView.findViewById(R.id.text_item_wallet_left);
            textRight = itemView.findViewById(R.id.text_item_wallet_right);
        }
    }

    public MyWalletRVAdapter(List<Map<String, String>> list, int[] array) {
        this.list = list;
        this.array = array;
    }

    public MyWalletRVAdapter(List<Map<String, String>> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_wallet, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Map<String, String> map = list.get(position);
        holder.textLeft.setText(map.get("key"));
        holder.textRight.setText(map.get("value"));

        if (array != null){
            for(int i: array){
                if (position == i){
                    RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) holder.itemView.getLayoutParams();
                    layoutParams.setMargins(0,0,0,40);
                    holder.itemView.setLayoutParams(layoutParams);
                }
            }
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
