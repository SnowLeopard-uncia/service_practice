package com.snowleopard.servicetest;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;
//后台service
public class MyService extends Service {


    public MyService() {
    }
    private Binder mBinder =new DownloadBinder();

    static class DownloadBinder extends Binder{
        public void startDownload(){
            Log.d("MyService", "startDownload: ");
        }

        public int getProgress(){
            Log.d("MyService", "getProgress: ");
            return 0;
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return mBinder;
    }

    //创建service时候调用 首次创建才会调用
    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("MyService", "onCreate: start");
    }

    //service启动的时候调用 每次启动的时候也会调用
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("MyService", "onStartCommand: ");
        return super.onStartCommand(intent, flags, startId);

    }

    //service销毁的时候使用 service销毁时在onDestroy中回收不再使用的资源
    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("MyService", "onDestroy: ");
    }
}