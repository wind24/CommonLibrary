package com.testcommonlibrary;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.commonlibrary.datasource.SimpleDataSource;
import com.commonlibrary.http.PostResponse;
import com.commonlibrary.presentes.HttpDataRequest;
import com.datapresenter.controllers.Controller;
import com.datapresenter.requests.DataRequest;

import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {

    private TextView label;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        label = (TextView) findViewById(R.id.label);

        Controller controller = null;
        DataRequest request = null;
        controller.executeRequest(request).onSubscribe(null);

//        Observable.create(new Observable.OnSubscribe<String>() {
//            @Override
//            public void call(Subscriber<? super String> subscriber) {
//                PostResponse response = HttpDataManager.getInstance().getSupplier().postData(url, null, null,30000);
//                if (response.isSuccess() && response.getData() != null) {
//                    subscriber.onNext(new String(response.getData()));
//                    subscriber.onCompleted();
//                } else {
//                    subscriber.onError(null);
//                }
//            }
//        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<String>() {
//            @Override
//            public void onCompleted() {
//
//            }
//
//            @Override
//            public void onError(Throwable e) {
//            }
//
//            @Override
//            public void onNext(String s) {
//                label.setText(s);
//            }
//        });
    }

    public void loadWeb(View v) {
        final String url = "http://www.bejson.com/";
        final HttpDataRequest request = HttpDataRequest.newBuilder().setUrl(url).build();

        SimpleDataSource<PostResponse> source = new SimpleDataSource<>(Executors.newFixedThreadPool(2));
        source.subscribe(request);

        PostResponse response = source.getResult();
        if (response != null) {
            label.setText("finish bejson");
        }
        Log.d("Test","thread1:"+Thread.currentThread().getName());
        String url1 = "http://www.sina.com";
        HttpDataRequest request1 = HttpDataRequest.newBuilder().setUrl(url1).build();
        source.subscribe(request1);
        response = source.getResult();
        if (response != null) {
            String str = new String(response.getData());
            Log.d("Test","thread1:"+Thread.currentThread().getName());
            label.append("finish sina");
        }
    }


}
