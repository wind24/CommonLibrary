package com.testcommonlibrary;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.commonlibrary.datacontroller.NetworkDataController;
import com.commonlibrary.presentes.StringDataRequest;

public class MainActivity extends AppCompatActivity {

    private TextView label;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
        final String url = "http://www.bejson.com/";
        StringDataRequest request = StringDataRequest.newBuilder().setUrl(url).build();

        NetworkDataController.NetworkDataSource source = NetworkDataController.createDataSource(request);
        NetworkDataController<String> controller = new NetworkDataController<>();
        String result = controller.get(source);
        label.setText(result);
    }


}
