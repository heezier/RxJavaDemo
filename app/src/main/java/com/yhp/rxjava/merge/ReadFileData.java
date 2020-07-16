package com.yhp.rxjava.merge;

import android.text.TextUtils;
import android.util.Log;

import com.yhp.rxjava.polling.Translation;

import io.reactivex.Observable;
import io.reactivex.Observer;

/**
 * @Author yihuapeng
 * @Date 2020/7/16 19:44
 **/
public class ReadFileData {
    String result = null;
    public void readFileData(final Listener listener){
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
                try {
                    Log.e("yhp", "开始读取数据");
                    Thread.sleep(4000);
                    result =  "本地数据";
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    result = e.toString();
                }
                listener.onResult(result);
//            }
//        }).start();
    }

    interface Listener{
        void onResult(String str);
    }

}
