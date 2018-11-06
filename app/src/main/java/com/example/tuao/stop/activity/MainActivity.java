package com.example.tuao.stop.activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.SearchView;
import android.text.TextUtils;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;
import com.example.tuao.stop.PropertyAnimation;
import com.example.tuao.stop.R;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.DefaultRefreshHeaderCreator;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;


import java.util.ArrayList;
import java.util.List;


import static com.example.tuao.stop.fragment.FindFragment.listView;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,View.OnClickListener,SearchView.OnQueryTextListener {
    private MapView mapView;
    private LocationClient mLocationClient;
    private BaiduMap baiduMap;
    private boolean isFirstLocate=true;
    private TextView load,currentAdress;//屏幕下方点击加载
    private RelativeLayout mHiddenLayout,findhiddenLayout;
    private PropertyAnimation propertyAnimation;
    private ImageView location;//点击定位按钮
    public static StringBuilder currentPosition;
    private SearchView searchView;

    private ImageView imgAvatar;

    static {
        SmartRefreshLayout.setDefaultRefreshHeaderCreator(new DefaultRefreshHeaderCreator() {
            @Override
            public RefreshHeader createRefreshHeader(Context context, RefreshLayout layout) {
                layout.setPrimaryColorsId(R.color.greyWhite, android.R.color.darker_gray);//全局设置主题颜色
                return new ClassicsHeader(context);
            }
        });
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mLocationClient = new LocationClient(getApplicationContext());
        mLocationClient.registerLocationListener(new MyLocationListener());
        // 在setcontentView之前用
        SDKInitializer.initialize(getApplicationContext());
        setContentView(R.layout.activity_main);

        //获取searview实例
      ////  searchView=findViewById(R.id.ab_search);

        //获取字符串实例 当前具体地址
        currentPosition=new StringBuilder();
        //自己定义的属性动画类
        propertyAnimation=new PropertyAnimation(this);
        load=(TextView)findViewById(R.id.load);
        currentAdress=(TextView)findViewById(R.id.currentAddress);
        load.setOnClickListener(this);
        mHiddenLayout=(RelativeLayout)this.findViewById(R.id.showhideView);
        findhiddenLayout=(RelativeLayout)this.findViewById(R.id.showhide_find);
  location=(ImageView)findViewById(R.id.location);
  location.setOnClickListener(this);


        //自己定义的属性动画类
        propertyAnimation = new PropertyAnimation(this);
        load = (TextView) findViewById(R.id.load);
        load.setOnClickListener(this);
        mHiddenLayout = (RelativeLayout) this.findViewById(R.id.showhideView);

        location = (ImageView) findViewById(R.id.location);
        location.setOnClickListener(this);



        mapView = (MapView) findViewById(R.id.bmapView);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(null);
        setSupportActionBar(toolbar);
        //获取BaiduMap实例

        baiduMap=mapView.getMap();
        baiduMap.setMyLocationEnabled(true);

        baiduMap = mapView.getMap();

        MapStatusUpdate statusUpdate = MapStatusUpdateFactory.zoomTo(baiduMap.getMaxZoomLevel() - 3);
        baiduMap.animateMapStatus(statusUpdate);


//悬浮按钮点击事件
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        View headerView = navigationView.inflateHeaderView(R.layout.nav_header_main);
        imgAvatar = headerView.findViewById(R.id.img_home_avatar);
        imgAvatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, UserInfo.class);
                startActivity(intent);
            }
        });

//访问权限
        List<String> permissionList = new ArrayList<>();
        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            permissionList.add(Manifest.permission.ACCESS_FINE_LOCATION);
        }
        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            permissionList.add(Manifest.permission.READ_PHONE_STATE);
        }
        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            permissionList.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }
        if (!permissionList.isEmpty()) {
            String[] permissions = permissionList.toArray(new String[permissionList.size()]);
            ActivityCompat.requestPermissions(MainActivity.this, permissions, 1);
        } else {
            requestLocation();
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
       getMenuInflater().inflate(R.menu.main, menu);
       Menu mMenu=menu;
       MenuItem menuItem=mMenu.findItem(R.id.ab_search);
       searchView=(SearchView)menuItem.getActionView();
        searchView.setQueryHint("搜索附近停车场");
        searchView.setIconifiedByDefault(true);
        searchView.setOnQueryTextListener(this);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }

    /* 点击左侧menu 的点击事件*/
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.menu的点击事件
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            Intent i_1 = new Intent(MainActivity.this, CarManager.class);
            startActivity(i_1);
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {
            Intent i_2 = new Intent(MainActivity.this, ParkRecord.class);
            startActivity(i_2);

        } else if (id == R.id.nav_slideshow) {
            Intent i_3 = new Intent(MainActivity.this, MyWallet.class);
            startActivity(i_3);

        } else if (id == R.id.nav_manage) {
            Toast.makeText(this, "暂未开放", Toast.LENGTH_SHORT).show();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void requestLocation(){
        initLocation();
         mLocationClient.start();
    }
    private void initLocation() {
        LocationClientOption option = new LocationClientOption();
        option.setScanSpan(5000);
        option.setIsNeedAddress(true);
        // option.setLocationMode(LocationClientOption.LocationMode.Device_Sensors);
        mLocationClient.setLocOption(option);
    }



    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0) {
                    for (int result : grantResults) {
                        if (result != PackageManager.PERMISSION_GRANTED) {
                            Toast.makeText(this, "必须同意所有权限才能运行此程序", Toast.LENGTH_SHORT).show();
                            finish();
                            return;
                        }
                    }
                    requestLocation();
                } else {
                    Toast.makeText(this, "发生未知错误", Toast.LENGTH_SHORT).show();
                    finish();
                }
                break;
            default:
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();

        mapView.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mLocationClient.stop();
        mapView.onDestroy();
        baiduMap.setMyLocationEnabled(false);
    }

    private void navigateTo(BDLocation location) {
        if (isFirstLocate) {
            LatLng ll = new LatLng(location.getLatitude(), location.getLongitude());
            MapStatusUpdate update = MapStatusUpdateFactory.newLatLng(ll);
            baiduMap.animateMapStatus(update);

            update=MapStatusUpdateFactory.zoomTo(17f);
            baiduMap.animateMapStatus(update);
            isFirstLocate=false;

            isFirstLocate = false;

        }
        MyLocationData.Builder locationBuilder=new MyLocationData.Builder();
        locationBuilder.latitude(location.getLatitude());
        locationBuilder.longitude(location.getLongitude());
        MyLocationData locationData=locationBuilder.build();
        baiduMap.setMyLocationData(locationData);
    }

    public class MyLocationListener implements BDLocationListener {
        @Override
        public void onReceiveLocation(BDLocation bdLocation) {


            //获取具体街道

            currentPosition.append("“").append(bdLocation.getCity()).append(bdLocation.getDistrict()).append(bdLocation.getStreet()).append("街道附近停车场...").append("”");
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    currentAdress.setText(currentPosition);
                }
            });
            currentPosition.delete(0, currentPosition.length());
            if (bdLocation.getLocType() == BDLocation.TypeGpsLocation || bdLocation.getLocType() == BDLocation.TypeNetWorkLocation) {

                if (bdLocation.getLocType() == BDLocation.TypeGpsLocation || bdLocation.getLocType() == BDLocation.TypeNetWorkLocation) {

                    navigateTo(bdLocation);
                }
            }
        }
    }

    //屏幕上的一些点击事件
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.load:
                if (mHiddenLayout.getVisibility() == View.GONE) {
                    propertyAnimation.animateOpen(mHiddenLayout);
                    propertyAnimation.animationIvOpen(load);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            load.setText("关闭");
                        }
                    });
                } else {
                    propertyAnimation.animateClose(mHiddenLayout);
                    propertyAnimation.animationIvClose(load);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            load.setText("点击展开更多");
                        }
                    });
                }
                break;
            case R.id.location: //requestLocation();//点击定位待写
        }


}


//搜索框的事件
    @Override
    public boolean onQueryTextChange(String newText) {
        if(!TextUtils.isEmpty(newText)) {
listView.setFilterText(newText);
            if (findhiddenLayout.getVisibility() == View.GONE) {
                propertyAnimation.animateOpen(findhiddenLayout);
            }
          //  listView.setFilterText(newText);
           // adapter.getFilter().filter(newText);

        }

else {

           listView.clearTextFilter();
            //adapter.getFilter().filter("");
            if (findhiddenLayout.getVisibility() == View.VISIBLE) {
                propertyAnimation.animateClose(findhiddenLayout);
            }
        }
        return true;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }
}
