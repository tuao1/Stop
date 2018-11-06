package com.example.tuao.stop.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tuao.stop.R;
import com.example.tuao.stop.adapter.MyWalletRVAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.baidu.mapapi.BMapManager.getContext;

public class MyWallet extends AppCompatActivity {

    private ImageView toolbarBack;
    private TextView toolbarTitle;

    RecyclerView recyclerViewWallet;
    MyWalletRVAdapter myWalletRVAdapter;
    LinearLayoutManager layoutManager;
    public static List<Map<String, String>> walletList = new ArrayList<>();
    private static boolean isAdded;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_wallet);

        toolbarBack = findViewById(R.id.toolbar_back);
        toolbarTitle = findViewById(R.id.toolbar_title);
        recyclerViewWallet = findViewById(R.id.recyclerView_wallet);

        layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);

        if (!isAdded) {
            Map<String, String> map_1 = new HashMap<>();
            map_1.put("key", "余额");
            map_1.put("value", "0.00元");
            Map<String, String> map_2 = new HashMap<>();
            map_2.put("key", "月卡");
            map_2.put("value", "0张");
            Map<String, String> map_3 = new HashMap<>();
            map_3.put("key", "优惠券");
            map_3.put("value", "10张");
            Map<String, String> map_4 = new HashMap<>();
            map_4.put("key", "无感支付");
            map_4.put("value", "");
            Map<String, String> map_5 = new HashMap<>();
            map_5.put("key", "支付密码");
            map_5.put("value", "");
            walletList.add(map_1);
            walletList.add(map_2);
            walletList.add(map_3);
            walletList.add(map_4);
            walletList.add(map_5);
            isAdded = true;
        }

        myWalletRVAdapter = new MyWalletRVAdapter(walletList, new int[]{2});
        recyclerViewWallet.setLayoutManager(layoutManager);
        recyclerViewWallet.setAdapter(myWalletRVAdapter);

        toolbarTitle.setText("我的钱包");
        toolbarBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
