package com.example.ndkcpp2.aidl;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class MyService extends Service {

    @Override
    public IBinder onBind(Intent intent) {
        return impl;
    }

    private BinderImpl impl = new BinderImpl() {
        @Override
        public String getMessage() {
            return "11111";
        }
    };
}
