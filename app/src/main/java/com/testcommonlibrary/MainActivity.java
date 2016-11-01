package com.testcommonlibrary;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.commonlibrary.datarequests.OnRequestCallback;
import com.commonlibrary.datarequests.OnSubscribe;
import com.commonlibrary.datarequests.Requestable;
import com.commonlibrary.datarequests.requests.HttpDataRequest;
import com.commonlibrary.datarequests.threadrunners.SingleThreadRunner;
import com.commonlibrary.datarequests.threadrunners.UiThreadRunner;
import com.commonlibrary.http.HttpDataManager;
import com.commonlibrary.http.OkHttpDataSupplier;
import com.commonlibrary.http.OkHttpManager;
import com.commonlibrary.http.PostResponse;
import com.commonlibrary.utils.RegexUtils;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

  private TextView label;
  private EditText input;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    input = (EditText) findViewById(R.id.input);
    label = (TextView) findViewById(R.id.label);

    label.setText("is email:" + RegexUtils.isEmail("huangzefeng@hello"));

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
    //final String url = input.getText().toString();
    //HttpDataRequest.newBuilder(url).get().build().execute(new OnRequestCallback<PostResponse>() {
    //  @Override public void onResult(final PostResponse result) {
    //    label.setText(new String(result.getData()));
    //  }
    //
    //  @Override public void onError(final int code, final String msg) {
    //    Toast.makeText(MainActivity.this, "request error code=" + code + ",msg=" + msg,
    //        Toast.LENGTH_SHORT).show();
    //  }
    //});
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
    final String url = "http://testwmc.notedown.cn/interface/user/register_by_email";
    final String json =
        "{\n\t\"first_name\": \"st\",\n\t\"last_name\": \"mh\",\n\t\"email\": \"2635645792@qq.com\",\n\t\"passwd\": \"123456\"\n}";
    final HashMap<String, String> headers = new HashMap<>();
    headers.put("content-type", "application/json");
    headers.put("cache-control", "no-cache");
    headers.put("postman-token", "b1cdb9ab-5805-c26e-b95a-88f32fb01b2a");
    Requestable.create(new OnSubscribe<PostResponse>() {
      @Override public void onCall(OnRequestCallback<PostResponse> callback) {
        PostResponse response =
            HttpDataManager.getInstance().getSupplier().postJsonData(url, json, headers, 0);

        if (response != null) {
          if (response.getCode() >= 200 && response.getCode() <= 300) {
            callback.onResult(response);
          } else {
            callback.onError(response.getCode(), response.getMessage());
          }
        } else {
          callback.onError(-1, "response is empty");
        }
      }
    })
        .setRequestRunner(new SingleThreadRunner())
        .setCallbackRunner(new UiThreadRunner())
        .subscribe(new OnRequestCallback<PostResponse>() {

          @Override public void onResult(PostResponse result) {
            Toast.makeText(MainActivity.this,result.getStringData(),Toast.LENGTH_SHORT).show();
          }

          @Override public void onError(int code, String msg) {

          }
        });
  }
}
