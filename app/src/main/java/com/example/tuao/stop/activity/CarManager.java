package com.example.tuao.stop.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tuao.stop.R;
import com.example.tuao.stop.adapter.CarManagerRVAdapter;
import com.example.tuao.stop.bean.CarManagerBean;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import static com.baidu.mapapi.BMapManager.getContext;

@SuppressWarnings("ALL")
public class CarManager extends AppCompatActivity implements View.OnClickListener{

    private ImageView toolbarBack;
    private TextView toolbarTitle, textTip;
    private Button btnNew, btnNewBottom;
    private RefreshLayout refreshLayout;
    private LinearLayout layoutList;

    RecyclerView recyclerViewCar;
    LinearLayoutManager layoutManager;
    CarManagerRVAdapter carManagerRVAdapter;
    public static List<CarManagerBean> carManagerBeanList = new ArrayList<>();
    private static boolean isAdded;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_manager);

        toolbarBack = findViewById(R.id.toolbar_back);
        toolbarTitle = findViewById(R.id.toolbar_title);
        refreshLayout = findViewById(R.id.refreshLayout);
        textTip = findViewById(R.id.text_car_manager_tips);
        btnNew = findViewById(R.id.btn_car_manager_new_1);
        btnNewBottom = findViewById(R.id.btn_car_manager_new_2);
        recyclerViewCar = findViewById(R.id.recyclerView_car_manager);
        layoutList = findViewById(R.id.layout_car_manager);

        layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);

        if (!isAdded) {
            carManagerBeanList.add(new CarManagerBean("京A-QWERTY", false));
            carManagerBeanList.add(new CarManagerBean("鄂S-ZXCVBN", true));
            isAdded = true;
        }

        carManagerRVAdapter = new CarManagerRVAdapter(carManagerBeanList);
        recyclerViewCar.setLayoutManager(layoutManager);
        recyclerViewCar.setAdapter(carManagerRVAdapter);

        toolbarTitle.setText("车辆管理");
        toolbarBack.setOnClickListener(this);
        btnNew.setOnClickListener(this);
        btnNewBottom.setOnClickListener(this);

        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                refreshLayout.finishRefresh(2000);
                Toast.makeText(CarManager.this, "开始刷新！", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        if (carManagerBeanList == null || carManagerBeanList.size() == 0){
            textTip.setVisibility(View.VISIBLE);
            btnNew.setVisibility(View.VISIBLE);
            layoutList.setVisibility(View.GONE);
        }else{
            textTip.setVisibility(View.GONE);
            btnNew.setVisibility(View.GONE);
            layoutList.setVisibility(View.VISIBLE);
        }
        carManagerRVAdapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.toolbar_back:
                finish();
                break;
            case R.id.btn_car_manager_new_2:
            case R.id.btn_car_manager_new_1:
                Intent intent = new Intent(CarManager.this, CarManagerNew.class);
                startActivity(intent);
                break;
        }
    }
}
