package com.testcommonlibrary;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.commonlibrary.dataworkers.controller.ActionCallback;
import com.commonlibrary.dataworkers.controller.SimpleHttpController;
import com.commonlibrary.dataworkers.request.HttpDataRequest;
import com.commonlibrary.http.PostResponse;

public class MainActivity extends AppCompatActivity {

    private TextView label;
    private EditText input;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        input = (EditText) findViewById(R.id.input);
        label = (TextView) findViewById(R.id.label);


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
        final String url = input.getText().toString();
        final HttpDataRequest request = HttpDataRequest.newBuilder().setUrl(url).build();

        SimpleHttpController source = new SimpleHttpController();
        source.setRequest(request).subscribe(new ActionCallback<PostResponse>() {
            @Override
            public void onResult(PostResponse result) {
                if (result != null) {
                    label.setText(new String(result.getData()));
                }
            }

            @Override
            public void onFailue(int code, String msg) {

            }

            @Override
            public void onProgress(float percent) {

            }
        });

    }


}
