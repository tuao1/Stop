package com.example.tuao.stop.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.tuao.stop.Activity.ScrollingActivity;
import com.example.tuao.stop.Find_Adapter;
import com.example.tuao.stop.Item.Find_item;
import com.example.tuao.stop.R;

import java.util.ArrayList;
import java.util.List;

public class FindFragment extends Fragment {
   // String[] mAdress=new String[7];
    List<String> mAdress=new ArrayList<>();
    public static List<Find_item> list=new ArrayList<>();
   // public static List<Find_item> mBackData;
   public static ListView listView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.findfragment,container,false);
         listView=view.findViewById(R.id.stopListView);
         init();
        ArrayAdapter arrayAdapter=new ArrayAdapter(getContext(),android.R.layout.simple_list_item_1,mAdress);
        listView.setAdapter(arrayAdapter);
        listView.setTextFilterEnabled(true);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
             //  Find_item item=list.get(position);
             //   String data=item.getStopData();
                String data=mAdress.get(position);

             Intent intent=new Intent(getActivity(),ScrollingActivity.class);
              intent.putExtra("data",data);
                startActivity(intent);
          }
        });
        return view;
    }
    private void init(){

       Find_item item1=new Find_item("百联奥特莱斯广场(武汉-盘龙)"+"\n"+"湖北省武汉市荒坡区");
       mAdress.add(item1.getStopData());
       Find_item item2=new Find_item("奥山世纪广场"+"\n"+"湖北省武汉市青山区");
       mAdress.add(item2.getStopData());
       Find_item item3=new Find_item("武汉艾格眼科医院"+"\n"+"湖北省武汉市江汉区");
       mAdress.add(item3.getStopData());
        Find_item item4=new Find_item("武汉科技大学城市学院"+"\n"+"湖北省武汉市洪山区");
        mAdress.add(item4.getStopData());
        Find_item item5=new Find_item("武昌站"+"\n"+"湖北省武汉市武昌区");
        mAdress.add(item5.getStopData());
        Find_item item6=new Find_item("中央大街"+"\n"+"黑龙江省哈尔滨市道里区");
        mAdress.add(item6.getStopData());
        Find_item item7=new Find_item("北京发展大厦"+"\n"+"北京市朝阳区");
        mAdress.add(item7.getStopData());
        Find_item item8=new Find_item("深圳搞咩野炊农庄"+"\n"+"广东省深圳市龙岗区");
        mAdress.add(item8.getStopData());

    }
}
