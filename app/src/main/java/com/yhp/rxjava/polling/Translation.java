package com.yhp.rxjava.polling;

import android.util.Log;

/**
 * @Author yihuapeng
 * @Date 2020/7/16 17:39
 **/
public class Translation {
    private int status;

    private content content;
    private static class content {
        private String from;
        private String to;
        private String vendor;
        private String out;
        private int errNo;
    }

    //定义 输出返回数据 的方法
    public void show() {
        Log.d("RxJava", content.out );
    }


}
