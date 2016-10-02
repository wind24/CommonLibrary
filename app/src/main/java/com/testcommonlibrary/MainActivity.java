package com.testcommonlibrary;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.commonlibrary.datarequests.OnRequestCallback;
import com.commonlibrary.datarequests.requests.HttpDataRequest;
import com.commonlibrary.http.PostResponse;

public class MainActivity extends AppCompatActivity {

  private TextView label;
  private EditText input;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    input = (EditText) findViewById(R.id.input);
    label = (TextView) findViewById(R.id.label);

    //Observable.create(new Observable.OnSubscribe<String>() {
    //    @Override
    //    public void call(Subscriber<? super String> subscriber) {
    //        PostResponse response = HttpDataManager.getInstance().getSupplier().postData(url, null, null,30000);
    //        if (response.isSuccess() && response.getData() != null) {
    //            subscriber.onNext(new String(response.getData()));
    //            subscriber.onCompleted();
    //        } else {
    //            subscriber.onError(null);
    //        }
    //    }
    //}).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<String>() {
    //    @Override
    //    public void onCompleted() {
    //
    //    }
    //
    //    @Override
    //    public void onError(Throwable e) {
    //    }
    //
    //    @Override
    //    public void onNext(String s) {
    //        label.setText(s);
    //    }
    //});
  }

  public void loadWeb(View v) {
    final String url = input.getText().toString();
    HttpDataRequest.newBuilder(url).get().build().execute(new OnRequestCallback<PostResponse>() {
      @Override public void onResult(final PostResponse result) {
        label.setText(new String(result.getData()));
      }

      @Override public void onError(final int code, final String msg) {
        Toast.makeText(MainActivity.this, "request error code=" + code + ",msg=" + msg,
            Toast.LENGTH_SHORT).show();
      }
    });
    //final HttpDataRequest request = HttpDataRequest.newBuilder().setUrl(url).build();
    //
    //SimpleTaskController<PostResponse> source = new SimpleTaskController<>(Executors.newFixedThreadPool(2));
    //source.callwith(request).subscribe(new Callback<PostResponse>() {
    //    @Override public void onResult(PostResponse result) {
    //        if (result != null) {
    //            label.setText(new String(result.getData()));
    //        }
    //    }
    //
    //    @Override public void onFailue(int code, String msg) {
    //        label.setText("error code="+code+",message="+msg);
    //    }
    //
    //    @Override public void onProgress(float percent) {
    //
    //    }
    //});

  }
}
