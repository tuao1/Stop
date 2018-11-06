package com.example.tuao.stop;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.example.tuao.stop.Item.Find_item;

import java.util.ArrayList;
import java.util.List;


import static com.example.tuao.stop.fragment.FindFragment.list;


public class Find_Adapter extends ArrayAdapter<Find_item> implements Filterable {
    private int resourceID;
   // private MyFilter mFilter;
    public Find_Adapter(Context context,int textVIewResourced,List<Find_item> objects){
        super(context,textVIewResourced,objects);
        resourceID =textVIewResourced;
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Find_item item=getItem(position);
        View view=LayoutInflater.from(getContext()).inflate(resourceID,parent,false);
        TextView findtext=(TextView)view.findViewById(R.id.item_stop);
        findtext.setText(item.getStopData());
        return view;
    }
/*   @Override
//    public Filter getFilter() {
//        if (null == mFilter) {
//            mFilter = new MyFilter();
//        }
//        return mFilter;
//    }
//
//    // 自定义Filter类
//    class MyFilter extends Filter {
//        @Override
//        // 该方法在子线程中执行
//        // 自定义过滤规则
//        protected FilterResults performFiltering(CharSequence constraint) {
//            FilterResults results = new FilterResults();
//
//            List<Find_item> newValues = new ArrayList<Find_item>();
//            String filterString = constraint.toString().trim()
//                    .toLowerCase();
//
//            // 如果搜索框内容为空，就恢复原始数据
//            if (TextUtils.isEmpty(filterString)) {
//                newValues = mBackData;
//            } else {
//                // 过滤出新数据
//                for (Find_item str : mBackData) {
//                    if (-1 != str.getStopData().toLowerCase()
//                            .indexOf(filterString)) {
//                        newValues.add(str);
//                    }
//                }
//            }
//
//            results.values = newValues;
//            results.count = newValues.size();
//
//            return results;
//        }
//
//        @Override
//        protected void publishResults(CharSequence constraint,
//                                      FilterResults results) {
//            list = (List<Find_item>) results.values;
//
//            if (results.count > 0) {
//                adapter.notifyDataSetChanged(); // 通知数据发生了改变
//            } else {
//                adapter.notifyDataSetInvalidated(); // 通知数据失效
//            }
//        }
    }*/
}
