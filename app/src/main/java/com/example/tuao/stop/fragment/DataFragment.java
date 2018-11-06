package com.example.tuao.stop.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.tuao.stop.activity.ScrollingActivity;
import com.example.tuao.stop.Find_Adapter;
import com.example.tuao.stop.Item.Find_item;
import com.example.tuao.stop.R;

import java.util.ArrayList;
import java.util.List;

public class DataFragment extends Fragment {
    private ListView data;
    private List<Find_item> items=new ArrayList<>();
    private SwipeRefreshLayout swipeRefreshLayout;
    private Find_Adapter adapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.datafragment,container,false);
        data=view.findViewById(R.id.data);
        swipeRefreshLayout=view.findViewById(R.id.swipe_refresh);
        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        data.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Find_item item=items.get(position);
                String data=item.getStopData();
                Intent intent=new Intent(getActivity(),ScrollingActivity.class);
                intent.putExtra("address",data);
                startActivity(intent);
            }
        });
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refresh();
            }
        });
        init();
         adapter=new Find_Adapter(getContext(),R.layout.stop_item,items);
        data.setAdapter(adapter);
        return view;
    }
    private void init(){
        items.clear();
        items.add(new Find_item("武汉科技大学(黄家湖校区)-停车场"));
        items.add(new Find_item("教一楼-停车场"));
        items.add(new Find_item("停车场(丽水南路)"));
        items.add(new Find_item("东南岸-停车场"));
        items.add(new Find_item("园艺文化街-停车场"));
        items.add(new Find_item("湖北中医药大学-停车场"));
    }
    private void refresh(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                }catch (Exception e){
                    e.printStackTrace();
                }
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        init();
                        adapter.notifyDataSetChanged();
                        swipeRefreshLayout.setRefreshing(false);

                    }
                });
            }
        }).start();
        Toast.makeText(getContext(),"刷新成功",Toast.LENGTH_SHORT).show();
    }
}
