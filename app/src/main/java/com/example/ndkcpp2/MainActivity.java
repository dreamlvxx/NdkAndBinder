package com.example.ndkcpp2;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.ndkcpp2.aidl.BinderImpl;
import com.example.ndkcpp2.aidl.IMyInterface;
import com.example.ndkcpp2.aidl.MyService;

public class MainActivity extends AppCompatActivity {

    static {
        System.loadLibrary("myfirstlib");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        Toast.makeText(this,stringFromJNI(),Toast.LENGTH_LONG).show();
        startSer();
    }

    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            IMyInterface iMyInterface = BinderImpl.asInterface(service);
            String s = iMyInterface.getMessage();
            System.out.println("=================" + s);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    private void startSer() {
        Intent intent = new Intent(this, MyService.class);
        bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);
    }

    public native String stringFromJNI();
}
