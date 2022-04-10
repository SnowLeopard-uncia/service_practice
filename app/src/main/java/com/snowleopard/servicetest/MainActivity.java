package com.snowleopard.servicetest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button btnStartService;
    Button btnStopService;
    Button btnBindService;
    Button btnUnBindService;
    MyService.DownloadBinder downloadBinder = new MyService.DownloadBinder();

    private ServiceConnection connect  = new ServiceConnection() {
        //Activity与service成功绑定调用
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
    //调用binder里面的方法来操纵service
            downloadBinder= (MyService.DownloadBinder) iBinder;
            downloadBinder.startDownload();
            downloadBinder.getProgress();
        }
        //Service创建进程崩溃或者被杀掉调用
        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        btnStartService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //使用intent启动service
                Intent intent = new Intent(MainActivity.this,MyService.class);
                startService(intent);
            }
        });

        btnStopService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,MyService.class);
                stopService(intent);
            }
        });

        btnBindService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,MyService.class);
            bindService(intent,connect, Context.BIND_AUTO_CREATE);
            }
        });

        btnUnBindService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                unbindService(connect);
            }
        });
    }

    private void initView() {
        btnStartService=findViewById(R.id.btn_start_service);
        btnStopService=findViewById(R.id.btn_stop_service);
        btnBindService =findViewById(R.id.btn_bind_service);
        btnUnBindService=findViewById(R.id.btn_unbind_service);
    }
}