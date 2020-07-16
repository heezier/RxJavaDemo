package com.yhp.rxjava.merge;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.yhp.rxjava.R;
import com.yhp.rxjava.polling.GetRequest_Interface;
import com.yhp.rxjava.polling.Translation;
import com.yhp.rxjava.polling.Translation2;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @Author yihuapeng
 * @Date 2020/7/16 19:40
 **/
public class MergeData extends AppCompatActivity {
    private String TAG  = "MergeData";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 步骤1：创建Retrofit对象
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://fy.iciba.com/") // 设置 网络请求 Url
                .addConverterFactory(GsonConverterFactory.create()) //设置使用Gson解析(记得加入依赖)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create()) // 支持RxJava
                .build();

        // 步骤2：创建 网络请求接口 的实例
        GetRequest_Interface request = retrofit.create(GetRequest_Interface.class);

        //获取网络数据
//        Observable<Translation> observable1 = request.getCall();
        Observable<String> disk = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(final ObservableEmitter<String> emitter) throws Exception {

                ReadFileData readFileData = new ReadFileData();
                readFileData.readFileData(new ReadFileData.Listener() {
                    @Override
                    public void onResult(String str) {
                        Log.d(TAG, "文件数据读取成功1" + Thread.currentThread().getName());
                        emitter.onNext(str);
                    }
                });

            }
        });

        disk.subscribeOn(Schedulers.io())               // （初始被观察者）切换到IO线程进行网络请求1
                .observeOn(AndroidSchedulers.mainThread())  // （新观察者）切换到主线程 处理网络请求1的结果
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept( String s) throws Exception {
                        Log.d(TAG,"最终获取的数据来源 =  " + s + "当前线程："+ Thread.currentThread().getName());
                    }
                });



    }




}
