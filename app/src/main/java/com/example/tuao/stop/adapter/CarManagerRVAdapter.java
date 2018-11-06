package com.example.tuao.stop.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tuao.stop.R;
import com.example.tuao.stop.bean.CarManagerBean;

import java.util.List;

import static android.support.constraint.Constraints.TAG;

public class CarManagerRVAdapter extends RecyclerView.Adapter<CarManagerRVAdapter.ViewHolder> {

    private List<CarManagerBean> list;
    private Context context;

    public CarManagerRVAdapter(List<CarManagerBean> list) {
        this.list = list;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textCar;
        Switch aSwitch;
        ImageView imgMenu;
        public ViewHolder(View itemView) {
            super(itemView);
            textCar = itemView.findViewById(R.id.text_item_car_manager);
            aSwitch = itemView.findViewById(R.id.switch_item_car_manager);
            imgMenu = itemView.findViewById(R.id.img_item_car_manager);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_car_manager, parent,false);
        if (context == null){
            context = parent.getContext();
        }
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CarManagerBean bean = list.get(position);

        holder.textCar.setText(bean.getCarId());
        holder.aSwitch.setChecked(bean.isSupported());
        holder.imgMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "More!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
