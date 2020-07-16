package com.yhp.rxjava.polling;

import android.util.Log;

/**
 * @Author yihuapeng
 * @Date 2020/7/16 19:16
 **/
public class Translation2 {
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

        Log.d("RxJava", "翻译内容 = " + content.out);

    }

}
