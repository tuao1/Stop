package com.example.tuao.stop.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.tuao.stop.R;

public class ScrollingActivity extends AppCompatActivity implements View.OnClickListener {
    private RelativeLayout commitmonet,yueka;
    private TextView bowei,money,juli,goto_baidu;
    private String data, address,dq;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        bowei=(TextView)findViewById(R.id.bowei);
        money=(TextView)findViewById(R.id.money);
        juli=(TextView)findViewById(R.id.juli);
        goto_baidu=(TextView)findViewById(R.id.goto_baidu);
        goto_baidu.setOnClickListener(this);



        commitmonet=(RelativeLayout)findViewById(R.id.commitmoney);
        yueka=(RelativeLayout)findViewById(R.id.yuekataocan);
        commitmonet.setOnClickListener(this);
        yueka.setOnClickListener(this);
        ActionBar actionBar=getSupportActionBar();
        if(actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        Intent intent=getIntent();
         data=intent.getStringExtra("data");
          address=getIntent().getStringExtra("address");
        CollapsingToolbarLayout collapsingToolbarLayout=(CollapsingToolbarLayout)findViewById(R.id.toolbar_layout);
        if(data!=null)
        { collapsingToolbarLayout.setTitle(data);}
        else if(address!=null){collapsingToolbarLayout.setTitle(address);}

        ImageView view=(ImageView)findViewById(R.id.view);
        Glide.with(this).load(R.drawable.view).into(view);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home: finish(); return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.commitmoney:Toast.makeText(ScrollingActivity.this,"该停车场未开放此功能",Toast.LENGTH_SHORT).show();break;
            case R.id.yuekataocan:Toast.makeText(ScrollingActivity.this,"敬请期待",Toast.LENGTH_SHORT).show();break;
            case R.id.goto_baidu:Intent i1=new Intent();
            if(data!=null){dq=data;}
            else if(address!=null){dq=address;}
            i1.setData(Uri.parse("baidumap://map/place/search?query="+dq+"&region=wuhan&src=andr.baidu.Stop"));
            startActivity(i1);break;
        }
    }
}
