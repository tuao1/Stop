package com.example.tuao.stop.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tuao.stop.R;
import com.example.tuao.stop.bean.CarManagerBean;
import com.jyn.vcview.VerificationCodeView;

import static com.example.tuao.stop.activity.CarManager.carManagerBeanList;

public class CarManagerNew extends AppCompatActivity {

    private ImageView toolbarBack;
    private TextView toolbarTitle;
    private Button btnAdd;
    VerificationCodeView verificationCodeView;
    private static String content;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_manager_new);

        toolbarBack = findViewById(R.id.toolbar_back);
        toolbarTitle = findViewById(R.id.toolbar_title);
        verificationCodeView = findViewById(R.id.input_car_manager_new);
        btnAdd = findViewById(R.id.btn_car_manager_new);

        toolbarTitle.setText("添加车辆");

        verificationCodeView.setOnCodeFinishListener(new VerificationCodeView.OnCodeFinishListener() {
            @Override
            public void onComplete(String content) {
                CarManagerNew.content = content;
                btnAdd.performClick();
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if (content != null && content.length() == 7) {
                    carManagerBeanList.add(new CarManagerBean(content, false));
                    Toast.makeText(CarManagerNew.this, "添加成功!", Toast.LENGTH_SHORT).show();
                    finish();
                }else{
                    Toast.makeText(CarManagerNew.this, "请输入正确的车牌号！", Toast.LENGTH_SHORT).show();
                }
            }
        });

        toolbarBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
