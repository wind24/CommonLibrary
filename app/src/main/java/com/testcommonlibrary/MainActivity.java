package com.testcommonlibrary;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.commonlibrary.http.HttpDataUtils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import rx.Observable;
import rx.Scheduler;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private TextView label;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        label = (TextView) findViewById(R.id.label);

        final String url = "http://www.bejson.com/";
        Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                byte[] data = HttpDataUtils.getByteData(url, null, null);
                if (data != null) {
                    subscriber.onNext(new String(data));
                    subscriber.onCompleted();
                } else {
                    subscriber.onError(null);
                }
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onNext(String s) {
                label.setText(s);
            }
        });
    }


}
