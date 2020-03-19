package com.example.ndkcpp2.aidl;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public abstract class BinderImpl extends Binder implements IMyInterface {
    public static final String SERVICE_NAME = "com.example.center.getmessage";
    public static final int TRANSSACTION_getMessage = IBinder.FIRST_CALL_TRANSACTION;

    public static IMyInterface asInterface(IBinder obj){
        if (obj == null){
            return null;
        }
        IInterface ii = obj.queryLocalInterface(SERVICE_NAME);//现在同进程的本地服务列表找，找到直接返回即可。(比如我们本地service)
        if (null != ii && ii instanceof IMyInterface){
            return (IMyInterface) ii;
        }
        return new Proxy(obj);
    }

    @Override
    protected boolean onTransact(int code,  Parcel data,  Parcel reply, int flags) throws RemoteException {
        switch (code){
            case TRANSSACTION_getMessage:
                String res = getMessage();
                reply.writeNoException();
                reply.writeString(res + "22222");
                return true;
        }
        return super.onTransact(code, data, reply, flags);
    }

    @Override
    public IBinder asBinder() {
        return this;
    }

    public static class Proxy implements IMyInterface{

        private IBinder remote;
        Parcel data = Parcel.obtain();
        Parcel replay = Parcel.obtain();


        public Proxy(IBinder remote) {
            this.remote = remote;
        }

        @Override
        public String getMessage() {
            try {
                System.out.println("\n第一步进入proxy，将参数传给远程，调用transact");
                remote.transact(TRANSSACTION_getMessage,data,replay,0);
                replay.readException();
                String res = replay.readString();
                System.out.println("\n第四部，返回结果给客户端");
                return res;
            } catch (RemoteException e) {
                e.printStackTrace();
            }finally {
                data.recycle();
                replay.recycle();
            }
            return "";
        }

        @Override
        public IBinder asBinder() {
            return remote;
        }



    }
}
