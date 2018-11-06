package com.example.tuao.stop.Activity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.tuao.stop.R;

import java.util.List;

public class Share extends AppCompatActivity {


    private ImageView qq;
    private ImageView wechat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share);
        qq = (ImageView) findViewById(R.id.qq);
        wechat = (ImageView) findViewById(R.id.wechat);
        qq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Shareqq(Share.this,"倾力推荐！到软件商店搜索便捷停车,享受免费停车服务！下载链接www.baidu.com");
            }
        });
        wechat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShareWeChat(Share.this,"倾力推荐！到软件商店搜索便捷停车,享受免费停车服务！下载链接www.baidu.com");
            }
        });
    }

    public static class PlatformUtil {
        public static final String PACKAGE_WECHAT = "com.tencent.mm";
        public static final String PACKAGE_MOBILE_QQ = "com.tencent.mobileqq";

        public static boolean isInstallApp(Context context, String app_package) {
            final PackageManager packageManager = context.getPackageManager();
            List<PackageInfo> pInfo = packageManager.getInstalledPackages(0);
            if (pInfo != null){
                for (int i=0;i<pInfo.size();i++){
                    String pn = pInfo.get(i).packageName;
                    if (app_package.equals(pn)){
                        return true;
                    }
                }
            }
            return false;
        }
    }

    private void Shareqq(Context mContext, String content) {
        if (PlatformUtil.isInstallApp(mContext, PlatformUtil.PACKAGE_MOBILE_QQ)) {
            Intent intent = new Intent("android.intent.action.SEND");
            intent.setType("text/plain");
            intent.putExtra(Intent.EXTRA_SUBJECT, "分享");
            intent.putExtra(Intent.EXTRA_TEXT, content);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setComponent(new ComponentName("com.tencent.mobileqq", "com.tencent.mobileqq.activity.JumpActivity"));
            mContext.startActivity(intent);
        } else {
            Toast.makeText(mContext, "您需要安装QQ客户端", Toast.LENGTH_LONG).show();
        }
    }
    private void ShareWeChat(Context mContext, String content) {
        if (PlatformUtil.isInstallApp(mContext,PlatformUtil.PACKAGE_WECHAT)){
            Intent intent = new Intent();
            ComponentName cop = new ComponentName("com.tencent.mm","com.tencent.mm.ui.tools.ShareImgUI");
            intent.setComponent(cop);
            intent.setAction(Intent.ACTION_SEND);
            intent.putExtra("android.intent.extra.TEXT", content);
            intent.putExtra("Kdescription", !TextUtils.isEmpty(content) ? content : "");
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            mContext.startActivity(intent);
        } else {
            Toast.makeText(mContext, "您需要安装微信客户端", Toast.LENGTH_LONG).show();

        }
    }


}