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

public class UserInfo extends AppCompatActivity {

    private ImageView toolbarBack;
    private TextView toolbarTitle;

    RecyclerView recyclerViewUser;
    MyWalletRVAdapter myWalletRVAdapter;
    LinearLayoutManager layoutManager;
    public List<Map<String, String>> userInfoList = new ArrayList<>();
    private static boolean isAdded;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        toolbarBack = findViewById(R.id.toolbar_back);
        toolbarTitle = findViewById(R.id.toolbar_title);
        recyclerViewUser = findViewById(R.id.recyclerView_user);

        Map<String, String> map_1 = new HashMap<>();
        map_1.put("key", "昵称");
        map_1.put("value", "Asche");
        Map<String, String> map_2 = new HashMap<>();
        map_2.put("key", "性别");
        map_2.put("value", "男");
        Map<String, String> map_3 = new HashMap<>();
        map_3.put("key", "生日");
        map_3.put("value", "");
        Map<String, String> map_4 = new HashMap<>();
        map_4.put("key", "手机号");
        map_4.put("value", "181****6351");

        userInfoList.add(map_1);
        userInfoList.add(map_2);
        userInfoList.add(map_3);
        userInfoList.add(map_4);

        layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        myWalletRVAdapter = new MyWalletRVAdapter(userInfoList);
        recyclerViewUser.setLayoutManager(layoutManager);
        recyclerViewUser.setAdapter(myWalletRVAdapter);

        toolbarTitle.setText("个人中心");
        toolbarBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
